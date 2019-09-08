package MapEditor;

public class MapElementWater extends MapElement{
	public MapElementWater(int x,int y) {
		super(x,y);
		imgX = 0;
		imgY = 7*34;
		width = 34;
		type = "WATER";
	}
	
	public MapElement CloneElement(int x,int y) {
		return new MapElementWater(x,y);
	}
}
