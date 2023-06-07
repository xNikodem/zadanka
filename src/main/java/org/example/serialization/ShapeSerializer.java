package org.example.serialization;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.Shape;
import org.example.shapes.Circle;
import org.example.shapes.Rectangle;
import org.example.shapes.Square;

import java.io.IOException;
public class ShapeSerializer extends JsonSerializer<Shape> {
    @Override
    public void serialize(Shape shape, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("@type", shape.getClass().getSimpleName());

        if (shape instanceof Circle circle) {
            jsonGenerator.writeNumberField("radius", circle.getRadius());
        } else if (shape instanceof Square square) {
            jsonGenerator.writeNumberField("side", square.getSide());
        } else if (shape instanceof Rectangle rectangle) {
            jsonGenerator.writeNumberField("length", rectangle.getLength());
            jsonGenerator.writeNumberField("width", rectangle.getWidth());
        }

        jsonGenerator.writeEndObject();
    }
}

