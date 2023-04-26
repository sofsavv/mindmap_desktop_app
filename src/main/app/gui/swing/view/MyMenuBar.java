package main.app.gui.swing.view;

import javax.swing.*;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");

        help.add(MainFrame.getInstance().getActionManager().getInfoAction());
        file.add(MainFrame.getInstance().getActionManager().getEditAction());
        file.add(MainFrame.getInstance().getActionManager().getNewAction());
        file.add(MainFrame.getInstance().getActionManager().getSetAuthor());
        file.add(MainFrame.getInstance().getActionManager().getRemoveAction());
        file.add(MainFrame.getInstance().getActionManager().getEditAction());

        this.add(file);
        this.add(help);

    }
}
