package com.efimchick.tasks.figures;

import java.util.ArrayList;

class Circle extends Figure{
    private Point center;
    private double radius;
    private static double pi = 3.1415926535897932384626433;

    public Circle(Point center,double radius){
        if(center == null || radius <= 0){
            throw new RuntimeException();
        }
        points = new ArrayList<Point>();
        points.add(center);
        this.startPoint = new Point(center.getX() - radius,center.getY());
        this.center = center;
        this.radius = radius;
        this.type = "Circle";
    }

    public double area(){
        return pi * radius * radius;
    }

    public Point centroid(){
        return center;
    }

    public boolean isTheSame(Figure figure){
        if(!getTypeOfFigure().equals(figure.getTypeOfFigure())){
            return false;
        }
        return (center.isTheSamePoint(figure.centroid()) && Math.abs(area() - figure.area()) <= 0.00000001);
    }

    public String toString(){
        String ret = "";
        ret += getTypeOfFigure();
        ret += "[";
        ret += center.toString();
        ret += Double.toString(radius);
        ret += "]";
        return  ret;
    }
}
