package main.app.gui.swing.observer;

import main.app.messageGenerator.Message;

public interface Subscriber {

    void update(MyNotification notification);
    void updateMessage(Message message);
    void updateInfo(Message message);
    void updateView(MyNotification notification, Object o);
}
