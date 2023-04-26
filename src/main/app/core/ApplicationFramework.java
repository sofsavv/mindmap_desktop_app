package main.app.core;

import lombok.Getter;
import lombok.Setter;
import main.app.errorLogger.ErrorLogger;
import main.app.messageGenerator.MessageGenerator;

@Getter
@Setter

public class ApplicationFramework {

    private static ApplicationFramework instance;
    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator messageGenerator;
    protected ErrorLogger errorLogger;
    protected Serializer serializer;

    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, MapRepository mapRepository, MessageGenerator messageGenerator, ErrorLogger errorLogger, Serializer serializer){
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.messageGenerator = messageGenerator;;
        this.errorLogger = errorLogger;
        this.serializer = serializer;
    }

    public static ApplicationFramework getInstance(){
        if(instance == null)
            instance = new ApplicationFramework();
        return instance;
    }

}
