package main.app.gui.swing.view;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import main.app.core.ApplicationFramework;
import main.app.gui.swing.controller.ActionManager;
import main.app.gui.swing.tree.MapTree;
import main.app.gui.swing.tree.MapTreeImplementation;
import main.app.gui.swing.tree.view.MapTreeView;
import main.app.mapRepository.implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private ActionManager actionManager;
    private MapTree mapTree;
    private MapTreeView projectExplorer;
    @Getter(AccessLevel.NONE)
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JToolBar rightToolBar;
    private JSplitPane splitPane;
    private Project project;
    private ProjectView currProjectView;
    private List<ProjectView> projectViewList;

    private MainFrame(){}

    private void init(){
        actionManager = new ActionManager();
        mapTree = new MapTreeImplementation();
        projectViewList = new ArrayList<>();
        projectExplorer = mapTree.generateTree(ApplicationFramework.getInstance().getMapRepository().getProjectExplorer());
        initGui(projectExplorer);
    }

    private void initGui(MapTreeView projectExplorer){

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/2, screenHeight/2+50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("GeRuMap");

        menuBar = new MyMenuBar();
        setJMenuBar(menuBar);
        toolBar = new MyToolbar();
        add(toolBar, BorderLayout.NORTH);
        rightToolBar = new RightToolbar();
        add(rightToolBar, BorderLayout.EAST);

        currProjectView = new ProjectView(project);

        JScrollPane scrollPane = new JScrollPane(projectExplorer);
        scrollPane.setMinimumSize(new Dimension(200, 150));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, currProjectView);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);
    }

    public ActionManager getActionManager(){
        return actionManager;
    }

    public static MainFrame getInstance(){
        if(instance == null) {
            instance = new MainFrame();
            instance.init();
        }
        return instance;
    }

    public MapTree getMapTree() {
        return mapTree;
    }
}
