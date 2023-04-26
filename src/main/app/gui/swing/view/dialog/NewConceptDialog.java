package main.app.gui.swing.view.dialog;

import lombok.Getter;
import main.app.core.ApplicationFramework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@Getter
public class NewConceptDialog extends JDialog{

    private JTextField tfText;
    private String conceptName;
    private boolean cant_add = true;

    public NewConceptDialog(JFrame parent){

        super(parent, "New element", true);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setResizable(false);
        setLocationRelativeTo(parent);
        Dimension dim = new Dimension(100,20);
        JLabel name = new JLabel("New concept: ");
        tfText = new JTextField();
        tfText.setPreferredSize(dim);

        JButton save = new JButton("Save");
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                conceptName = tfText.getText();
                if(conceptName.equals("")){
                    cant_add = true;
//                    ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.EXISTS);
                }else{
                    cant_add = false;
                    setVisible(false);
                }
            }
        };
        save.addMouseListener(mouseListener);
        add(name);
        add(tfText);
        add(save);
        pack();
    }

    public boolean isCant_add() {
        return cant_add;
    }
}
