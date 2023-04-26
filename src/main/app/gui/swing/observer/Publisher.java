package main.app.gui.swing.observer;

import main.app.messageGenerator.Message;

public interface Publisher {

    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notify(MyNotification notification);
    void notifyMessage(Message message);
    void notifyInfo(Message message);
    void notifyView(MyNotification notification, Object o);
}
