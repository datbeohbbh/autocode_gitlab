package com.efimchick.tasks.figures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//TODO
class Quadrilateral extends Figure{

    private static  final double lim = 0.00000000001;

    public Quadrilateral(Point a,Point b,Point c,Point d){
        if(a == null || b == null || c == null || d == null){
            throw new RuntimeException();
        }
        points = new ArrayList<Point>();
        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        if(!checkQuadPlain(points.get(0),points.get(1),points.get(2),points.get(3))){
            throw new RuntimeException();
        }
        if(!checkQuadPlain(points.get(1),points.get(2),points.get(0),points.get(3))){
            throw new RuntimeException();
        }
        if(!checkQuadPlain(points.get(2),points.get(3),points.get(0),points.get(1))){
            throw new RuntimeException();
        }
        points = computeConvexHull(points);
        if(points.size() != 4){
            throw new RuntimeException();
        }
        this.startPoint = points.get(0);
        this.type = "Quadrilateral";
    }

    boolean checkQuadPlain(Point x,Point y,Point u,Point v){
        Point vec = new Point(y.getX() - x.getX(),y.getY() - x.getY());
        Point n_vec = new Point(-vec.getY(),vec.getX());
        double ll = n_vec.getX() * (u.getX() - x.getX()) + n_vec.getY()*(u.getY() - x.getY());
        double rr = n_vec.getX() * (v.getX() - x.getX()) + n_vec.getY()*(v.getY() - x.getY());
        return  (ll * rr > 0.0);
    }

    ArrayList <Point> computeConvexHull(ArrayList <Point> li){
        Collections.sort(li, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int z = Double.compare(p1.getX(),p2.getX());
                if(z == 0){
                    z = Double.compare(p1.getY(),p2.getY());
                }
                return z;
            }
        });
        ArrayList <Point> hull = new ArrayList<Point>();
        for(int i = 0;i < li.size();++i){
            while(hull.size() >= 2 && bad(hull.get(hull.size() - 2),hull.get(hull.size() - 1),li.get(i))){
                hull.remove(hull.size() - 1);
            }
            hull.add(li.get(i));
        }
        hull.remove(hull.size() - 1);
        int k = hull.size();
        for(int i = li.size() - 1;i >= 0;--i){
            while(hull.size() - k >= 2 && bad(hull.get(hull.size() - 2),hull.get(hull.size() - 1),li.get(i))){
                hull.remove(hull.size() - 1);
            }
            hull.add(li.get(i));
        }
        hull.remove(hull.size() - 1);
        return hull;
    }

    private boolean bad(Point u,Point v,Point w){
        return (v.getX() - u.getX()) * (w.getY() - v.getY()) - (w.getX() - v.getX()) * (v.getY() - u.getY()) >= 0;
    }

    public double area(){
        Point cen = centroid();
        double ret = 0;
        for(int i = 0;i < points.size();++i){
            Point p = (i > 0 ? points.get(i - 1) : points.get(points.size() - 1));
            Point q = points.get(i);
            ret += (p.getX() - q.getX()) * (p.getY() + q.getY());
        }
        return Math.abs(ret) / 2.0;
    }

    public Point centroid(){
        Triangle abd = new Triangle(points.get(0),points.get(1),points.get(3));
        Triangle cbd = new Triangle(points.get(2),points.get(1),points.get(3));
        Triangle adc = new Triangle(points.get(0),points.get(3),points.get(2));
        Triangle abc = new Triangle(points.get(0),points.get(1),points.get(2));
        Point centroid_abd = abd.centroid();
        Point centroid_cbd = cbd.centroid();
        Point centroid_adc = adc.centroid();
        Point centroid_abc = abc.centroid();
        double slope_u = (centroid_cbd.getY() - centroid_abd.getY()) / (centroid_cbd.getX() - centroid_abd.getX());
        double slope_v = (centroid_abc.getY() - centroid_adc.getY()) / (centroid_abc.getX() - centroid_adc.getX());
        double b_u = centroid_cbd.getY() - slope_u * centroid_cbd.getX();
        double b_v = centroid_abc.getY() - slope_v * centroid_abc.getX();
        double x = (b_v - b_u) / (slope_u - slope_v);
        double y = slope_u * x + b_u;
        if(Math.abs(0 - x) <= lim)x = 0;
        if(Math.abs(0 - y) <= lim)y = 0;
        return new Point(x,y);
    }

    public boolean isTheSame(Figure figure){
        if(!getTypeOfFigure().equals(figure.getTypeOfFigure())){
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
        for(int i = 0;i < points.size();++i){
            ret += points.get(i).toString();
        }
        ret += "]";
        return ret;
    }
}
