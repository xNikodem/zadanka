package org.example.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.Shape;
import org.example.shapes.Circle;
import org.example.shapes.Rectangle;
import org.example.shapes.Square;

import java.io.IOException;

public class ShapeDeserializer extends JsonDeserializer<Shape> {

    @Override
    public Shape deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("@type").asText();

        return switch (type) {
            case "Circle" -> new Circle(node.get("radius").asDouble());
            case "Square" -> new Square(node.get("side").asDouble());
            case "Rectangle" -> new Rectangle(node.get("length").asDouble(), node.get("width").asDouble());
            default -> throw new IllegalArgumentException("Invalid type: " + type);
        };
    }
}

