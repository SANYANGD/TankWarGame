package MapEditor;

public class MapElementPlayerTank extends MapElement{
	public MapElementPlayerTank(int x,int y) {
		super(x,y);
		imgX = 0;
		imgY = 34;
		width = 34;
		type = "PLAYERTANK";
	}
	
	public MapElement CloneElement(int x,int y) {
		return new MapElementPlayerTank(x,y);
	}

}
