import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;


//TODO: endcase for length 3 

public class ClosestPair {
	
	class PointCmp implements Comparator<Point> {
		String usingCoordinate;
		public PointCmp(String coordinate) {
			usingCoordinate = coordinate;
		}
	    public int compare(Point a, Point b) {
	    	if (usingCoordinate.equals("x")) {
	    		return (a.x < b.x) ? -1 : (a.x > b.x) ? 1 : 0;
	    	} else {
	    		return (a.y < b.y) ? -1 : (a.y > b.y) ? 1 : 0;
	    	}
	    		
	    }
	}
	
	public Point[] findClosestPair(Point[] pointsSortedX, Point[] pointsSortedY) {
		
		if (pointsSortedX.length == 2) {
			return pointsSortedX;
		}
		//TODO endcase for lenght 3 
		
		int middle = pointsSortedX.length/2;
		Point[] leftX = Arrays.copyOfRange(pointsSortedX, 0, middle);
		Point[] leftY = Arrays.copyOfRange(pointsSortedY, 0, middle);
		Point[] rightX = Arrays.copyOfRange(pointsSortedX, middle, pointsSortedX.length);
		Point[] rightY = Arrays.copyOfRange(pointsSortedY, middle, pointsSortedY.length);
		
		
		Point[] leftClosestPair = findClosestPair(leftX, leftY);
		Point[] rightClosestPair = findClosestPair(rightX, rightY);
		double leftDistance = Point.distance(leftClosestPair[0].x, leftClosestPair[0].y, leftClosestPair[1].x, leftClosestPair[1].y);
		double rightDistance = Point.distance(rightClosestPair[0].x, rightClosestPair[0].y, rightClosestPair[1].x, rightClosestPair[1].y);
		double delta = Math.min(leftDistance,rightDistance);
		
		Point[] splitClosestPair = findClosestSplitPair(pointsSortedX,pointsSortedY,delta);
		double splitDistance = Point.distance(splitClosestPair[0].x, splitClosestPair[0].y, splitClosestPair[1].x, splitClosestPair[1].y);
		if (leftDistance <= rightDistance && leftDistance <= splitDistance) {
			return leftClosestPair;
		} else if (rightDistance <= leftDistance && rightDistance <= splitDistance) {
			return rightClosestPair;
		} else {
			return splitClosestPair;
		}
	}

	private Point[] findClosestSplitPair(Point[] pointsSortedX, Point[] pointsSortedY, double delta) {
		int middle = pointsSortedX.length/2;
		//Point[] leftX = Arrays.copyOfRange(pointsSortedX, 0, middle);
		//Point xCentral = leftX[leftX.length-1];
		Point xCentral = pointsSortedX[middle-1];
		Point[] Sy= new Point[pointsSortedY.length];
		int j=0;
		for (int i = 0; i < pointsSortedY.length; i++) {
			if (pointsSortedY[i].x >= xCentral.x-delta && pointsSortedY[i].x <= xCentral.x+delta) {
				Sy[j] = pointsSortedY[i];
				j++;
			}
		}
		double best = delta;
		//initialize to big value:
		Point[] bestPair = {new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), new Point(Integer.MIN_VALUE, Integer.MIN_VALUE)};
		int SyLen = j;
		int iMax = SyLen, kMax = SyLen;
		boolean lessThanSeven = true;
		if (SyLen > 7) {
			iMax = SyLen - 7;
			kMax = 7;
			lessThanSeven = false;
		}
		for (int i = 0; i < iMax; i++) {
			for (int k = 0; k < kMax; k++) { //TODO
				if (i == k && lessThanSeven) continue;
				Point p1 = Sy[i];
				Point p2 = Sy[k];
				if (Point.distance(p1.x,p1.y, p2.x, p2.y) < best) {
					best = Point.distance(p1.x,p1.y, p2.x, p2.y);
					bestPair[0] = p1;
					bestPair[1] = p2;
				}
			}
		}
		return bestPair;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClosestPair cp = new ClosestPair();
		Point[] points = {new Point(0,0), new Point(2,2), new Point(1,3), new Point(5,1)};
		PointCmp xCmp = cp.new PointCmp("x");
		PointCmp yCmp = cp.new PointCmp("y");
		Point[] pointsSortedX = points.clone();
		Point[] pointsSortedY = points.clone();
		Arrays.sort(pointsSortedX, xCmp);
		Arrays.sort(pointsSortedY, yCmp);
		System.out.println(Arrays.toString(pointsSortedX));
		System.out.println(Arrays.toString(pointsSortedY));
		Point[] bestPair = cp.findClosestPair(pointsSortedX, pointsSortedY);
		System.out.println(Arrays.toString(bestPair));
		
		
	}

}
