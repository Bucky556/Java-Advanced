package Telegram_bot.Project.App;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@AllArgsConstructor
@Getter
public class Entry {
    private String fieldName;
    private FieldType fieldType;
    private Supplier<Object> supplier;
}
