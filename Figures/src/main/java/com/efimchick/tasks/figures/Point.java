package com.efimchick.tasks.figures;

class Point {
    private double x;
    private double y;
    private static double lim = 0.00000001;
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString(){
        String ret = "";
        ret += "(";
        ret += Double.toString(x);
        ret += ",";
        ret += Double.toString(y);
        ret += ")";
        return ret;
    }

    public boolean isTheSamePoint(Point cur){
        return (Math.abs(x - cur.getX()) <= lim && Math.abs(y - cur.getY()) <= lim);
    }

}
