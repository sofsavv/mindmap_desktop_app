package main.app.messageGenerator;

import lombok.Getter;
import lombok.Setter;
import main.app.gui.swing.observer.MyNotification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Getter
@Setter
public class Message {

    private String content;
    private MyNotification type;
    private DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();
    private String time;

    public Message(String content, MyNotification type){
        this.content = content;
        this.type = type;
        this.time = date.format(now);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + time + "]" + "[" + content + "]";
    }
}
