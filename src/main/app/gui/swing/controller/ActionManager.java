package main.app.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;
import main.app.gui.swing.controller.actions.*;
import main.app.gui.swing.controller.projViewActions.*;

@Getter
@Setter
public class ActionManager {

    private EditAction editAction;
    private NewAction newAction;
    private RemoveAction removeAction;
    private InfoAction infoAction;
    private SetAuthorAction setAuthor;

    private UndoAction undoAction;
    private RedoAction redoAction;

    private OpenTemplateAction openTemplateAction;
    private SaveAction saveAction;
    private SaveAsAction saveAsAction;
    private OpenProjectAction openProjectAction;
    private ImageExportAction imageExport;

    private ConceptAction conceptAction;
    private LinkAction linkAction;
    private SelectAction selectAction;
    private SettingsAction settingsAction;
    private MoveAction moveAction;
    private ZoomAction zoomAction;
    private ColourAction colourAction;
    private DeleteElementAction deleteElementAction;
    private SetMainConcept mainConcept;

    public ActionManager(){
        initializeAction();
    }

    private void initializeAction(){

        editAction = new EditAction();
        newAction = new NewAction();
        removeAction = new RemoveAction();
        infoAction = new InfoAction();
        setAuthor = new SetAuthorAction();

        redoAction = new RedoAction();
        undoAction = new UndoAction();

        openProjectAction = new OpenProjectAction();
        openTemplateAction = new OpenTemplateAction();
        saveAction = new SaveAction();
        saveAsAction = new SaveAsAction();
        imageExport = new ImageExportAction();

        conceptAction = new ConceptAction();
        linkAction = new LinkAction();
        moveAction = new MoveAction();
        deleteElementAction = new DeleteElementAction();
        selectAction = new SelectAction();
        settingsAction = new SettingsAction();
        zoomAction = new ZoomAction();
        colourAction = new ColourAction();
        mainConcept = new SetMainConcept();
    }
}
