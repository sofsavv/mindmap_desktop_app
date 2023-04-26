package main.app.gui.swing.controller.projViewActions;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.MainFrame;
import main.app.gui.swing.view.mindMapView.LinkPaint;
import main.app.gui.swing.view.mindMapView.MindMapView;
import main.app.gui.swing.view.mindMapView.Painter;
import main.app.mapRepository.implementation.Concept;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SetMainConcept extends MyAbstractAction {

    public SetMainConcept(){
        putValue(NAME, "Set main concept");
        putValue(SMALL_ICON, loadIcon("/images/highlight.png"));
        putValue(SHORT_DESCRIPTION, "Set main concept");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MindMapView mapView = MainFrame.getInstance().getCurrProjectView().getCurrMapView();

        if(mapView.getSelectedElements().size() != 1){
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.WRONG_SELECTION);
        }else{
            Concept mainConcept = (Concept) mapView.getSelectedElements().get(0);

            List<Concept> visited = new ArrayList<>();
            Queue<Concept> queue = new ArrayDeque<>();
            List<Concept> left = new ArrayList<>();
            List<Concept> down = new ArrayList<>();
            List<Concept> right = new ArrayList<>();
            List<Concept> up = new ArrayList<>();
            List<List<Concept>> all = new ArrayList<>();

            int radiusHor = 80 + (int)mainConcept.getW();
            int radiusVert = 100 + (int)mainConcept.getH();
            int distanceVert = (int)mainConcept.getH() + 30;
            int distanceHor = (int)mainConcept.getW() + 15;

            List<Concept> children = getChildren(mainConcept, visited);
            setFirstLevel(children, left, down, right, up);

            all.add(left);
            all.add(down);
            all.add(right);
            all.add(up);
            addToQueue(all, queue);

            moveLeft(mainConcept, left, radiusHor, distanceVert);
            moveDown(mainConcept, down, radiusVert, distanceHor);
            moveRight(mainConcept, right, radiusHor, distanceVert);
            moveUp(mainConcept, up, radiusVert, distanceHor);

            List<Concept> left1 = new ArrayList<>();
            List<Concept> down1 = new ArrayList<>();
            List<Concept> right1 = new ArrayList<>();
            List<Concept> up1 = new ArrayList<>();

            while(!queue.isEmpty()){
                Concept curr = queue.poll();
                if(!visited.contains(curr)){
                    List<Concept> newChildren = getChildren(curr, visited);
                    newChildren.removeIf(concept -> concept.equals(mainConcept));
                    queue.addAll(newChildren);
                    move(curr, newChildren, left, down, right, up, radiusHor, radiusVert, distanceHor, distanceVert);
                }
            }
        }
    }

    private void move(Concept parent,  List<Concept> children, List<Concept> left, List<Concept> down, List<Concept> right, List<Concept> up,
                      int radiusHor, int radiusVert, int distanceHor, int distanceVert){
        if(left.contains(parent) && !children.contains(parent)){
            moveLeft(parent, children, radiusHor, distanceVert);
        }else if(down.contains(parent)&& !children.contains(parent)){
            moveDown(parent, children, radiusVert, distanceHor);
        }else if(right.contains(parent)&& !children.contains(parent)){
            moveRight(parent, children, radiusHor, distanceVert);
        }else if(up.contains(parent)&& !children.contains(parent)){
            moveUp(parent, children, radiusVert, distanceHor);
        }
    }

    private void setFirstLevel(List<Concept> children, List<Concept> left, List<Concept> down, List<Concept> right, List<Concept> up){

        int num = children.size()/4;
        int ostatak = children.size()%4;
        int i = 0;

        for(i = 0; i < num; i++)
            left.add(children.get(i));
        for(i = num; i < num*2; i++)
            down.add(children.get(i));
        for(i = num*2; i < num*3; i++)
            right.add(children.get(i));
        for(i = num*3; i < num*4; i++)
            up.add(children.get(i));
        i = num*4;

        // da bi se malo lepse slozilo
        if(ostatak == 1){
            down.add(children.get(i));
        }else if(ostatak == 2){
            left.add(children.get(i++));
            up.add(children.get(i));
        }else if(ostatak == 3){
            left.add(children.get(i++));
            down.add(children.get(i++));
            up.add(children.get(i));
        }
    }
    private void addToQueue( List<List<Concept>> sides , Queue<Concept> queue){
        for(List<Concept> side: sides)
            queue.addAll(side);
    }

    private List<Concept> getChildren(Concept parent, List<Concept> visited){

        MindMapView mapView = MainFrame.getInstance().getCurrProjectView().getCurrMapView();
        List<Concept> children = new ArrayList<>();

        for(Painter painter: mapView.getPainters()){
            if(painter instanceof LinkPaint){

                if((((LinkPaint) painter).getLink().getFrom().equals(parent)) && !visited.contains(parent))
                    children.add(((LinkPaint) painter).getLink().getTo());

                else if((((LinkPaint) painter).getLink().getTo().equals(parent)) && !visited.contains(parent))
                    children.add(((LinkPaint) painter).getLink().getFrom());
            }
        }
        visited.add(parent);
        return children;
    }

    private void moveLeft(Concept parent, List<Concept> children, int radius, int distance){

        for(int i = 0; i < children.size(); i++){
            if(children.size() == 1){
                Point p = new Point(parent.getPoint().x - radius, parent.getPoint().y);
                children.get(i).setPoint(p);
                children.get(i).setLinkPoints(p);
                break;
            }
            if(i == 0){
                Point p = new Point(parent.getPoint().x - radius, parent.getPoint().y - distance/children.size() - 10);
                children.get(i).setPoint(p);
                children.get(i).setLinkPoints(p);
                continue;
            }
            Concept prev = children.get(i-1);
            Point p = new Point(prev.getPoint().x, prev.getPoint().y + distance);
            children.get(i).setPoint(p);
            children.get(i).setLinkPoints(p);
        }

    }
    private void moveDown(Concept parent, List<Concept> children, int radius, int distance){

        for(int i = 0; i < children.size(); i++){
            if(children.size() == 1){
                Point p = new Point(parent.getPoint().x, parent.getPoint().y + radius);
                children.get(i).setPoint(p);
                children.get(i).setLinkPoints(p);
                break;
            }
            if(i == 0){
                Point p = new Point(parent.getPoint().x - distance/children.size(), parent.getPoint().y + radius);
                children.get(i).setPoint(p);
                children.get(i).setLinkPoints(p);
                continue;
            }
            Concept prev = children.get(i-1);
            Point p = new Point(prev.getPoint().x + distance, prev.getPoint().y);
            children.get(i).setPoint(p);
            children.get(i).setLinkPoints(p);
        }

    }

    private void moveRight(Concept parent, List<Concept> children, int radius, int distance){

        for(int i = 0; i < children.size(); i++){
            if(children.size() == 1){
                Point p = new Point(parent.getPoint().x + radius, parent.getPoint().y);
                children.get(i).setPoint(p);
                children.get(i).setLinkPoints(p);
                break;
            }
            if(i == 0){
                Point p = new Point(parent.getPoint().x + radius, parent.getPoint().y - distance/children.size() - 10);
                children.get(i).setPoint(p);
                children.get(i).setLinkPoints(p);
                continue;
            }
            Concept prev = children.get(i-1);
            Point p = new Point(prev.getPoint().x, prev.getPoint().y + distance);
            children.get(i).setPoint(p);
            children.get(i).setLinkPoints(p);
        }

    }

    private void moveUp(Concept parent, List<Concept> children, int radius, int distance){

        for(int i = 0; i < children.size(); i++){
            if(children.size() == 1){
                Point p = new Point(parent.getPoint().x, parent.getPoint().y - radius);
                children.get(i).setPoint(p);
                children.get(i).setLinkPoints(p);
                break;
            }
            if(i == 0){
                Point p = new Point(parent.getPoint().x - distance/children.size(), parent.getPoint().y - radius);
                children.get(i).setPoint(p);
                children.get(i).setLinkPoints(p);
                continue;
            }
            Concept prev = children.get(i-1);
            Point p = new Point(prev.getPoint().x + distance, prev.getPoint().y);
            children.get(i).setPoint(p);
            children.get(i).setLinkPoints(p);
        }
//        System.out.println("usao");
//        left.clear();
//        down.clear();
//        right.clear();
//        up.clear();
//
//        left.addAll(left1);
//        down.addAll(down1);
//        right.addAll(right1);
//        up.addAll(up1);
//
//        left1.clear();
//        down1.clear();
//        right1.clear();
//        up1.clear();

    }
}
