package main.app.gui.swing.view;

import javax.swing.*;

public class RightToolbar extends JToolBar {

    public RightToolbar(){
        super(VERTICAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getConceptAction());
        add(MainFrame.getInstance().getActionManager().getLinkAction());
        add(MainFrame.getInstance().getActionManager().getMainConcept());
        add(MainFrame.getInstance().getActionManager().getSelectAction());
        add(MainFrame.getInstance().getActionManager().getSettingsAction());
        add(MainFrame.getInstance().getActionManager().getDeleteElementAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getZoomAction());
        add(MainFrame.getInstance().getActionManager().getMoveAction());
    }
}
