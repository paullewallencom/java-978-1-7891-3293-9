import java.util.ArrayList;
import java.util.List;

/**
 * Created by jananiravi on 11/7/15.
 */
public class PointsWithinDistance {

    public static void main(String[] args) {
        List<Point> list = new ArrayList<Point>();
        list.add(new Point(3, 6));
        list.add(new Point(5, 6));
        list.add(new Point(20, 21));
        list.add(new Point(1, -3));
        list.add(new Point(11, 20));
        list.add(new Point(50, 50));
        list.add(new Point(4, 4));
        list.add(new Point(-2, -6));
        list.add(new Point(55, 48));
        list.add(new Point(54, 49));

        getPointsWithinDistance(list, new Point(0, 0), 10.0);
        getPointsWithinDistance(list, new Point(52, 54), 10.0);
    }

    public static List<Point> getPointsWithinDistance(List<Point> list, Point point, double distance) {
        List<Point> withinDistanceList = new ArrayList<Point>();
        for (Point p : list) {
            if (p.isWithinDistance(point, distance)) {
                withinDistanceList.add(p);
            }
        }

        System.out.println(String.format("Points within %s of point x = %s, y = %s",
                distance, point.getX(), point.getY()));
        for (Point p : withinDistanceList) {
            System.out.println(String.format("Point: x = %s, y = %s", p.getX(), p.getY()));
        }

        return withinDistanceList;
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public double getDistance(Point otherPoint) {
            return Math.sqrt(Math.pow(otherPoint.x - x, 2) + Math.pow(otherPoint.y - y, 2));
        }

        public boolean isWithinDistance(Point otherPoint, double distance) {
            if (Math.abs(otherPoint.x - x) > distance  || (otherPoint.y - y) > distance) {
                return false;
            }

            return getDistance(otherPoint) <= distance;
        }
    }
}
