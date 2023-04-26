package main.app.mapRepository.command.concrete;

import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.implementation.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsCommand extends AbstractCommand {

    private Color color, prevColor;
    private int lineWidth, prevLineW;
    private String name , prevName;
    private java.util.List<Element> selected = new ArrayList<>();
    private Element element;

    public SettingsCommand(List<Element> selected, Color color, int lineWidth, Color prevColor, int prevLineW){
        this.selected = selected;
        this.color = color;
        this.lineWidth = lineWidth;
        this.prevColor = prevColor;
        this.prevLineW = prevLineW;
    }

    public SettingsCommand(Element element, Color color, int lineWidth, String name, Color prevColor, int prevLineW, String prevName){
        this.element = element;
        this.color = color;
        this.lineWidth = lineWidth;
        this.name = name;
        this.prevColor = prevColor;
        this.prevLineW = prevLineW;
        this.prevName = prevName;
    }

    @Override
    public void doCommand() {
        if(selected.isEmpty()){
            element.setWidth(lineWidth);
            element.setName(name);
            element.setColor(color);
        }else{
            for(Element el: selected){
                el.setColor(color);
                el.setWidth(lineWidth);
            }
        }
    }

    @Override
    public void undoCommand() {

        if(!selected.isEmpty()){
            for(Element el: selected){
                el.setWidth(prevLineW);
                el.setColor(prevColor);
            }
        }else{
            element.setColor(prevColor);
            element.setWidth(prevLineW);
            element.setName(prevName);
        }

    }
}
