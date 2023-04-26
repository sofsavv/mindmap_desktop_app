package main.app.errorLogger;

import main.app.gui.swing.observer.MyNotification;
import main.app.messageGenerator.Message;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ErrorLogger{

    public FileLogger(){}

    @Override
    public void log(Message message) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("log.txt", true);
            fw.write(message.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void update(MyNotification notification) {}
    @Override
    public void updateMessage(Message message) {
        log(message);
    }
    @Override
    public void updateInfo(Message message) { log(message) ;}
    @Override
    public void updateView(MyNotification notification, Object o) {}
}
