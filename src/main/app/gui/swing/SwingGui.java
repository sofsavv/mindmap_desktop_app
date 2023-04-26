package main.app.gui.swing;

import main.app.core.Gui;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.MainFrame;
import main.app.messageGenerator.Message;

import javax.swing.*;

public class SwingGui implements Gui {

    private static MainFrame mainFrame;

    public SwingGui(){}

    @Override
    public void start() {
        mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }

    @Override
    public void update(MyNotification notification) {}

    @Override
    public void updateMessage(Message message) {
        JOptionPane.showMessageDialog(mainFrame, message.getContent(), message.getType().toString(), JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void updateInfo(Message message) {
        JOptionPane.showMessageDialog(mainFrame, message.getContent(), message.getType().toString(), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void updateView(MyNotification notification, Object o) {}
}
