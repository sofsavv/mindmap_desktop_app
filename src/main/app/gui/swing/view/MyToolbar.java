package main.app.gui.swing.view;

import javax.swing.*;

public class MyToolbar extends JToolBar {

    public MyToolbar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getEditAction());
        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getRemoveAction());
        add(MainFrame.getInstance().getActionManager().getSetAuthor());
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        add(MainFrame.getInstance().getActionManager().getOpenTemplateAction());
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        add(MainFrame.getInstance().getActionManager().getImageExport());

    }
}
