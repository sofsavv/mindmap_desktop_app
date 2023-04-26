package main.app.gui.swing.view.dialog;

import javax.swing.*;
import java.awt.*;

public class InfoDialog extends JDialog {

    public InfoDialog(JFrame parent, Icon icon){
        super(parent, "About", true);

        setLayout(new BorderLayout());
        setSize(200,90);
        setResizable(false);
        setLocationRelativeTo(parent);

        JLabel about = new JLabel("Desktop application GeRuMap");

        JPanel descriptionPane = new JPanel();
        descriptionPane.setBackground(Color.lightGray);
        descriptionPane.add(about);
        add(descriptionPane);
    }
}
