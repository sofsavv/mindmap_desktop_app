package main.app.gui.swing.controller.actions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;
import main.app.gui.swing.view.dialog.InfoDialog;

import java.awt.event.ActionEvent;

public class InfoAction extends MyAbstractAction {
    public InfoAction(){
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
        putValue(SMALL_ICON, loadIcon("/images/info.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InfoDialog dialog = new InfoDialog(MainFrame.getInstance(), loadIcon("/images/sofijana1.png"));
        dialog.setVisible(true);
    }
}
