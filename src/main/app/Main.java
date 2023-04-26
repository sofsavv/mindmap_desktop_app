package main.app;

import main.app.core.ApplicationFramework;
import main.app.core.Gui;
import main.app.core.MapRepository;
import main.app.core.Serializer;
import main.app.errorLogger.ConsoleLogger;
import main.app.errorLogger.ErrorLogger;
import main.app.gui.swing.SwingGui;
import main.app.mapRepository.MapRepImplementation;
import main.app.messageGenerator.MessGenImplementation;
import main.app.messageGenerator.MessageGenerator;
import main.app.serializer.JacksonSerializer;

public class Main {

    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        MapRepository mapRepository = new MapRepImplementation();
        MessageGenerator messageGenerator = new MessGenImplementation();

        ErrorLogger errorLogger = new ConsoleLogger();
        Gui gui = new SwingGui();
        Serializer json = new JacksonSerializer();

        messageGenerator.addSubscriber(errorLogger);
        messageGenerator.addSubscriber(gui);

        appCore.initialise(gui, mapRepository, messageGenerator, errorLogger, json);
        appCore.run();
    }

}