package main.app.gui.swing.tree.controller;

import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.mapRepository.implementation.Project;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem selected = (MapTreeItem) path.getLastPathComponent();

        if(selected.getMapNode() instanceof Project){
            System.out.println("Autor projekta " + ((Project) selected.getMapNode()).getAuthor());
        }

    }
}
