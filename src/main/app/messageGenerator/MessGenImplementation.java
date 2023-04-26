package main.app.messageGenerator;

import main.app.gui.swing.observer.MyNotification;
import main.app.gui.swing.observer.Subscriber;

import java.util.LinkedList;
import java.util.List;

public class MessGenImplementation implements MessageGenerator{
    private List<Subscriber> subscribers;

    public MessGenImplementation() {}

    @Override
    public void generate(MyNotification type) {

        if(type.equals(MyNotification.CANNOT_RENAME))
            notifyMessage(new Message("Cannot rename selected item", MyNotification.CANNOT_RENAME));

        else if(type.equals(MyNotification.CANNOT_ADD_CHILD))
            notifyMessage(new Message("Cannot add child", MyNotification.CANNOT_ADD_CHILD));

        else if(type.equals(MyNotification.INVALID_PATH))
            notifyMessage(new Message("Invalid path", MyNotification.INVALID_PATH));

        else if(type.equals(MyNotification.CANNOT_DELETE_NODE))
            notifyMessage(new Message("This node cannot be deleted", MyNotification.CANNOT_DELETE_NODE));

        else if(type.equals(MyNotification.CANNOT_ADD_AUTHOR))
            notifyMessage(new Message("Author can be added only to Project", MyNotification.CANNOT_ADD_AUTHOR));

        else if(type.equals(MyNotification.CANNOT_EDIT_EXPLORER))
            notifyMessage(new Message("Project Explorer cannot be renamed", MyNotification.CANNOT_EDIT_EXPLORER));

        else if(type.equals(MyNotification.OVERLAP))
            notifyMessage(new Message("Elements overlap", MyNotification.OVERLAP));

        else if(type.equals(MyNotification.EXISTS))
            notifyMessage(new Message("Concept already exists", MyNotification.EXISTS));

        else if(type.equals(MyNotification.NOTHING_IS_SELECTED))
            notifyMessage(new Message("Nothing is selected", MyNotification.NOTHING_IS_SELECTED));

        else if(type.equals(MyNotification.UNSUPPORTED_EXTENSION))
            notifyMessage(new Message("Unsupported file format", MyNotification.UNSUPPORTED_EXTENSION));

        else if(type.equals(MyNotification.CANNOT_SAVE))
            notifyMessage(new Message("Cannot save this file", MyNotification.CANNOT_SAVE));

        else if(type.equals(MyNotification.SAVED))
            notifyInfo(new Message("Successfully saved!", MyNotification.SAVED));

        else if(type.equals(MyNotification.NO_PROJ_VIEW))
            notifyInfo(new Message("You need to open project for choosing template for mind map", MyNotification.NO_PROJ_VIEW));

        else if(type.equals(MyNotification.WRONG_SELECTION)){
            notifyInfo(new Message("Only one concept can be centered!\n Please select one concept!", MyNotification.WRONG_SELECTION));
        }
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(subscriber == null)
            return;
        if(this.subscribers == null)
            this.subscribers = new LinkedList<>();
        if(this.subscribers.contains(subscriber))
            return;
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        if(subscriber == null || this.subscribers == null || !this.subscribers.contains(subscriber))
            return;
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notify(MyNotification notification) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for(Subscriber subscriber : subscribers)
            subscriber.update(notification);
    }

    @Override
    public void notifyMessage(Message message) {
        if(message == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for(Subscriber subscriber : subscribers)
            subscriber.updateMessage(message);
    }

    @Override
    public void notifyInfo(Message message) {
        if(message == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for(Subscriber subscriber : subscribers)
            subscriber.updateInfo(message);
    }

    @Override
    public void notifyView(MyNotification notification, Object o) {}
}
