package main.app.gui.swing.view.dialog;

import javax.swing.*;
import java.awt.*;

public class InfoDialog extends JDialog {

    public InfoDialog(JFrame parent, Icon icon){
        super(parent, "About", true);

        setLayout(new BorderLayout());
        setSize(500,350);
        setResizable(false);
        setLocationRelativeTo(parent);

        JLabel name = new JLabel("Sofija Savic");
        JLabel about = new JLabel("Desktop application GeRuMap");

        JPanel descriptionPane = new JPanel();
        descriptionPane.setBackground(Color.pink);
        descriptionPane.add(name);
        descriptionPane.add(about);
        add(descriptionPane);
    }
}
