package main.app.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.awt.*;
import java.io.IOException;

public class ColourDeserialization extends JsonDeserializer<Color> {

    @Override
    public Color deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TreeNode root = jsonParser.getCodec().readTree(jsonParser);
        TextNode rgba = (TextNode) root.get("argb");
        return new Color(Integer.parseUnsignedInt(rgba.textValue(), 16), true);
    }
}
