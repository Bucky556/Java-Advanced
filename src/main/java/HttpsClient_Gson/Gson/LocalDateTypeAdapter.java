package HttpsClient_Gson.Gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        if (jsonWriter == null)
            jsonWriter.nullValue();
        else
            jsonWriter.value(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        JsonToken jsonToken = jsonReader.peek();
        if (jsonToken == JsonToken.NULL)
            return null;
        else
            return LocalDate.parse(jsonReader.toString());
    }
}
