package main.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.observer.Subscriber;
import main.app.gui.swing.tree.model.MapTreeItem;
import main.app.gui.swing.view.mindMapView.MindMapView;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;
import main.app.mapRepository.implementation.ProjectExplorer;
import main.app.messageGenerator.Message;
import main.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ProjectView extends JPanel implements Subscriber {

    private Project project;
    private JPanel infoPanel;
    private JLabel projectName;
    private JLabel projectAuthor;
    private JTabbedPane tabbedPane;

    private List<MindMapView> mapViewList;
    private MindMapView currMapView;
    private StateManager stateManager;

    public ProjectView(Project project){
        super();
        this.project = project;
        stateManager = new StateManager();
        mapViewList = new ArrayList<>();
        init();
    }

    private void init(){
        setLayout(new BorderLayout());
        Dimension dim = MainFrame.getInstance().getSize();
        Dimension size = new Dimension(650, 700);
        setVisible(true);
        setMaximumSize(dim);
        infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        projectAuthor = new JLabel("");
        projectAuthor.setFont(new Font("Calibri", Font.ITALIC, 14));
        projectName = new JLabel("");
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.setPreferredSize(size);
        infoPanel.add(projectName);
        infoPanel.add(projectAuthor);
        add(infoPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.WEST);
    }


    private void makeTabbedPane(Project proj){
        for(MapNode child: proj.getChildren()){
            MindMap mindMap = (MindMap) child;
            MindMapView mindMapView = new MindMapView(mindMap);
            proj.addSubscriber(mindMapView);
            ((MindMap) child).addSubscriber(mindMapView);
            ((MindMap) child).addSubscriber(this);
            mindMapView.setVisible(true);
            tabbedPane.add(child.getName(), mindMapView);
            mapViewList.add(mindMapView);
        }
    }

    @Override
    public void update(MyNotification notification) {

        MapTreeItem item = MainFrame.getInstance().getMapTree().getSelectedNode();

        if(notification.equals(MyNotification.NEW)){

            MainFrame.getInstance().getProjectViewList().add(this);
            MainFrame.getInstance().setCurrProjectView(this);
            project.addSubscriber(this);
            ((ProjectExplorer)this.project.getParent()).addSubscriber(this);
            projectName.setText(project.getName());
            projectAuthor.setFont(new Font("Calibri", Font.ITALIC, 14));
            projectAuthor.setText(project.getAuthor());
            infoPanel.add(projectName);
            infoPanel.add(projectAuthor);
            add(infoPanel, BorderLayout.NORTH);
            makeTabbedPane(project);
            if(!this.getMapViewList().isEmpty())
                currMapView = this.getMapViewList().get(0);

            add(tabbedPane, BorderLayout.CENTER);
            MainFrame.getInstance().getSplitPane().setRightComponent(this);
            repaint();

        }else if(notification.equals(MyNotification.SHOW)){
            MainFrame.getInstance().setCurrProjectView(this);
            MainFrame.getInstance().getSplitPane().setRightComponent(this);

            if(!mapViewList.isEmpty()){
                int i = tabbedPane.getSelectedIndex();
                setCurrMapView(mapViewList.get(i));
            }
        } else if(notification.equals(MyNotification.ADD_CHILD)){
            if(item != null && item.getMapNode() instanceof Project) {
                for (MapNode child : project.getChildren()) {
                    if (tabbedPane.indexOfTab(String.valueOf(child)) == -1) {
                        MindMapView mapView = new MindMapView((MindMap) child);
                        mapViewList.add(mapView);
                        tabbedPane.addTab(child.getName(), mapView);
                        mapViewList.add(mapView);

                        ((MindMap) child).addSubscriber(this);
                        int i = tabbedPane.getSelectedIndex();
                        setCurrMapView(mapViewList.get(i));
                    }
                }
            }

        }else if(notification.equals(MyNotification.DELETE_CHILD)){

            tabbedPane.removeTabAt(tabbedPane.indexOfTab(String.valueOf(item)));
            this.mapViewList.removeIf(mapView -> item.getMapNode().getName().equals(mapView.getName()));
            ((MindMap)item.getMapNode()).removeSubscriber(this);

        }else if(notification.equals(MyNotification.RENAME)){

            if(item.getMapNode() instanceof Project)
                projectName.setText(project.getName());

            if(item.getMapNode() instanceof MindMap){

                int totalTabs = tabbedPane.getTabCount();
                for (int i = 0; i < totalTabs; i++) {
                    if(!this.project.getChildren().get(i).getName().equals(tabbedPane.getTitleAt(i))){
                        tabbedPane.setTitleAt(i, item.getMapNode().getName());
                        repaint();
                    }
                }
            }

        }else if(notification.equals(MyNotification.AUTHOR)){
            projectAuthor.setText(project.getAuthor());
        }
    }

    @Override
    public void updateMessage(Message message) {}
    @Override
    public void updateInfo(Message message) {}

    @Override
    public void updateView(MyNotification notification, Object o) {

        MapTreeItem item = MainFrame.getInstance().getMapTree().getSelectedNode();

        if(notification.equals(MyNotification.DELETE)){
            if(project.equals((Project) o)){
                MainFrame.getInstance().getProjectViewList().remove(this);
                tabbedPane.removeAll();
                projectAuthor.setText("");
                projectName.setText("");
                ((Project)item.getMapNode()).removeSubscriber(this);
            }
        }
    }

    public void setProject(Project project) {
        update(MyNotification.NEW);
    }

    public void startConceptState(){this.stateManager.setConceptState();}
    public void startDeleteState(){this.stateManager.setDeleteElementState();}
    public void startLinkState(){this.stateManager.setLinkState();}
    public void startSelectState(){this.stateManager.setSelectState();}
    public void startMoveState(){this.stateManager.setMoveState();}
    public void startZoomState(){this.stateManager.setZoomState();}
    public StateManager getStateManager() { return stateManager; }
}
