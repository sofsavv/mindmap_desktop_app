package main.app.gui.swing.controller.projViewActions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class LinkAction extends MyAbstractAction {

    public LinkAction(){
        putValue(NAME, "Link");
        putValue(SMALL_ICON, loadIcon("/images/link.png"));
        putValue(SHORT_DESCRIPTION, "Link");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCurrProjectView().startLinkState();
        System.out.println("... in link state ...");
    }
}
