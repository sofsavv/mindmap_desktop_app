package main.app.mapRepository.command.concrete;

import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;
import main.app.mapRepository.implementation.Link;
import main.app.mapRepository.implementation.MindMap;

public class LinkCommand extends AbstractCommand {

    private Concept cFrom, cTo;
    private MindMap mindMap;
    private Element link;

    public LinkCommand(MindMap mindMap, Concept cFrom, Concept cTo) {
        this.mindMap = mindMap;
        this.cFrom = cFrom;
        this.cTo = cTo;
    }

    @Override
    public void doCommand() {

        if(link == null){
            link = new Link("link_" + cFrom.getName() + "_" + cTo.getName(), mindMap, cFrom, cTo);
            for (MapNode node : mindMap.getChildren()) {
                if (node instanceof Link && node.equals(link))
                    return;
            }
            if(cFrom.equals(cTo))
                return;
        }
        mindMap.addChild(link);
    }

    @Override
    public void undoCommand() {
        mindMap.deleteChild(link);
    }

}
