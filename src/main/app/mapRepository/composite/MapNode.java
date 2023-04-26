package main.app.mapRepository.composite;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MapNode {

    private String name;
    private MapNode parent;

    public MapNode(){};
    public MapNode(String name, MapNode parent){
        this.name = name;
        this.parent = parent;
    }

}
