package main.app.gui.swing.view.dialog;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Getter
@Setter

public class AuthorDialog extends JDialog {

    private JTextField tf;
    private String newAuthor;
    public AuthorDialog(JFrame parent, Icon icon){

        super(parent, "New Author", true);
        setLayout(new BorderLayout());
        setSize(350,150);
        setResizable(false);
        setLocationRelativeTo(parent);
        Dimension dim = new Dimension(140,25);

        JLabel authorName = new JLabel("Enter author");
        tf = new JTextField();
        tf.setPreferredSize(dim);

        JLabel myIcon = new JLabel(icon);

        JButton save = new JButton("Save");
        save.setPreferredSize(new Dimension(63,25));
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newAuthor = tf.getText();
                System.out.println("autor " + newAuthor);
                setVisible(false);
            }
        };
        save.addMouseListener(mouseListener);
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    newAuthor = tf.getText();
                    System.out.println("Novi autor " + newAuthor);
                    setVisible(false);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        };
        save.addKeyListener(keyListener);
        tf.addKeyListener(keyListener);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(myIcon);
        panel.add(authorName);
        panel.add(tf);
        panel.add(save);
        add(panel);
        pack();
    }

    public  String getAuthor() {
        return newAuthor;
    }

}
