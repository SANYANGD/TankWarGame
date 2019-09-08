package MapEditor;

public class MapElementIron extends MapElement{
	public MapElementIron(int x,int y) {
		super(x,y);
		imgX = 0;
		imgY = 6*34;
		width = 17;
		type = "IRON";
	}
	public MapElement CloneElement(int x,int y) {
		return new MapElementIron(x,y);
	}

}
