package HttpsClient_Gson.Test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class Languages {
    @Expose
    @SerializedName("id")
    private Integer languageId;
    @SerializedName("title")
    @Expose
    private String languageTitle;
    @SerializedName("bookAuthor")
    @Expose
    private String languageAuthor;
    private LocalDate publishedDate;
    @Since(1.0)
    @Expose
    @Builder.Default
    private Date languageDate = new Date();
}
