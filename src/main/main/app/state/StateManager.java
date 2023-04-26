package main.app.state;

import main.app.state.concrete.*;

public class StateManager {

    private State currentState;
    private ConceptState conceptState;
    private DeleteElementState deleteElementState;
    private LinkState linkState;
    private SelectState selectState;
    private MoveState moveState;
    private ZoomState zoomState;

    public StateManager(){

        conceptState = new ConceptState();
        deleteElementState = new DeleteElementState();
        linkState = new LinkState();
        selectState = new SelectState();
        moveState = new MoveState();
        zoomState = new ZoomState();
        currentState = conceptState;
    }

    public State getCurrentState(){
        return currentState;
    }
    public void setConceptState(){
        currentState = conceptState;
    }
    public void setDeleteElementState(){
        currentState = deleteElementState;
    }
    public void setLinkState(){ currentState = linkState;}
    public void setSelectState(){
        currentState = selectState;
    }
    public void setMoveState() { currentState = moveState; }
    public void setZoomState() { currentState = zoomState; }

}
