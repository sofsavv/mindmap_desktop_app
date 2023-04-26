package main.app.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.awt.*;
import java.io.IOException;

public class ColourSerialization extends JsonSerializer<Color> {
    @Override
    public void serialize(Color color, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("argb");
        jsonGenerator.writeString(Integer.toHexString(color.getRGB()));
        jsonGenerator.writeEndObject();
    }
}
