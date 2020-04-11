package com.efimchick.tasks.figures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//TODO
class Triangle extends Figure{

    public Triangle(Point a, Point b, Point c) {
        if(Math.abs(ccw(a,b,c) - 0.00001) <= 0.00001){
            throw new RuntimeException();
        }
        points = new ArrayList<Point>();
        points.add(a);
        points.add(b);
        points.add(c);
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int z = Double.compare(p1.getX(), p2.getX());
                if (z == 0) {
                    z = Double.compare(p1.getY(), p2.getY());
                }
                return z;
            }
        });
        this.startPoint = points.get(0);
        this.type = "Triangle";
    }

    private double ccw(Point aa,Point bb,Point cc){
        Point ac = new Point(cc.getX() - aa.getX(),cc.getY() - aa.getY());
        Point ab = new Point(bb.getX() - aa.getX(),bb.getY() - aa.getY());
        return Math.abs(ac.getX() * ab.getY() - ac.getY() * ab.getX());
    }

    public double area() {
        return ccw(points.get(0),points.get(1),points.get(2)) / 2.0;
    }

    public Point centroid(){
        return new Point((points.get(0).getX() + points.get(1).getX() + points.get(2).getX()) / 3.0,
                         (points.get(0).getY() + points.get(1).getY() + points.get(2).getY()) / 3.0);
    }

    public boolean isTheSame(Figure figure){
        if(getTypeOfFigure() != figure.getTypeOfFigure()) {
            return false;
        }
        boolean ok = true;
        for(int i = 0;i < points.size();++i){
            if(!points.get(i).isTheSamePoint(figure.points.get(i))){
                ok = false;
            }
        }
        return ok;
    }

    public String toString(){
        String ret = "";
        ret += getTypeOfFigure();
        ret += "[";
        for(int i = 0;i < 3;++i){
            ret += points.get(i).toString();
        }
        ret += "]";
        return ret;
    }
}
