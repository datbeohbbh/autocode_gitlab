import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args){
        final Segment a = new Segment(new Point(2, 5), new Point(0.5, 1.5));
        final Segment b = new Segment(new Point(0, 2), new Point(2,5));
        Point m = a.intersection(b);
        System.out.printf("%.16f",m.getX());
        System.out.println();
        System.out.printf("%.16f",m.getY());
    }
}
