package MapEditor;

public class MapElementBrick extends MapElement{
	public MapElementBrick(int x,int y) {
		super(x,y);
		imgX = 18*34;
		imgY = 5*34;
		width = 17;
		type = "BRICK";
	}
	public MapElement CloneElement(int x,int y) {
		return new MapElementBrick(x,y);
	}

}
