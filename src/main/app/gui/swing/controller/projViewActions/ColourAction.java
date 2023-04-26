package main.app.gui.swing.controller.projViewActions;

import main.app.gui.swing.controller.MyAbstractAction;
import main.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColourAction extends MyAbstractAction {

    private Color color;

    public ColourAction(){
        putValue(NAME, "set colour");
        putValue(SMALL_ICON, loadIcon("/images/colours.png"));
        putValue(SHORT_DESCRIPTION, "Set colour");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JColorChooser jcc = new JColorChooser();
        color = jcc.showDialog(MainFrame.getInstance(), "Colour Chooser", Color.BLACK);
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) { this.color = color; }
}
