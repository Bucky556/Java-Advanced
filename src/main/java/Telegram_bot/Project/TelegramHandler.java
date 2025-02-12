package Telegram_bot.Project;

import Telegram_bot.Project.App.FieldType;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.concurrent.ConcurrentHashMap;

public class TelegramHandler {
    private final TelegramBot telegramBot = new TelegramBot("8144761099:AAHTBv_LZQ9U0mi5RH655OXeqRoLpoNmeOc");
    private static final ConcurrentHashMap<Long, State> usersState = new ConcurrentHashMap<>();

    public void handle(Update update) {
        Message message = update.message();
        CallbackQuery callbackQuery = update.callbackQuery();
        if (message != null) {
            Chat chat = message.chat();
            Long chatID = chat.id();
            String text = message.text();
            if (text.equals("/start")) {
                SendMessage sendMessage = new SendMessage(chatID, "Welcome to /@jsongenerate_bot! \n\n To generate data press /generate");
                telegramBot.execute(sendMessage);
            } else if (text.equals("/generate")) {
                SendMessage sendMessage = new SendMessage(chatID, "Enter File name");
                telegramBot.execute(sendMessage);
                usersState.put(chatID, State.FILE_NAME);
            } else if (State.FILE_NAME.equals(usersState.get(chatID))) {
                System.out.println("Your File name is: " + text);
                SendMessage sendMessage = new SendMessage(chatID, "Enter Row count ");
                telegramBot.execute(sendMessage);
                usersState.put(chatID, State.ROW_COUNT);
            } else if (State.ROW_COUNT.equals(usersState.get(chatID))) {
                System.out.println("Row count is : " + text);
                SendMessage sendMessage = new SendMessage(chatID, "Choose Fields");
                sendMessage.replyMarkup(getMarkUp());
                telegramBot.execute(sendMessage);
                usersState.put(chatID, State.FIELDS);
            } else {
                DeleteMessage deleteMessage = new DeleteMessage(chatID, message.messageId());
                telegramBot.execute(deleteMessage);
            }
        } else {
            System.out.println(callbackQuery.data());  //bu masalan: menudagi language lar keladi(uz,ru,en,kr)
        }
    }

    private Keyboard getMarkUp() {
        FieldType[] fieldTypes = FieldType.values();
        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[12][2]; 
        for (int i = 0; i < fieldTypes.length / 2; i++) {
            InlineKeyboardButton button1 = new InlineKeyboardButton(fieldTypes[i * 2].name());
            InlineKeyboardButton button2 = new InlineKeyboardButton(fieldTypes[i * 2 + 1].name());
             button1.callbackData("" + i * 2);
            button2.callbackData("" + i * 2 + 1);
            buttons[i][0] = button1;
            buttons[i][1] = button2;
        }
        InlineKeyboardMarkup buttonsMarkUp = new InlineKeyboardMarkup(buttons);
        InlineKeyboardButton generateButton = new InlineKeyboardButton("Generate");
        generateButton.callbackData("g");
        buttonsMarkUp.addRow(generateButton);
        return buttonsMarkUp;
    }
}
enum State {
    FIELDS,
    FILE_NAME,
    ROW_COUNT;
}