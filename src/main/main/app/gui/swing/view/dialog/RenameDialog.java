package main.app.gui.swing.view.dialog;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
@Getter
public class RenameDialog extends JDialog {

    private JTextField tf;
    private String newName;

    public RenameDialog(JFrame parent, Icon icon){

        super(parent, "Rename", true);
        setLayout(new BorderLayout());
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(parent);
        Dimension dim = new Dimension(100,20);
        JLabel myIcon = new JLabel(icon);
        JLabel name = new JLabel("Enter name");
        tf = new JTextField();
        tf.setPreferredSize(dim);

        JButton save = new JButton("Save");
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    newName = tf.getText();
                    setVisible(false);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        };
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newName = tf.getText();
                setVisible(false);
            }
        };
        save.addMouseListener(mouseListener);
        tf.addKeyListener(keyListener);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(myIcon);
        panel.add(name);
        panel.add(tf);
        panel.add(save);
        add(panel);
        pack();
    }
    public String getNewName(){
        return newName;
    }


}
