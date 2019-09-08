package MapEditor;

public class MapElementAutoTank extends MapElement{
	public MapElementAutoTank(int x,int y) {
		super(x,y);
		imgX = 4*34;
		imgY = 0;
		width = 34;
		type = "AUTOTANK";
	}
	public MapElement CloneElement(int x,int y) {
		return new MapElementAutoTank(x,y);
	}

}
