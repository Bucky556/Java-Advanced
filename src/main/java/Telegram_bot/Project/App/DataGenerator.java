package Telegram_bot.Project.App;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.PhoneNumber;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Supplier;

public class DataGenerator {
    static final Map<FieldType, Supplier<Object>> functions = new HashMap<>();

    static {
        Faker faker = new Faker();
        Name name = faker.name();
        Address address = faker.address();
        PhoneNumber phoneNumber = faker.phoneNumber();
        functions.put(FieldType.FIRST_NAME, name::firstName);
        functions.put(FieldType.LAST_NAME, name::lastName);
        functions.put(FieldType.FULL_NAME, name::fullName);
        functions.put(FieldType.CELL_PHONE, phoneNumber::phoneNumber);
        functions.put(FieldType.COUNTRY, address::country);
        functions.put(FieldType.AGE, () -> faker.random().nextInt(20, 60));
        functions.put(FieldType.BLOOD_GROUP, name::fullName);
        functions.put(FieldType.CITY, address::city);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter fileName:");
        String fileName = scanner.nextLine();
        System.out.println("Enter type:");
        String type = scanner.nextLine();
        System.out.println("Enter count:");
        int count = scanner.nextInt();
        scanner.nextLine();
        List<Pairs> pairs = new ArrayList<>();
        FieldType[] fieldTypes = FieldType.values();
        System.out.println("FieldTypes:");
        for (int i = 0; i < fieldTypes.length; i++) {
            System.out.println((i + 1) + ". " + fieldTypes[i]);
        }

        while (true) {
            System.out.println("Choose FieldType (or type 0 to close):");
            int fieldType = scanner.nextInt();
            scanner.nextLine();
            if (fieldType == 0) break;
            if (fieldType < 1 || fieldType > fieldTypes.length) continue;
            FieldType choosenFieldType = fieldTypes[fieldType - 1];
            System.out.println("Choose FieldName for " + choosenFieldType + ":");
            String fieldName = scanner.nextLine();
            pairs.add(new Pairs(fieldName, choosenFieldType));
        }

        Request request = new Request(fileName, count, type, pairs);
        List<Pairs> pairsList = request.getPairs();
        StringJoiner jsonArray = new StringJoiner(", ", "[", "\n]\n");
        List<Entry> neededMethods = getNeededMethods(pairsList);
        for (int i = 0; i < request.getCount(); i++) {
            StringJoiner jsonData = new StringJoiner(",\n", "\n{\n", "\n}");
            for (Entry entry : neededMethods) {
                Supplier<Object> supplier = entry.getSupplier();
                String fieldName = entry.getFieldName();
                FieldType fieldType = entry.getFieldType();
                Object value = (supplier == null) ? "null" : supplier.get();
                jsonData.add(fieldType.getJsonPairs(fieldName, value));
            }
            jsonArray.add(jsonData.toString());
        }
        System.out.println(jsonArray);

        Files.writeString(Path.of(request.getFileName()), jsonArray.toString());
    }

    private static List<Entry> getNeededMethods(List<Pairs> pairsList) {
        var list = new ArrayList<Entry>();
        for (Pairs pairs : pairsList) {
            list.add(new Entry(pairs.getField_name(), pairs.getFieldType(), functions.get(pairs.getFieldType())));
        }
        return list;
    }
}