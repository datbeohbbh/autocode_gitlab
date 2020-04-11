package com.efimchick.tasks.figures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String args[]){
        ArrayList <Figure> li = new ArrayList<>();
        Circle c1;
        Circle c2;
        Circle c3;
        Triangle t1;
        Triangle t2;
        Triangle t3;
        Quadrilateral q1;
        Quadrilateral q2;
        Quadrilateral q3;
        c1 = new Circle(new Point(0, 9), 7);
        c2 = new Circle(new Point(1, 9), 10);
        c3 = new Circle(new Point(-1, 9), 9.5);
        t1 = new Triangle(new Point(0, 20), new Point(20, 0), new Point(-20, 0));
        t2 = new Triangle(new Point(-15, 0), new Point(-10, 20), new Point(-5, 0));
        t3 = new Triangle(new Point(-14, 0), new Point(-10, 10), new Point(-5, 0));
        q1 = new Quadrilateral(new Point(-30, -30), new Point(-30, 30), new Point(30, 30), new Point(30, -30));
        q2 = new Quadrilateral(new Point(-20, -100), new Point(-20, 100), new Point(30, 30), new Point(30, -30));
        q3 = new Quadrilateral(new Point(-10, -10), new Point(-10, 10), new Point(10, 30), new Point(30, -10));
        li.add(c1);
        li.add(c2);
        li.add(c3);
        li.add(t1);
        li.add(t2);
        li.add(t3);
        li.add(q1);
        li.add(q2);
        li.add(q3);
        Collections.sort(li, new Comparator<Figure>() {
            @Override
            public int compare(Figure lhs, Figure rhs) {
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
        });
        for(int i = 0;i < li.size();++i){
            System.out.println(li.get(i).type + "  " + li.get(i).startPoint);
        }
    }

}
