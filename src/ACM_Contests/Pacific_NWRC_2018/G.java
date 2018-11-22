package ACM_Contests.Pacific_NWRC_2018;
import java.awt.geom.Point2D;
import java.util.*;
import java.lang.*;

public class G {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Point2D post = new Point2D.Double(sc.nextInt(), sc.nextInt());
        Point2D lowerLeft = new Point2D.Double(sc.nextInt(), sc.nextInt());
        Point2D upperRight = new Point2D.Double(sc.nextInt(), sc.nextInt());
        Point2D upperLeft = new Point2D.Double(lowerLeft.getX(), upperRight.getY());
        Point2D lowerRight = new Point2D.Double(upperRight.getX(), lowerLeft.getY());

        double dist;

        if (post.getX() < lowerLeft.getX()) {

            if (post.getY() < lowerLeft.getY()) {
                dist = post.distance(lowerLeft);
            } else if (post.getY() < upperRight.getY()) {
                dist = post.distance(new Point2D.Double(
                        lowerLeft.getX(), post.getY()
                ));
            } else {
                dist = post.distance(upperLeft);
            }


        } else if (post.getX() < upperRight.getX()) {

            if (post.getY() < lowerLeft.getY()) {
                dist = post.distance(new Point2D.Double(
                        post.getX(), lowerLeft.getY()
                ));
            } else if (post.getY() < upperRight.getY()) {
                throw new Exception();
            } else {
                dist = post.distance(new Point2D.Double(
                        post.getX(), upperLeft.getY()
                ));
            }

        } else {

            if (post.getY() < lowerLeft.getY()) {
                dist = post.distance(lowerRight);
            } else if (post.getY() < upperRight.getY()) {
                dist = post.distance(new Point2D.Double(
                        upperRight.getX(), post.getY()
                ));
            } else {
                dist = post.distance(upperRight);
            }

        }

        System.out.println(String.format("%.3f", dist));

    }

}
