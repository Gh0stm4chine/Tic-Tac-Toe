package project6;

public class User2 extends Morpion {

	public static Morpion.MorpionShape shape = MorpionShape.STAR;
	public static  javafx.scene.paint.Color Color2 = javafx.scene.paint.Color.GREEN;
	public static int score = 0 ;

	public User2(int x, int y) {
		super(x, y);
		map[x][y]= 'Y';
	}
	
	public static void setShape(MorpionShape m){
		shape = m;
	}
	
	public static void setColor(javafx.scene.paint.Color m){
		Color2 = m;
	}
	
	
	public static MorpionShape viewShape(){return shape;}

	public javafx.scene.paint.Color viewOutlineColor() { return Color2; }
	public javafx.scene.paint.Color viewFillColor() { return Color2; }
	

}
