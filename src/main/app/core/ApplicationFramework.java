package main.app.core;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class ApplicationFramework {

    private static ApplicationFramework instance;
    protected Gui gui;
    protected MapRepository mapRepository;

    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, MapRepository mapRepository){
        this.gui = gui;
        this.mapRepository = mapRepository;
    }

    public static ApplicationFramework getInstance(){
        if(instance == null)
            instance = new ApplicationFramework();
        return instance;
    }

}
