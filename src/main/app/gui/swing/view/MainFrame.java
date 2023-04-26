package main.app.gui.swing.view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private MainFrame(){};

    private void initialise(){
        initGui();
    }

    private void initGui(){

    }

    public static MainFrame getInstance(){
        if(instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }
}
