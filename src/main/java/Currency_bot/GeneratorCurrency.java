package Currency_bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.concurrent.ConcurrentHashMap;

public class GeneratorCurrency {
    private final TelegramBot telegramBot = new TelegramBot("7355241716:AAGdVsHcVE2MTcgjcuRvq8qDRsqBLtNIOus");
    private static final ConcurrentHashMap<Long, State> userState = new ConcurrentHashMap<>();

    public void exchange(Update update) {
        Message message = update.message();
        CallbackQuery callbackQuery = update.callbackQuery();
        if (message != null) {
            Chat chat = message.chat();
            Long chatID = chat.id();
            String text = message.text();
            String res = null;
            if (text.equals("/start")) {
                SendMessage sendMessage = new SendMessage(chatID, "Assalom alekum, Telegram bot ga xush kelibsiz!\n\nBot ga start berish uchun /generate ni junating 💁🏻");
                telegramBot.execute(sendMessage);
            } else if (text.equals("/generate")) {
                SendMessage sendMessage = new SendMessage(chatID, "Turini tanlang:");
                KeyboardButton rublUZS = new KeyboardButton("₽ -> UZS");
                KeyboardButton UZSrubl = new KeyboardButton("UZS -> ₽");
                KeyboardButton UZS$ = new KeyboardButton("UZS -> $");

                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton[][]{
                        {UZS$, UZSrubl, rublUZS},
                        {new KeyboardButton("₽ -> $"), new KeyboardButton("$ -> UZS"), new KeyboardButton("$ -> ₽")}
                });
                sendMessage.replyMarkup(replyKeyboardMarkup);
                replyKeyboardMarkup.resizeKeyboard(true);
                telegramBot.execute(sendMessage);
            } else if (text.equals("₽ -> UZS")) {
                userState.put(chatID, State.RUBL_TO_UZS);
                telegramBot.execute(new SendMessage(chatID, "Rubl miqdorini kiriting:"));
            } else if (text.equals("UZS -> ₽")) {
                userState.put(chatID, State.UZS_TO_RUBL);
                telegramBot.execute(new SendMessage(chatID, "Sum miqdorini kiriting:"));
            } else if (text.equals("UZS -> $")) {
                userState.put(chatID, State.UZS_TO_USD);
                telegramBot.execute(new SendMessage(chatID, "Sum miqdorini kiriting:"));
            } else if (text.equals("₽ -> $")) {
                userState.put(chatID, State.RUBL_TO_USD);
                telegramBot.execute(new SendMessage(chatID, "Rubl miqdorini kiriting:"));
            } else if (text.equals("$ -> UZS")) {
                userState.put(chatID, State.USD_TO_UZS);
                telegramBot.execute(new SendMessage(chatID, "Dollar miqdorini kiriting:"));
            } else if (text.equals("$ -> ₽")) {
                userState.put(chatID, State.USD_TO_RUBL);
                telegramBot.execute(new SendMessage(chatID, "Dollar miqdorini kiriting:"));
            } else if (userState.containsKey(chatID)) {
                State state = userState.get(chatID);
                Integer amount;
                try {
                    amount = Integer.parseInt(text);
                } catch (Exception e) {
                    telegramBot.execute(new SendMessage(chatID, "Notug'ri Ma'lumot Kiritildi!!!"));
                    return;
                }
                double initialAmount = 0;
                res = "";
                double $UZS = 12500;
                double rubUZS = 140;
                double $rub = 90;

                switch (state) {
                    case UZS_TO_USD -> {
                        initialAmount = amount / $UZS;
                        res = amount + " UZS = " + initialAmount + " $";
                        break;
                    }
                    case UZS_TO_RUBL -> {
                        initialAmount = amount * rubUZS;
                        res = amount + " ₽ = " + initialAmount + " ₽";
                        break;
                    }
                    case RUBL_TO_USD -> {
                        initialAmount = amount / $rub;
                        res = amount + " ₽ = " + initialAmount + " $";
                        break;
                    }
                    case RUBL_TO_UZS -> {
                        initialAmount = amount * $UZS;
                        res = amount + " ₽ = " + initialAmount + " UZS";
                        break;
                    }
                    case USD_TO_UZS -> {
                        initialAmount = amount * $UZS;
                        res = amount + " $ = " + initialAmount + " UZS";
                        break;
                    }
                    case USD_TO_RUBL -> {
                        initialAmount = amount * $rub;
                        res = amount + " $ = " + initialAmount + " ₽";
                        break;
                    }
                }
            }
            telegramBot.execute(new SendMessage(chatID, res));
            userState.remove(chatID);
        } else {
            System.out.println(callbackQuery.data());
        }
    }
}

enum State {
    RUBL_TO_UZS,
    UZS_TO_RUBL,
    UZS_TO_USD,
    USD_TO_UZS,
    USD_TO_RUBL,
    RUBL_TO_USD
}