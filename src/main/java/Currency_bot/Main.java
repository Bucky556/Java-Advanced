package Currency_bot;

import Telegram_bot.Project.TelegramHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final ThreadLocal<GeneratorCurrency> telegramHandler = ThreadLocal.withInitial(GeneratorCurrency::new);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        TelegramBot telegramBot = new TelegramBot("7355241716:AAGdVsHcVE2MTcgjcuRvq8qDRsqBLtNIOus");
        telegramBot.setUpdatesListener((update) -> {
            for (Update updates : update) {
                CompletableFuture.runAsync(() -> telegramHandler.get().exchange(updates), executorService);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        },Throwable::printStackTrace);
    }
}
