package Telegram_bot.Project.App;

public enum FieldType {
    FULL_NAME("\""),
    FIRST_NAME("\""),
    LAST_NAME("\""),
    USERNAME("\""),
    TITLE("\""),
    BLOOD_GROUP("\""),
    PARAGRAPHS("\""),
    WORDS("\""),
    CHARACTERS("\""),
    ZIP_CODE("\""),
    CITY("\""),
    COUNTRY("\""),
    BOOK_AUTHOR("\""),
    CELL_PHONE("\""),
    AGE("");

    private final String i;

    FieldType(String i) {
        this.i = i;
    }


    public CharSequence getJsonPairs(String fieldName, Object value) {
        return "\t\"%s\" : %s%s%s".formatted(fieldName, i, value, i);
    }
}
