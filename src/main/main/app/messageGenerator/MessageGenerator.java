package main.app.messageGenerator;

import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.observer.Publisher;

public interface MessageGenerator extends Publisher {
    void generate(MyNotification type);
}
