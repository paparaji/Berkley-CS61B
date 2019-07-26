package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    /**
     * Initialize the point set
     */
    NaivePointSet(List<Point> points) {
        this.points = points;
    }

    /**
     * Return the nearest point
     */
    @Override
    public Point nearest(double x, double y) {
        if (!points.isEmpty()) {
            Point temp = new Point(x, y);
            Point nearest = points.get(0);
            for (Point p : points) {
                if (Point.distance(p, temp) < Point.distance(nearest, temp)) {
                    nearest = p;
                }
            }
            return nearest;
        }
        return null;
    }
}
