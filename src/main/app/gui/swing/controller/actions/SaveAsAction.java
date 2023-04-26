package main.app.gui.swing.controller.actions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.MainFrame;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAsAction extends MyAbstractAction {
    private final File directory = new File("src/main/resources/templates");

    public SaveAsAction(){
        putValue(NAME, "SaveAs");
        putValue(SHORT_DESCRIPTION, "Save as");
        putValue(SMALL_ICON, loadIcon("/images/saveAs.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = windowsJFileChooser(new JFileChooser());

        if(MainFrame.getInstance().getMapTree().getSelectedNode() != null){
            if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project) {
                Project project = (Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
                File projectFile = null;
                project.setFilePath("src/main/resources/templates");
                if (fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = fileChooser.getSelectedFile();
                    project.setFilePath(projectFile.getPath());
                }else
                    return;
                ApplicationFramework.getInstance().getSerializer().save(project);
                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.SAVED);
                project.setChanged(false);

            }else if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap){ //cuvamo templejt

                MindMap map = (MindMap) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
                directory.mkdir();
                fileChooser.setCurrentDirectory(directory);
                File mapFile = null;
                if (map.isChanged() && (map.getFilePath() == null || map.getFilePath().isEmpty())
                        && fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    mapFile = fileChooser.getSelectedFile();
                    map.setFilePath(mapFile.getPath());
                }else
                    return;
                ApplicationFramework.getInstance().getSerializer().save(map);
                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.SAVED);
                map.setChanged(false);
            }else
                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.INVALID_PATH);
        }else
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.NOTHING_IS_SELECTED);

    }

    public static JFileChooser windowsJFileChooser(JFileChooser chooser){
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            chooser = new JFileChooser();
            UIManager.setLookAndFeel(previousLF);
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return chooser;
    }
}
