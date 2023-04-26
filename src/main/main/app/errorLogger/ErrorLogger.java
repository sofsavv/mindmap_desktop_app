package main.app.errorLogger;

import main.app.gui.swing.observer.Subscriber;
import main.app.messageGenerator.Message;

public interface ErrorLogger extends Subscriber {
    void log(Message message);
}
