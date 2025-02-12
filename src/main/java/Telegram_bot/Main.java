package Telegram_bot;

import com.pengrad.telegrambot.ExceptionHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramException;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendAudio;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        TelegramBot telegramBot = new TelegramBot("8058837092:AAFdw5OYRck_knJPPQkggHWF19ZXbNGE6ko");
        //sendMessage(telegramBot);
        sendPhoto(telegramBot);
        //sendAudio(telegramBot);
        telegramBot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {
                for (Update update : updates) {
                    Message message = update.message();
//                    String text = message.text();
//                    Chat chat = message.chat();          nima yozsak reply qilib beradi
//                    Long chatId = chat.id();
//                    SendMessage sendMessage = new SendMessage(chatId, "Could not found :" + text);
//                    telegramBot.execute(sendMessage);
                }
                return CONFIRMED_UPDATES_ALL;
            }
        }, new ExceptionHandler() {
            @Override
            public void onException(TelegramException e) {
                System.out.println("Error occurred!!!");
            }
        });
    }

    private static void sendAudio(TelegramBot telegramBot) throws IOException {
        SendAudio sendAudio = new SendAudio("7455218063", Files.readAllBytes(Path.of("/Users/nodirjonokilov/IdeaProjects/Lombok/Techno Project   DJ Geny Tur - DUDUK.mp3")));
        telegramBot.execute(sendAudio);
    }

    private static void sendPhoto(TelegramBot telegramBot) throws IOException {
        SendPhoto sendPhoto = new SendPhoto("7455218063", Files.readAllBytes(Path.of("/Users/nodirjonokilov/IdeaProjects/Lombok/Screenshot 2025-02-05 at 7.40.25 PM.png")));
        telegramBot.execute(sendPhoto);
    }

    private static void sendMessage(TelegramBot telegramBot) {
        SendMessage sendMessage = new SendMessage("7455218063","Message from Java");
//        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup(new String[][]{
//                {"☎ Contact", "⚙ Setting"},
//                {"\uD83D\uDCCD Location"}
//        });
        KeyboardButton[] keyboardButton = {
                new KeyboardButton("Contact").requestContact(true),
                new KeyboardButton("location").requestLocation(true)
        };
        ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup(keyboardButton);
        replyMarkup.addRow(new KeyboardButton("Settings"));
        sendMessage.replyMarkup(replyMarkup);
        replyMarkup.resizeKeyboard(true);
        telegramBot.execute(sendMessage);
    }
}
