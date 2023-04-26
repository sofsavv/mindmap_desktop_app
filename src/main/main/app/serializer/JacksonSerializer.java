package main.app.serializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import main.app.core.ApplicationFramework;
import main.app.core.Serializer;
import main.app.gui.swing.observer.MyNotification;
import main.app.mapRepository.composite.MapNode;
import main.app.mapRepository.implementation.MindMap;
import main.app.mapRepository.implementation.Project;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JacksonSerializer implements Serializer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SimpleModule module = new SimpleModule();

    public JacksonSerializer(){
        module.addSerializer(Color.class, new ColourSerialization());
        module.addDeserializer(Color.class, new ColourDeserialization());
        objectMapper.registerModule(module);
    }

    @Override
    public Project openProject(File file) {
        try{
            FileReader fileReader = new FileReader(file);
            return objectMapper.readValue(fileReader, Project.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MindMap openTemplate(File mapTemplate) {
        try{
            FileReader fileReader = new FileReader(mapTemplate);
            return objectMapper.readValue(fileReader, MindMap.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(MapNode mapNode) {
        try{
            objectMapper.setVisibilityChecker(objectMapper.getSerializationConfig()
                    .getDefaultVisibilityChecker()
                    .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                    .withGetterVisibility(JsonAutoDetect.Visibility.NONE));
            if(mapNode instanceof Project){
                Project project = (Project) mapNode;
                FileWriter writer = new FileWriter(project.getFilePath());
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, project);
            }else{
                MindMap mindMap = (MindMap) mapNode;
                FileWriter writer = new FileWriter(mindMap.getFilePath());
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, mindMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
            ApplicationFramework.getInstance().getMessageGenerator().generate(MyNotification.CANNOT_SAVE);
        }
    }


}
