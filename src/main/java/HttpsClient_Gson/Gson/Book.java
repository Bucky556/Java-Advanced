package HttpsClient_Gson.Gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class Book {
    @Expose(serialize = false)
    @SerializedName("id")
    private Integer bookId;
    @Expose
    @SerializedName("title")
    private String bookTitle;
    @Expose
    @SerializedName("book_author")
    private String bookAuthor;
    // @Builder.Default
    @Expose
    @Since(1.0)
    private volatile Date bookDate = new Date();
    @Expose
    private LocalDate publishedAt;
}
