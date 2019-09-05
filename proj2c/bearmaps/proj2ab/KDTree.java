package bearmaps;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.PointSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class KDTree implements PointSet {

    private PointNode kdtree;

    private HashSet<Point> keySet = new HashSet<>();

    private int size = 1;

    private class PointNode {
        private double X;
        private double Y;
        private PointNode leftChild = null;
        private PointNode rightChild = null;
        private boolean direction; //True : vertical, False : horizontal

        PointNode(Point point, boolean direction) {
            this.X = point.getX();
            this.Y = point.getY();
            this.direction = direction;
        }

        public void setLeftChild(PointNode leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(PointNode rightChild) {
            this.rightChild = rightChild;
        }

        public double getX() {
            return X;
        }

        public double getY() {
            return Y;
        }

        public boolean getDirection() {
            return direction;
        }

        public double distance(Point point) {
            return Point.distance(new Point(X, Y), point);
        }
    }

    /**
     * Initialize the KDT Tree
     */
    KDTree(List<Point> points) {
        if (!points.isEmpty()) {
            kdtree = new PointNode(points.get(0), true);
            for (int i = 1; i < points.size(); i++) {
                Helper(kdtree, points.get(i));
            }
        }
    }

    /**
     * Helper function of the constructor
     */
    public void Helper(PointNode currentNode, Point point) {
        double difference;
        if (currentNode.getDirection()) {
            difference = currentNode.getX() - point.getX();
        } else {
            difference = currentNode.getY() - point.getY();
        }
        if (difference < 0.00001) {
            if (currentNode.rightChild == null) {
                currentNode.setRightChild(new PointNode(point, !currentNode.getDirection()));
                keySet.add(point);
                size += 1;
            } else {
                Helper(currentNode.rightChild, point);
            }
        } else {
            if (currentNode.leftChild == null) {
                currentNode.setLeftChild(new PointNode(point, !currentNode.getDirection()));
                keySet.add(point);
                size += 1;
            } else {
                Helper(currentNode.leftChild, point);
            }
        }
    }

    /**
     * Return the nearest point
     */
    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        PointNode result = nearestHelper(kdtree, kdtree, goal);
        return new Point(result.getX(), result.getY());
    }

    /**
     * Helper function of the nearest method
     */
    private PointNode nearestHelper(PointNode currentNode, PointNode best, Point goal) {
        if (currentNode == null) {
            return best;
        }
        if (currentNode.distance(goal) < best.distance(goal)) {
            best = currentNode;
        }
        PointNode goodSide;
        PointNode badSide;
        double difference;
        if (currentNode.getDirection()) {
            difference = currentNode.getX() - goal.getX();
        } else {
            difference = currentNode.getY() - goal.getY();
        }
        if (difference < 0.00001) {
            goodSide = currentNode.rightChild;
            badSide = currentNode.leftChild;
        } else {
            goodSide = currentNode.leftChild;
            badSide = currentNode.rightChild;
        }
        best = nearestHelper(goodSide, best, goal);
        if (currentNode.getDirection()) {
            if (Math.pow(Math.abs(currentNode.getX() - goal.getX()), 2) - best.distance(goal) < 0.00001) {
                best = nearestHelper(badSide, best, goal);
            }
        } else {
            if (Math.pow(Math.abs(currentNode.getY() - goal.getY()), 2) - best.distance(goal) < 0.00001) {
                best = nearestHelper(badSide, best, goal);
            }
        }
        return best;
    }
}
