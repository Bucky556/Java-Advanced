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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class GeneratorCurrency {
    private final TelegramBot telegramBot = new TelegramBot("7355241716:AAGdVsHcVE2MTcgjcuRvq8qDRsqBLtNIOus");
    private static final ConcurrentHashMap<Long, State> userState = new ConcurrentHashMap<>();

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/5d36208085b0fe3800b92b6c/latest/USD";
    private static final String API_KEY = "5d36208085b0fe3800b92b6c";
    private static final OkHttpClient client = new OkHttpClient();

    public void exchange(Update update) throws IOException {
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
//                double $UZS = 12500;
//                double rubUZS = 140;
//                double $rub = 90;
                double rateUSD = getExchangeUSD();
                double rateRUB = getExchangeRUB();

                switch (state) {
                    case UZS_TO_USD -> {
                        initialAmount = amount / rateUSD;
                        res = amount + " UZS = " + initialAmount + " $";
                        break;
                    }
                    case UZS_TO_RUBL -> {
                        initialAmount = amount * rateRUB;
                        res = amount + " ₽ = " + initialAmount + " ₽";
                        break;
                    }
                    case RUBL_TO_USD -> {
                        initialAmount = amount / rateRUB;
                        res = amount + " ₽ = " + initialAmount + " $";
                        break;
                    }
                    case RUBL_TO_UZS -> {
                        initialAmount = amount * rateUSD;
                        res = amount + " ₽ = " + initialAmount + " UZS";
                        break;
                    }
                    case USD_TO_UZS -> {
                        initialAmount = amount * rateUSD;
                        res = amount + " $ = " + initialAmount + " UZS";
                        break;
                    }
                    case USD_TO_RUBL -> {
                        initialAmount = amount * rateRUB;
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

    private double getExchangeRUB() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONObject rates = jsonResponse.getJSONObject("conversion_rates");
                return rates.getDouble("RUB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0;
    }

    private double getExchangeUSD() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONObject rates = jsonResponse.getJSONObject("conversion_rates");
                return rates.getDouble("USD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0;
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