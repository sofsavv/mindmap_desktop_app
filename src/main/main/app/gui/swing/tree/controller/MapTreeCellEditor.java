package main.app.gui.swing.tree.controller;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.composite.MapNodeComposite;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;
    private JTextField edit = null;

    public MapTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row){

        clickedOn = value;
        edit = new JTextField(value.toString());
        edit.addActionListener(this);
        return edit;
    }
    public boolean isCellEditable(EventObject obj) {
        if (obj instanceof MouseEvent)
            return ((MouseEvent) obj).getClickCount() == 3;
        return false;
    }

    public void actionPerformed(ActionEvent e){
        if (!(clickedOn instanceof MapTreeItem))
            return;
        MapTreeItem clicked = (MapTreeItem) clickedOn;

        for(MapNode child: ((MapNodeComposite)clicked.getMapNode().getParent()).getChildren()){
            if(child.getName().equals(e.getActionCommand()) || e.getActionCommand().equals("")) {
                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.CANNOT_RENAME);
                return;
            }
        }

        clicked.setName(e.getActionCommand());
    }

}
