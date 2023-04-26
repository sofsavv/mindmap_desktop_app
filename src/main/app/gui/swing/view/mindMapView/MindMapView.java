package main.app.gui.swing.view.mindMapView;

import lombok.Getter;
import lombok.Setter;
import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.projViewActions.MouseController;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.observer.Subscriber;
import main.app.gui.swing.view.MainFrame;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;
import main.app.mapRepository.implementation.Link;
import main.app.mapRepository.implementation.MindMap;
import main.app.messageGenerator.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
@Getter
@Setter

public class MindMapView extends JPanel implements Subscriber {

    private MindMap mindMap;
    private List<Painter> painters = new ArrayList<>();
    private List<Painter> frames;
    private List<Painter> selected;
    private List<Element> selectedElements;
    private Rectangle2D rectangle;
    private Line2D line;

    private AffineTransform transform;
    private double zoomFactor = 1.0D;
    private double prevZoomFactor = 1.0D;
    private double xOffset = 0.0D;
    private double yOffset = 0.0D;


    public MindMapView(MindMap mindMap) {
        super();
        this.mindMap = mindMap;
        mindMap.addSubscriber(this);
        MouseController mouseController = new MouseController(this);
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
        addMouseWheelListener(mouseController);
        for(MapNode child: mindMap.getChildren()) {
            Element element = (Element) child;
            element.addSubscriber(this);
            element.setParent(mindMap);
            setLinks(mindMap);
        }
        init();
    }

    private void findConcepts(MindMap mindMap, Link link){
        for(MapNode el: mindMap.getChildren()){
            if(el instanceof Concept){
                if(link.getFrom().getName().equals(el.getName())){
                    link.setFrom((Concept) el);
                }else if(link.getTo().getName().equals(el.getName())){
                    link.setTo((Concept) el);
                }
            }
        }
    }

    private void setLinks(MindMap mindMap){

        for(MapNode el: mindMap.getChildren()){
            if(el instanceof Link){
                findConcepts(mindMap, (Link)el);
            }
        }
    }

    private void init(){
        setLayout(new BorderLayout());
        MainFrame.getInstance().getCurrProjectView().setCurrMapView(this);
        painters = new ArrayList<>();
        selected = new ArrayList<>();
        frames = new ArrayList<>();
        selectedElements = new ArrayList<>();

        Dimension dim = new Dimension(700, 500);
        setPreferredSize(dim);
        setBackground(Color.WHITE);
        setVisible(true);
        updateUI();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        transform = new AffineTransform();
        transform.translate(xOffset, yOffset);
        transform.scale(zoomFactor, zoomFactor);
        g2D.transform(transform);

        if(rectangle != null){
            g2D.setColor(Color.BLUE);
            g2D.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
            g2D.draw(rectangle);
        }
        if(line != null){
            g2D.setColor(Color.CYAN);
            g2D.draw(line);
        }
        for(Painter p: painters) {
            p.paint(g2D);
        }
        if(!selected.isEmpty())
            for(Painter n: frames)
                n.paint(g2D);
    }

    @Override
    public void updateView(MyNotification notification, Object o) {

        if(notification.equals(MyNotification.ADD_CHILD)){
            if(o instanceof Concept){
                Rectangle2D rect = new Rectangle(((Concept) o).getPoint().x, ((Concept) o).getPoint().y,
                        (int)((Concept) o).getW(), (int)((Concept) o).getH());
                for(Painter p: this.getPainters()){
                    if(p instanceof ConceptPaint && ((ConceptPaint)p).getShape() != null && ((ConceptPaint)p).getShape().intersects(rect)){
                        ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.OVERLAP);
                        mindMap.getCommandManager().undoCommand();
                        return;
                    }
                }
                Painter painter = new ConceptPaint((Concept)o);
                painters.add(painter);
                ((Concept)o).addSubscriber(this);
            }else if(o instanceof Link){
                Painter painter = new LinkPaint((Link)o);
                painters.add(painter);
                ((Link)o).addSubscriber(this);
            }
        }else if(notification.equals(MyNotification.DELETE_CHILD)){
            ListIterator<Painter> painterIter = getPainters().listIterator();
            while (painterIter.hasNext()){
                Painter p = painterIter.next();
                if(o instanceof Concept && p instanceof ConceptPaint
                        && ((ConceptPaint) p).getConcept().equals(((Concept) o))){
                    painterIter.remove();
                }else if(o instanceof Link && p instanceof LinkPaint
                        && ((LinkPaint) p).getLink().equals(((Link) o))){
                    painterIter.remove();
                }
            }
            unselect();
        }else if(notification.equals(MyNotification.OPENED)){
            painters.clear();
            for(MapNode child: getMindMap().getChildren()){
                if(child instanceof Concept){
                    Painter painter = new ConceptPaint((Element) child);
                    painters.add(painter);
                }else if(child instanceof Link){
                    Painter painter = new LinkPaint((Element) child);
                    painters.add(painter);
                }
            }
        }
        repaint();
    }

    public void unselect(){
        this.selectedElements.clear();
        this.selected.clear();
        this.frames.clear();
    }
    public void setCurrent(){ MainFrame.getInstance().getCurrProjectView().setCurrMapView(this); }

    @Override
    public void updateMessage(Message message) {}
    @Override
    public void updateInfo(Message message) {}
    @Override
    public void update(MyNotification notification) {}
}
