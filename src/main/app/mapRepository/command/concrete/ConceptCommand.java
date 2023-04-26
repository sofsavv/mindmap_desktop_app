package main.app.mapRepository.command.concrete;

import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;
import main.app.mapRepository.implementation.MindMap;

import java.awt.*;

public class ConceptCommand extends AbstractCommand {

    private String name;
    private Point point;
    private MindMap mindMap;
    private Element concept;

    public ConceptCommand(String name, MindMap mindMap, Point point){
        this.name = name;
        this.mindMap = mindMap;
        this.point = point;
    }

    @Override
    public void doCommand() {
        if(concept == null){
            concept = new Concept(name, mindMap, point, 120, 80);
            ((Concept)concept).setPoint(point);
            ((Concept)concept).setLinkPoints(point);
        }
        mindMap.addChild(concept);
    }

    @Override
    public void undoCommand() {
        mindMap.deleteChild(concept);
    }
}
