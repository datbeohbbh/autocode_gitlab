package com.efimchik.tasks.triangle;

class Triangle {

    private Point a,b,c;

    public Triangle(Point a, Point b, Point c) {
        if(Math.abs(ccw(a,b,c) - 0.00001) <= 0.00001){
            throw new RuntimeException();
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private double ccw(Point aa,Point bb,Point cc){
        Point ac = new Point(cc.getX() - aa.getX(),cc.getY() - aa.getY());
        Point ab = new Point(bb.getX() - aa.getX(),bb.getY() - aa.getY());
        return Math.abs(ac.getX() * ab.getY() - ac.getY() * ab.getX());
    }

    public double area() {
        return ccw(a,b,c) / 2.0;
    }

    public double calc(double x,double y,double z){
        return (x + y + z) / 3.0;
    }

    public Point centroid(){
        return new Point(calc(a.getX(),b.getX(),c.getX()),calc(a.getY(),b.getY(),c.getY()));
    }

    public static void main(String[] args) {

    }
}
