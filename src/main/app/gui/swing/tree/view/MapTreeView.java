package main.app.gui.swing.tree.view;

import main.app.gui.swing.tree.controller.MapTreeCellEditor;
import main.app.gui.swing.tree.controller.MapTreeSelectionListener;
import main.app.gui.swing.tree.controller.MyMouseListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeView extends JTree {

    public MapTreeView(DefaultTreeModel defaultTreeModel){
        setModel(defaultTreeModel);
        MapTreeCellRenderer mTreeCellRenderer = new MapTreeCellRenderer();
        addTreeSelectionListener(new MapTreeSelectionListener());
        setCellEditor(new MapTreeCellEditor(this, mTreeCellRenderer));
        setCellRenderer(mTreeCellRenderer);
        addMouseListener(new MyMouseListener());
        setEditable(true);
        setToggleClickCount(0);
    }
}
