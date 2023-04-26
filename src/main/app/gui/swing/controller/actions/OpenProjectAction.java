package main.app.gui.swing.controller.actions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.MainFrame;
import main.app.mapRepository.implementation.Project;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenProjectAction extends MyAbstractAction {
    public OpenProjectAction(){
        putValue(NAME, "Open project");
        putValue(SMALL_ICON, loadIcon("/images/openProject.png"));
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SHORT_DESCRIPTION, "Open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = windowsJFileChooser(new JFileChooser());
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".json","json"));
        if(fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            try{
                File file = fileChooser.getSelectedFile();
                Project p = ApplicationFramework.getInstance().getSerializer().openProject(file);
                MainFrame.getInstance().getMapTree().loadProject(p);
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getProjectExplorer());
                for(int i = 0; i < MainFrame.getInstance().getProjectExplorer().getRowCount(); i++)
                    MainFrame.getInstance().getProjectExplorer().expandRow(i);

            }catch (Exception ex){
                ex.printStackTrace();
                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.UNSUPPORTED_EXTENSION);
            }
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
