package main.app.gui.swing.view.dialog;

import main.app.core.ApplicationFramework;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.view.MainFrame;
import main.app.mapRepository.command.AbstractCommand;
import main.app.mapRepository.command.concrete.SettingsCommand;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.implementation.Concept;
import main.app.mapRepository.implementation.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class SettingsDialog extends JDialog {

    private JLabel lblColour;
    private JLabel lblLineWidth;
    private JLabel lblText;
    private JComboBox<Integer> cmbLineWidth;
    private JTextField tfText;
    private JButton btnSave;
    private JButton btnCancel;
    private List<Element> selectedElements = new ArrayList<>();

    private String currName;
    private String newName;
    private int lineWidth;
    private Color color;
    private boolean selected;

    private String prevName;
    private Color prevColor;
    private int prevLineW;

    public SettingsDialog(JFrame parent, Icon editText, Icon lineWidthI){

        super(parent, "Settings", true);
        setLayout(new BorderLayout());
        setSize(330,230);
        setResizable(false);
        setLocationRelativeTo(parent);
        Dimension dimension = new Dimension(80, 25);
        lblColour = new JLabel(" Choose different colour");
        lblText = new JLabel("Rename element");
        JLabel picEditText = new JLabel(editText);
        picEditText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tfText = new JTextField();

        lblLineWidth = new JLabel("Choose line width");
        JLabel picLineWidth = new JLabel(lineWidthI);
        picLineWidth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cmbLineWidth = new JComboBox<>();
        cmbLineWidth.setBackground(Color.WHITE);
        cmbLineWidth.addItem(1);
        cmbLineWidth.addItem(2);
        cmbLineWidth.addItem(3);
        cmbLineWidth.addItem(4);
        cmbLineWidth.addItem(5);
        cmbLineWidth.addItem(6);
        cmbLineWidth.addItem(7);
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        JButton colourButton = new JButton();
        colourButton.setAction(MainFrame.getInstance().getActionManager().getColourAction());
        colourButton.setBackground(Color.WHITE);

        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel linePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textPane.setBackground(Color.lightGray);
        linePane.setBackground(Color.lightGray);
        textPane.add(picEditText);
        textPane.add(lblText);
        linePane.add(picLineWidth);
        linePane.add(lblLineWidth);

        btnSave.setPreferredSize(dimension);
        btnCancel.setPreferredSize(dimension);
        tfText.setPreferredSize(new Dimension(100, 30));

        GridLayout gridLayout = new GridLayout(3,3);
        gridLayout.setHgap(10);
        gridLayout.setVgap(15);
        JPanel settings = new JPanel(gridLayout);
        settings.add(lblColour);
        settings.add(colourButton);
        settings.add(textPane);
        settings.add(tfText);
        settings.add(linePane);
        settings.add(cmbLineWidth);
        settings.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(btnSave);
        buttons.add(btnCancel);
        settings.setBackground(Color.lightGray);
        buttons.setBackground(Color.lightGray);

        selected = MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements().isEmpty();
        MainFrame.getInstance().getActionManager().getColourAction().setColor(MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements().get(0).getColor());
        lineWidth = MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements().get(0).getWidth();

        if(MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements().size() > 1){
            tfText.setText("/");
            tfText.setEditable(false);
            cmbLineWidth.setSelectedItem(MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements().get(0).getWidth());
        }else if(!selected){
            currName = MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements().get(0).getName();
            cmbLineWidth.setSelectedItem(MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements().get(0).getWidth());
            tfText.setText(currName);
            tfText.setEditable(true);
        }

        prevLineW = lineWidth;
        prevColor = MainFrame.getInstance().getActionManager().getColourAction().getColor();
        prevName = currName;

        MouseListener saveClick = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tfText.getText().equals("/") && !selected){

                    color = MainFrame.getInstance().getActionManager().getColourAction().getColor();
                    lineWidth = (int) cmbLineWidth.getSelectedItem();
                    selectedElements.addAll(MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements());

                    AbstractCommand command  = new SettingsCommand(selectedElements , color, lineWidth, prevColor, prevLineW);
                    MainFrame.getInstance().getCurrProjectView().getCurrMapView().getMindMap().getCommandManager().addCommand(command);

                } else if(!selected){

                    newName = tfText.getText();
                    lineWidth = (int) cmbLineWidth.getSelectedItem();
                    Element element = MainFrame.getInstance().getCurrProjectView().getCurrMapView().getSelectedElements().get(0);

                    for(MapNode mapNode: MainFrame.getInstance().getCurrProjectView().getCurrMapView().getMindMap().getChildren()){
                        if(mapNode instanceof Concept){
                            if(!element.getName().equals(newName) && mapNode.getName().equals(newName)) {
                                newName = currName;
                                tfText.setText(newName);
                                ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.EXISTS);
                                return;
                            }
                        }
                    }
                    color = MainFrame.getInstance().getActionManager().getColourAction().getColor();
                    AbstractCommand command = new SettingsCommand(element, color, lineWidth, newName, prevColor, prevLineW, prevName);
                    MainFrame.getInstance().getCurrProjectView().getCurrMapView().getMindMap().getCommandManager().addCommand(command);

                }else
                    ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.NOTHING_IS_SELECTED);

                setVisible(false);
            }
        };

        MouseListener cancelClick = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                dispose();
            }
        };

        btnSave.addMouseListener(saveClick);
        btnCancel.addMouseListener(cancelClick);

        add(settings, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    public boolean isSelected() {
        return selected;
    }
}
