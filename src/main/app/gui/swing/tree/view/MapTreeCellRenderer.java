package main.app.gui.swing.tree.view;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MapTreeCellRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus){

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        URL imageURL = null;
//
//        if (((MapTreeItem)value).getMapNode() instanceof ProjectExplorer) {
//            imageURL = getClass().getResource("/images/project-explorer.png");
//        }
//        else if (((MapTreeItem)value).getMapNode() instanceof Project) {
//            imageURL = getClass().getResource("/images/folder.png");
//        }
//        else if(((MapTreeItem)value).getMapNode() instanceof MindMap){
//            imageURL = getClass().getResource("/images/mindmap.png");
//        }
//        else if(((MapTreeItem)value).getMapNode() instanceof Element){
//            imageURL = getClass().getResource("/images/element.png");
//        }

        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;

    }
}
