package main.app.mapRepository.command.concrete;

import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;
import main.app.mapRepository.implementation.Link;
import main.app.mapRepository.implementation.MindMap;

import java.util.ArrayList;
import java.util.List;

public class DeleteElementCommand extends AbstractCommand {

    private MindMap mindMap;
    private List<Element> selected = new ArrayList<>();
    private List<Link> links = new ArrayList<>();
    private List<Element> savedElements = new ArrayList<>();

    public DeleteElementCommand(List<Element> selected, MindMap mindMap) {
        this.selected.addAll(selected);
        this.mindMap = mindMap;
    }

    @Override
    public void doCommand() {
        for(int i = 0; i < selected.size(); i++){
            Element element = selected.get(i);
            if(element instanceof Concept){
                links.clear();
                for(MapNode child: mindMap.getChildren()){
                    if(child instanceof Link) {
                        if ((((Link) child).getFrom().equals(element)) || ((Link) child).getTo().equals(element)){
                            savedElements.add((Link) child);
                            links.add((Link) child);
                        }
                    }
                }
                for(Link link: links)
                    mindMap.deleteChild(link);
            }
            savedElements.add(element);
            mindMap.deleteChild(element);
        }
    }

    @Override
    public void undoCommand() {

        for(Element element: savedElements)
            mindMap.addChild(element);

    }
}
