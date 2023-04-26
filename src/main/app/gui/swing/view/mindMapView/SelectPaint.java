package main.app.gui.swing.view.mindMapView;

import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;
import main.app.mapRepository.implementation.Link;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SelectPaint extends Painter{
    private Shape shape;
    private Element element;

    public SelectPaint(Element element) {
        super(element);
        this.element = element;
    }
    @Override
    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLUE);
        g2D.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        if(element instanceof Concept){
            shape = new Rectangle2D.Double(((Concept)element).getPoint().x, ((Concept)element).getPoint().y,
                    ((Concept)element).getW(), ((Concept)element).getH());
            g2D.draw(shape);

        }else if(element instanceof Link){
            Rectangle rectangle = new Rectangle();
            rectangle.setFrameFromDiagonal(((Link)element).getBestFrom(), ((Link)element).getBestTo());
            shape = new Rectangle2D.Double(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            g2D.draw(shape);
        }
    }
    @Override
    public boolean elementAt(Point point) {
        if(shape.contains(point))
            return true;
        return false;
    }
}
