package main.app.core;

import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;

import java.io.File;

public interface Serializer {
    Project openProject(File file);
    MindMap openTemplate(File mapTemplate);
    void save(MapNode mapNode);
}
