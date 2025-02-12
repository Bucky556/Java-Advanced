package Telegram_bot.Project.App;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Request {
    private String fileName;
    private int count;
    private String type;
    private List<Pairs> pairs;
}
