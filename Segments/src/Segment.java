public class Segment{
    private Point start,end;
    public Segment(Point start,Point end){
        this.start = start;
        this.end = end;
    }

    public Point getStartPoint(){
        return start;
    }

    public Point getEndPoint(){
        return end;
    }

    public double length(){
        return Math.sqrt((start.getX() - end.getX()) * ((start.getX()) - end.getX()) +
                        (start.getY() - end.getY())*(start.getY() - end.getY()));
    }

    public Point middle(){
        return new Point((start.getX() + end.getX()) / 2.0,(start.getY() + end.getY()) / 2.0);
    }

    private double ccw(Point a,Point b,Point c){
        Point ac = new Point(c.getX() - a.getX(),c.getY() - a.getY());
        Point ab = new Point(b.getX() - a.getX(),b.getY() - a.getY());
        return Math.abs(ac.getX() * ab.getY() - ac.getY() * ab.getX());
    }

    public Point intersection(Segment annother){
        if(ccw(start,annother.getStartPoint(),end) + ccw(start,annother.getEndPoint(),end) > 0){
            double cur_s = ccw(start,annother.getStartPoint(),end) + ccw(start,annother.getEndPoint(),end);
            double nxt_s = ccw(start,annother.getEndPoint(),annother.getStartPoint())
                        +  ccw(end,annother.getEndPoint(),annother.getStartPoint());
            if(cur_s == nxt_s){
                double m_1 = (end.getY() - start.getY()) / (end.getX() - start.getX());
                double b_1 = start.getY() - m_1 * start.getX();
                double m_2 = (annother.end.getY() - annother.start.getY()) / (annother.end.getX() - annother.start.getX());
                double b_2 = annother.start.getY() - m_2 * annother.start.getX();
                double x = (b_2 - b_1) / (m_1 - m_2);
                double y = m_1 * x + b_1;
                return new Point(x,y);
            }
        }
        return null;
    }
}
