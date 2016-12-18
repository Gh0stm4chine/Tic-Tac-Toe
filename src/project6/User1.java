package project6;

public class User1 extends Morpion {
	
	public static Morpion.MorpionShape shape = MorpionShape.CIRCLE;
	public static  javafx.scene.paint.Color Color1 = javafx.scene.paint.Color.RED;
	public static int score = 0 ;
	

	public User1(int x, int y) {
		super(x, y);
		map[x][y]= 'X';
	}

	public static void setShape(MorpionShape m){
		shape = m;
	}
	
	public static void setColor(javafx.scene.paint.Color m){
		Color1 = m;
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return Color1; }
	public javafx.scene.paint.Color viewFillColor() { return Color1	; }
	
}
