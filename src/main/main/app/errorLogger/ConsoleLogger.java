package main.app.errorLogger;

import main.app.gui.swing.observer.MyNotification;
import main.app.messageGenerator.Message;

public class ConsoleLogger implements ErrorLogger{

    public ConsoleLogger(){}

    @Override
    public void log(Message message){
        System.out.println(message);
    }
    @Override
    public void updateMessage(Message message) { log(message); }
    @Override
    public void updateInfo(Message message) { log(message); }
    @Override
    public void update(MyNotification notification) {}
    @Override
    public void updateView(MyNotification notification, Object o) {}
}
