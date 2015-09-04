package dsaTutorial3;

import java.awt.geom.Point2D;
import java.util.Formatter;

/**
 * Created by bbr on 01.09.15.
 */

public class MyPolygon {

    private final MyLinkedList<MyPoint> pointList;
    private int coordsAmount = 0;
    private static final int RADIUS = 5;

    public MyPolygon(int coordsAmount) {
        if (coordsAmount > 0) {
            pointList = new MyLinkedList<>();
            this.coordsAmount = coordsAmount;
            generateCoords();
        } else
            throw new ExceptionInInitializerError("The amount of coordinates should be > 0.");
    }

    private void generateCoords() {
        for (int i = 0; i < coordsAmount; i++) {
            pointList.insert(new MyPoint(RADIUS * Math.cos((2 * Math.PI * i) / coordsAmount),
                                                RADIUS * Math.sin((2 * Math.PI * i) / coordsAmount)), i);
        }
    }

    public void printIt() {
        System.out.println("\nPolygon's coordinates:");
        for (Point2D point2D : pointList)
            System.out.println(point2D);
    }

    public double monteCarlo(int numberOfTestPoints) {
        double result = 0.0;
        int innerCounter = 0;
        int outerCounter = 0;

        Point2D.Double constantPoint = new Point2D.Double(-RADIUS * 2, -RADIUS * 2);
        Point2D.Double randomPoint;

        for (int i = 0; i < numberOfTestPoints; i++) {
            randomPoint = new Point2D.Double(Math.random() * RADIUS * 2 - RADIUS, Math.random() * RADIUS * 2 - RADIUS);

            for (int j = 0; j < pointList.getSize() - 1; j++) {
                if (intersects(pointList.get(j), pointList.get(j + 1), constantPoint, randomPoint))
                    innerCounter++;
            }

            if (innerCounter % 2 == 1)
                outerCounter++;
            innerCounter = 0;
        }

        System.out.println("\nThe amount of points: " + numberOfTestPoints + "; " + outerCounter + " of them are inside the polygon.");
        System.out.printf("PI * RADIUS * RADIUS: %.2f\n", Math.PI * RADIUS * RADIUS);
        result = (RADIUS * RADIUS * 4 * outerCounter / (double)numberOfTestPoints);

        return result;
    }

    private static boolean intersects(Point2D a, Point2D b, Point2D c, Point2D d) {
        // We describe the section AB as A+(B-A)*u and CD as C+(D-C)*v
        // then we solve A + (B-A)*u = C + (D-C)*v
        // let's use Kramer's rule to solve the task (Ax = B) where x = (u, v)^T
        // build a matrix for the equation

        double[][] A = new double[2][2];
        A[0][0] = b.getX() - a.getX();
        A[1][0] = b.getY() - a.getY();
        A[0][1] = c.getX() - d.getX();
        A[1][1] = c.getY() - d.getY();

        // calculate determinant
        double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];

        // substitute columns and calculate determinants
        double detU = (c.getX() - a.getX()) * A[1][1] - (c.getY() - a.getY()) * A[0][1];
        double detV = A[0][0] * (c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());

        // calculate the solution
        // even if det0 == 0 (they are parallel) this will return NaN and comparison will fail -> false
        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }

    private static class MyPoint extends Point2D.Double {

        public MyPoint(double v, double v1) {
            x = v;
            y = v1;
        }

        @Override
        public String toString() {
            Formatter formatter = new Formatter();
            formatter.format("Point:(%.2f; %.2f);", x, y);
            return formatter.toString();
        }
    }

    public static void main(String[] args) {
        MyPolygon polygon = new MyPolygon(20);
        polygon.printIt(); //for each loop
        System.out.printf("Monte-Carlo's result: %.2f\n", polygon.monteCarlo(100000));
    }
}
