package MapEditor;

public class MapElementSpade extends MapElement{
	public MapElementSpade(int x,int y) {
		super(x,y);
		imgX = 24*34;
		imgY = 7*34;
		width = 34;
		type = "SPADE";
	}
	public MapElement CloneElement(int x,int y) {
		return new MapElementSpade(x,y);
	}
	
	
}
