package main.app.gui.swing.tree.controller;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedON;
    private JTextField edit;
    public MapTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row){

        clickedON = value;
        edit = new JTextField(value.toString());
        edit.addActionListener(this);
        return edit;
    }
    public boolean isCellEditable(EventObject obj) {
        if (obj instanceof MouseEvent)
            return ((MouseEvent) obj).getClickCount() == 3;
        return false;
    }


}
