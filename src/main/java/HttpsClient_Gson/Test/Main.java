package HttpsClient_Gson.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Main {
    private static String ListFromJson;

    static {
        try {
            String file = Main.class.getClassLoader().getResource("language.json").getFile();
            ListFromJson = Files.readString(Path.of(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,new LocalDateTypeAdapter())
                .serializeNulls()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .setVersion(1.2)
                .create();
        Languages languages = Languages.builder()
                .languageId(21)
                .languageAuthor("Devid Thomas")
                .languageTitle("Java")
                .languageDate(new Date())
                .build();
        String toJson = gson.toJson(languages);
        String fromJson = "{\"languageId\":21,\"languageTitle\":\"Java\",\"languageAuthor\":\"Devid Thomas\"}\n";
        System.out.println(toJson);

        Languages language = gson.fromJson(fromJson, Languages.class);
        System.out.println(language);
        System.out.println(ListFromJson); // toJson
        Type type = new TypeToken<List<Languages>>(){}.getType();
        List<Languages> languagesList = gson.fromJson(ListFromJson,type);
        languagesList.forEach(System.err::println); //fromJson
    }
}
