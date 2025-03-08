package Currency_bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.UpdatesListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.*;

public class GeneratorCurrency {
    private final TelegramBot telegramBot = new TelegramBot("7355241716:AAGdVsHcVE2MTcgjcuRvq8qDRsqBLtNIOus");
    private static final ConcurrentHashMap<Long, State> userState = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Double> rateCache = new ConcurrentHashMap<>();

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/5d36208085b0fe3800b92b6c/latest/USD";
    private static final OkHttpClient client = new OkHttpClient();

    public void exchange(Update update) throws IOException {
        Message message = update.message();
        if (message == null || message.text() == null) return;

        Chat chat = message.chat();
        Long chatID = chat.id();
        String text = message.text();

        if (text.equals("/start")) {
            sendWelcomeMessage(chatID);
        } else if (text.equals("/generate")) {
            sendCurrencyOptions(chatID);
        } else if (handleCurrencySelection(chatID, text)) {
            return;
        } else if (userState.containsKey(chatID)) {
            handleAmountInput(chatID, text);
        }
    }

    private void sendWelcomeMessage(Long chatID) {
        telegramBot.execute(new SendMessage(chatID, "Assalom alekum, Telegram bot ga xush kelibsiz!\n\n" +
                "Valyuta konvertatsiyasi uchun /generate buyrug'ini yuboring 💁🏻"));
    }

    private void sendCurrencyOptions(Long chatID) {
        SendMessage sendMessage = new SendMessage(chatID, "Valyuta juftligini tanlang:");
        KeyboardButton[][] buttons = {
                {new KeyboardButton("₽ -> UZS"), new KeyboardButton("UZS -> ₽"), new KeyboardButton("UZS -> $")},
                {new KeyboardButton("₽ -> $"), new KeyboardButton("$ -> UZS"), new KeyboardButton("$ -> ₽")}
        };
        sendMessage.replyMarkup(new ReplyKeyboardMarkup(buttons).resizeKeyboard(true));
        telegramBot.execute(sendMessage);
    }

    private boolean handleCurrencySelection(Long chatID, String text) {
        switch (text) {
            case "₽ -> UZS" -> userState.put(chatID, State.RUBL_TO_UZS);
            case "UZS -> ₽" -> userState.put(chatID, State.UZS_TO_RUBL);
            case "UZS -> $" -> userState.put(chatID, State.UZS_TO_USD);
            case "₽ -> $" -> userState.put(chatID, State.RUBL_TO_USD);
            case "$ -> UZS" -> userState.put(chatID, State.USD_TO_UZS);
            case "$ -> ₽" -> userState.put(chatID, State.USD_TO_RUBL);
            default -> {
                return false;
            }
        }
        telegramBot.execute(new SendMessage(chatID, "Miqdorni kiriting:"));
        return true;
    }

    private void handleAmountInput(Long chatID, String text) throws IOException {
        try {
            double amount = Double.parseDouble(text);
            State state = userState.get(chatID);

            double rateUSD = getExchangeRate("UZS");
            double rateRUB = getExchangeRate("RUB");

            double result = switch (state) {
                case UZS_TO_USD -> amount / rateUSD;
                case UZS_TO_RUBL -> amount / rateRUB;
                case RUBL_TO_USD -> (amount * rateRUB) / rateUSD;
                case RUBL_TO_UZS -> amount * rateRUB;
                case USD_TO_UZS -> amount * rateUSD;
                case USD_TO_RUBL -> (amount * rateUSD) / rateRUB;
            };

            DecimalFormat df = new DecimalFormat("#,###.##");
            String message = switch (state) {
                case UZS_TO_USD -> text + " UZS = " + df.format(result) + " $";
                case UZS_TO_RUBL -> text + " UZS = " + df.format(result) + " ₽";
                case RUBL_TO_USD -> text + " ₽ = " + df.format(result) + " $";
                case RUBL_TO_UZS -> text + " ₽ = " + df.format(result) + " UZS";
                case USD_TO_UZS -> text + " $ = " + df.format(result) + " UZS";
                case USD_TO_RUBL -> text + " $ = " + df.format(result) + " ₽";
            };

            telegramBot.execute(new SendMessage(chatID, message));
            userState.remove(chatID);
        } catch (NumberFormatException e) {
            telegramBot.execute(new SendMessage(chatID, "Iltimos, to'g'ri raqam kiriting."));
        }
    }

    private double getExchangeRate(String currency) throws IOException {
        if (rateCache.containsKey(currency)) {
            return rateCache.get(currency);
        }

        Request request = new Request.Builder().url(API_URL).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject rates = new JSONObject(responseBody).getJSONObject("conversion_rates");
                double rate = rates.getDouble(currency);
                rateCache.put(currency, rate);
                return rate;
            }
        }

        throw new IOException("Valyuta kursini olishda xatolik yuz berdi.");
    }

    public static void main(String[] args) {
        GeneratorCurrency generatorCurrency = new GeneratorCurrency();
        generatorCurrency.startBot();
    }

    private void startBot() {
        telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                try {
                    exchange(update);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private enum State {
        RUBL_TO_UZS, UZS_TO_RUBL, UZS_TO_USD, USD_TO_UZS, USD_TO_RUBL, RUBL_TO_USD
    }
}


