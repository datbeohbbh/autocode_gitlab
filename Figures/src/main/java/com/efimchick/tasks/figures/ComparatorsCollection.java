package com.efimchick.tasks.figures;

public class ComparatorsCollection {

    //TODO
    public static int compareByArea(Figure lhs, Figure rhs){
        int z = Double.compare(lhs.area(),rhs.area());
        return z;
    }

    //TODO
    public static int compareByHorizontalStartPosition(Figure lhs, Figure rhs){
        Point l_lhs = lhs.startPoint;
        Point l_rhs = rhs.startPoint;
        int compareY = Double.compare(l_lhs.getY(),l_rhs.getY());
        int compareX = Double.compare(l_lhs.getX(),l_rhs.getX());
        if(compareX == 0){
            return -compareY;
        } else {
            return compareX;
        }
    }

    //TODO
    public static int compareByHorizontalCenterPosition(Figure lhs, Figure rhs){
        Point cen_lhs = lhs.centroid();
        Point cen_rhs = rhs.centroid();
        int compareY = Double.compare(cen_lhs.getY(),cen_rhs.getY());
        int compareX = Double.compare(cen_lhs.getX(),cen_rhs.getX());
        if(compareX == 0){
            return compareY;
        } else {
            return compareX;
        }
    }
}
