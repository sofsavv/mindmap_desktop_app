package main.app;

import main.app.core.ApplicationFramework;
import main.app.core.Gui;
import main.app.core.MapRepository;
import main.app.gui.swing.SwingGui;
import main.app.mapRepository.MapRepImplementation;

public class Main {

    public static void main(String[] args) {
        ApplicationFramework app = ApplicationFramework.getInstance();
        MapRepository mapRepository = new MapRepImplementation();
        Gui gui = new SwingGui();

        app.initialise(gui, mapRepository);
        app.run();
    }

}