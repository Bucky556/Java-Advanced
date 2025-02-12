package Telegram_bot.Project;

import com.pengrad.telegrambot.ExceptionHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramException;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    private static final ThreadLocal<TelegramHandler> telegramHandler = ThreadLocal.withInitial(TelegramHandler::new);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        TelegramBot telegramBot = new TelegramBot("8144761099:AAHTBv_LZQ9U0mi5RH655OXeqRoLpoNmeOc");
        telegramBot.setUpdatesListener((updates) -> {
                    for (Update update : updates)
                        CompletableFuture.runAsync(() -> telegramHandler.get().handle(update)
                                , executorService);

                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                },
                Throwable::printStackTrace);
    }
}