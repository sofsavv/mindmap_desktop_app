package main.app.mapRepository.command.concrete;

import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveElementCommand extends AbstractCommand {
    private List<Element> selected = new ArrayList<>();
    private List<Point> prevPoints = new ArrayList<>();
    private List<Point> newPoints = new ArrayList<>();

    public MoveElementCommand(List<Element> selected, List<Point> prevPoints, List<Point> newPoints){
        this.selected.addAll(selected);
        this.prevPoints.addAll(prevPoints);
        this.newPoints.addAll(newPoints);
    }

    @Override
    public void doCommand() {
        int i = 0;
        for(Element concept: selected){
            if(concept instanceof Concept){
                ((Concept) concept).setPoint(newPoints.get(i));
                ((Concept) concept).setLinkPoints(newPoints.get(i));
                i++;
            }
        }
    }

    @Override
    public void undoCommand() {
        int i = 0;
        for(Element concept: selected){
            if(concept instanceof Concept){
                ((Concept) concept).setPoint(prevPoints.get(i));
                ((Concept) concept).setLinkPoints(prevPoints.get(i));
                i++;
            }
        }
    }
}
