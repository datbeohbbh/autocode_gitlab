package com.efimchick.tasks.figures;

import java.util.ArrayList;

abstract class Figure{

    public Point startPoint;
    public ArrayList<Point> points;
    public String type;

    public abstract double area();

    public abstract Point centroid();

    public abstract boolean isTheSame(Figure figure);

    public String getTypeOfFigure(){
        return type;
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }
}
