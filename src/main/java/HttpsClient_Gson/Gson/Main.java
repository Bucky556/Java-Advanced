package HttpsClient_Gson.Gson;

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
import java.util.Date;
import java.util.List;


public class Main {
    private static String ListAsJson;
    static {
        String file = Main.class.getClassLoader().getResource("Books.json").getFile();
        try {
            ListAsJson =  Files.readString(Path.of(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,new LocalDateTypeAdapter())
                .serializeNulls()
                .setPrettyPrinting()
                .setDateFormat(DateFormat.DEFAULT)
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES)
                .excludeFieldsWithoutExposeAnnotation()
                .setVersion(1.2)
                .create();
        Book book = Book.builder()
                .bookAuthor("Jon Jones")
                .bookId(2313)
                .bookTitle("Spring Boot")
                //.bookDate(new Date())
                .publishedAt(LocalDate.of(2025,2,2))
                .build();
        String toJson = gson.toJson(book);
        System.out.println(toJson);

        Type type = new TypeToken<List<Book>>(){}.getType();
        List<Book> bookList = gson.fromJson(ListAsJson,type); // book ni listga o'girish
        bookList.forEach(System.err::println);
        //fromAndToJson();
    }

    private static void fromAndToJson() {
        Gson gson = new Gson();
        Book book = Book.builder()
                .bookAuthor("Jon Jones")
                .bookId(2313)
                .bookTitle("Spring Boot")
                //.publishedAt(2025,1,2)
                .build();
        String toJson = gson.toJson(book);
        String fromJson = "{\"id\":2313,\"title\":\"Spring Boot\",\"author\":\"Jon Jones\"}\n";
        System.out.println(toJson);
        Book jsonBook = gson.fromJson(fromJson, book.getClass());
        System.out.println(jsonBook);
        System.out.println(ListAsJson);
        Type type = new TypeToken<List<Book>>(){}.getType();
        List<Book> bookList = gson.fromJson(ListAsJson,type); // book ni listga o'girish
        bookList.forEach(System.err::println);
    }
}
