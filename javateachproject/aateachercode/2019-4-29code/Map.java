
import java.awt.Point;
public class Map {
	private Point[] points;
	private Point mainPoint;
	public Map(){
		points = new Point[3];
		points[0] = new Point(50,50);
		points[1] = new Point(400,50);
		points[2] = new Point(750,50);
		
		mainPoint = new Point(400,550);
	}
	
	public Point[] getPoints(){
		return points;
	}
	public Point getMainPoint(){
		return mainPoint;
	}
}
