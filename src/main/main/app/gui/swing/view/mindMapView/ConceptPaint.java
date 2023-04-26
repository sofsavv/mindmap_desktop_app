package main.app.gui.swing.view.mindMapView;

import lombok.Getter;
import lombok.Setter;
import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;

import java.awt.*;
import java.awt.geom.Ellipse2D;
@Getter
@Setter
public class ConceptPaint extends Painter{

    private Shape shape;
    private Concept concept;

    public ConceptPaint(Element element){
        super(element);
        concept = (Concept) element;
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.WHITE);
        g2D.setStroke(new BasicStroke(concept.getWidth()));
        shape = new Ellipse2D.Double(concept.getPoint().x, concept.getPoint().y, concept.getW(), concept.getH());
        FontMetrics metrics = g2D.getFontMetrics();
        g2D.draw(shape);
        g2D.fill(shape);
        g2D.setColor(concept.getColor());

        if(concept.getName() != null){

            if (metrics.stringWidth(concept.getName()) > shape.getBounds().width) {
                concept.setW(metrics.stringWidth(concept.getName() + 3));
                concept.setH(concept.getH() + 2);
            }
            int x = concept.getPoint().x + (shape.getBounds().width - metrics.stringWidth(concept.getName())) / 2;
            int y = concept.getPoint().y + (shape.getBounds().height / 2 - metrics.getHeight() / 2) + metrics.getAscent();

            shape = new Ellipse2D.Double(concept.getPoint().x, concept.getPoint().y, concept.getW(), concept.getH());
            concept.setLinkPoints(concept.getPoint());
            g2D.draw(shape);
            g2D.drawString(concept.getName(), x, y);
        }
    }

    @Override
    public boolean elementAt(Point point) {
        if(shape.contains(point))
            return true;
        return false;
    }
}
