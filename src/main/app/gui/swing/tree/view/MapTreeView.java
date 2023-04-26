package main.app.gui.swing.tree.view;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeView extends JTree {

    public MapTreeView(DefaultTreeModel defaultTreeModel){
        setModel(defaultTreeModel);
        MapTreeCellRenderer mTreeCellRenderer = new MapTreeCellRenderer();
        setEditable(true);
        setToggleClickCount(0);
    }
}
