package main.app.gui.swing.view.mindMapView;

import main.app.mapRepository.implementation.Element;

import java.awt.*;

public abstract class Painter {
    public Painter(Element element) {}
    public abstract void paint(Graphics g);
    public abstract boolean elementAt(Point point);
}
