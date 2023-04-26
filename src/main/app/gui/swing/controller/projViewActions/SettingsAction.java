package main.app.gui.swing.controller.projViewActions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.MainFrame;
import main.app.gui.swing.view.dialog.SettingsDialog;

import java.awt.event.ActionEvent;

public class SettingsAction extends MyAbstractAction {

    public SettingsAction(){
        putValue(NAME, "Settings");
        putValue(SMALL_ICON, loadIcon("/images/adjust.png"));
        putValue(SHORT_DESCRIPTION, "Settings");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getCurrProjectView().getCurrMapView() == null ||
                MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements().isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.NOTHING_IS_SELECTED);
        }else{
            SettingsDialog dialog = new SettingsDialog(MainFrame.getInstance(), loadIcon("/images/text_edit.png"),
                    loadIcon("/images/line_width.png"));
            if(!dialog.isSelected())
                dialog.setVisible(true);
        }

    }

}
