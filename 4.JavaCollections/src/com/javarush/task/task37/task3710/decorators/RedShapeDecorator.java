package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

public class RedShapeDecorator extends ShapeDecorator
{
    Shape decoratedShape;
    public RedShapeDecorator(Shape decoratedShape)
    {
        super(decoratedShape);
        this.decoratedShape = decoratedShape;
    }
    private void setBorderColor(Shape shape)
    {
        String x = shape.getClass().getSimpleName();
        System.out.println("Setting border color for "+x+" to red.");
    }
    public void draw()
    {
        setBorderColor(decoratedShape);
        decoratedShape.draw();
    }
}
