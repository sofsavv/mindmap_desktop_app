package main.app.gui.swing.controller.actions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.view.MainFrame;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenTemplateAction extends MyAbstractAction {
    public OpenTemplateAction(){
        putValue(NAME, "Open template");
        putValue(SMALL_ICON, loadIcon("/images/template.png"));
        putValue(SHORT_DESCRIPTION, "Open template");
    }
    private final File directory = new File("src/main/resources/templates");

    @Override
    public void actionPerformed(ActionEvent e) {

        if(MainFrame.getInstance().getMapTree().getSelectedNode() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.NOTHING_IS_SELECTED);
            return;
        }
        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project) {
            directory.mkdir();
            JFileChooser fileChooser = windowsJFileChooser(new JFileChooser());
            fileChooser.setCurrentDirectory(directory);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".json", "json"));
            if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
                if(MainFrame.getInstance().getCurrProjectView().getProject() != null
                        && selected.getMapNode().getName().equals(MainFrame.getInstance().getCurrProjectView().getProject().getName())){
                    try {
                        File file = fileChooser.getSelectedFile();

                        MindMap mindMap = ApplicationFramework.getInstance().getSerializer().openTemplate(file);
                        MainFrame.getInstance().getMapTree().loadMap(mindMap, selected);
                        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getProjectExplorer());
                        for (int i = 0; i < MainFrame.getInstance().getProjectExplorer().getRowCount(); i++)
                            MainFrame.getInstance().getProjectExplorer().expandRow(i);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.UNSUPPORTED_EXTENSION);
                    }
                }else
                    ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.NO_PROJ_VIEW);
            }
        }else{
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.INVALID_PATH);
        }

    }
    public static JFileChooser windowsJFileChooser(JFileChooser chooser){
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            chooser = new JFileChooser();
            UIManager.setLookAndFeel(previousLF);
        } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
        return chooser;
    }
}
