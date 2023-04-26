package main.app.gui.swing.controller.projViewActions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ConceptAction extends MyAbstractAction {

    public ConceptAction(){
        putValue(NAME, "Add Concept");
        putValue(SMALL_ICON, loadIcon("/images/elipse.png"));
        putValue(SHORT_DESCRIPTION, "Add Concept");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCurrProjectView().startConceptState();
        System.out.println("... in concept state ...");
    }

}
