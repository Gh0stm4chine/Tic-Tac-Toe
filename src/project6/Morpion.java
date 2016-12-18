package project6;

import java.util.ArrayList;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Morpion {



	private int x;
	private int y;
	public static ArrayList<Morpion> popul = new ArrayList<Morpion>();
	public static char map [][] = new char [3][3];
	private static java.util.Random rand = new java.util.Random();
	public static int cntPawn = 0;
	private static MorpionShape shape = MorpionShape.CIRCLE;
	public static int IAmoves[] = new int [2] ;

	public enum MorpionShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR,
		//POLYGONE
	}

	public Morpion(int x,int y){
		this.x = x;
		this.y = y;
		popul.add(this);
		cntPawn++;
	}



	public static boolean isOccupied(int x ,int y){
		return (map[x][y] == 'X' || map[x][y] == 'Y');
	}


	public static void play(int x,int y) {
		if (Params.Player == 1){
			Morpion m = new User1(x,y);
		}
		else {
			Morpion m = new User2(x,y);
		}
		if(endGame() == 3) {
			if (Params.IA){
				System.out.println(Params.level);
				if (Params.level == 1) {
					int xIA = rand.nextInt(3); 
					int yIA = rand.nextInt(3);
					while(isOccupied(xIA,yIA)) {
						xIA = rand.nextInt(3);
						yIA = rand.nextInt(3);
					}
					Morpion m1 = new User2(xIA,yIA);
					IAmoves[0] = xIA ; IAmoves[1] = yIA;
				}
				else {
					calcIA(Params.level);
				}

			} else {
				if (Params.Player == 1){
					Params.Player = 2;
				}
				else {
					Params.Player = 1;
				}
			}
		}
			Params.endGame = endGame();
			//MainController.MessageEnd();
	}


	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}

	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }



	/* 0 : draw
	 * 1 : User1 won
	 * 2 : User2 won
	 * 3 : Game still going
	 */
	public static int endGame() {
		if(map[0][0] == map[0][1] && map[0][2] == map[0][0] && map[0][0] != '-') {
			if(map[0][0] == 'X')
				return 1 ;
			else
				return 2 ;
		}
		if(map[0][0] == map[1][0] && map[2][0] == map[0][0] && map[0][0] != '-') {
			if(map[0][0] == 'X')
				return 1 ;
			else
				return 2 ;
		}
		if(map[0][0] == map[1][1] && map[2][2] == map[0][0] && map[0][0] != '-') {
			if(map[0][0] == 'X')
				return 1 ;
			else
				return 2 ;
		}
		if(map[2][2] == map[2][1] && map[2][2] == map[2][0] && map[2][2] != '-') {
			if(map[2][2] == 'X')
				return 1 ;
			else
				return 2 ;
		}
		if(map[2][2] == map[1][2] && map[2][2] == map[0][2] && map[2][2] != '-') {
			if(map[2][2] == 'X')
				return 1 ;
			else
				return 2 ;
		}
		if(map[0][2] == map[1][1] && map[0][2] == map[2][0] && map[1][1] != '-') {
			if(map[0][2] == 'X')
				return 1 ;
			else
				return 2 ;
		}
		if(map[0][1] == map[1][1] && map[0][1] == map[2][1] && map[0][1] != '-') {
			if(map[0][1] == 'X')
				return 1 ;
			else
				return 2 ;
		}
		if(map[1][0] == map[1][1] && map[1][0] == map[1][2] && map[1][0] != '-') {
			if(map[1][0] == 'X')
				return 1 ;
			else
				return 2 ;
		}
		if (popul.size() == 9)
			return 0 ;
		else
			return 3 ;

	}

	public static void initWorld() {
		for (int i = 0; i<3; i++){
			for (int j = 0;j<3;j++){
				map[i][j] = '-';
			}
		}
	}

	public static void displayWorld(){

		for (Morpion m : popul){
			//System.out.println(m.getClass().toString());
			if (m.getClass().toString().equals("class project6.User1")){
				map[m.x][m.y] = 'X';
			}
			else{
				map[m.x][m.y] = 'Y';
			} 
		}
		for (int i = 0; i<3; i++){
			for (int j = 0;j<3;j++){
				System.out.print(map[i][j]);
			}
			System.out.println();

		}
	}

	public static void calcIA(int level) {
		Morpion m;
		if(level == 2) {
			if (calcIAlevel2() == -1) {
				int xIA = rand.nextInt(3); 
				int yIA = rand.nextInt(3);
				while(isOccupied(xIA,yIA)) {
					xIA = rand.nextInt(3);
					yIA = rand.nextInt(3);
				}
				m = new User2(xIA,yIA);
				IAmoves[0] = xIA ; IAmoves[1] = yIA;
			}
		} else if(level == 3) {
			if(calcIAlevel3() == -1) {
				calcIA(2);
			}
		}
	}
	private static int calcIAlevel2() {
		Morpion m ;
		if(map[0][0] == map[1][0] && map[0][0] == 'X' && !isOccupied(2,0) ) {
			IAmoves[0] = 2 ; IAmoves[1] = 0 ;
			m = new User2(2,0);
			return 0;
		}
		else if(map[0][0] == map[2][0] && map[0][0] == 'X' && !isOccupied(1,0)) {
			IAmoves[0] = 1 ; IAmoves[1] = 0;
			m = new User2(1,0);
			return 0;
		}
		else if(map[1][0] == map[2][0] && map[1][0] == 'X' && !isOccupied(2,0)) {
			IAmoves[0] = 0 ; IAmoves[1] = 0;
			m = new User2(0,0);
			return 0;
		}
		else if(map[0][0] == map[1][1] && map[0][0] == 'X' && !isOccupied(2,2)) {
			m = new User2(2,2);
			IAmoves[0] = 2 ; IAmoves[1] = 2 ;
			return 0;
		}
		else if(map[2][2] == map[0][0] && map[2][2] == 'X' && !isOccupied(1,1)) {
			m = new User2(1,1);
			IAmoves[0] = 1; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[2][2] == map[1][1] && map[1][1] == 'X' && !isOccupied(0,0)) {
			m = new User2(0,0);
			IAmoves[0] = 0 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[0][0] == map[0][1] && map[0][0] == 'X' && !isOccupied(0,2)) {
			m = new User2(0,2);
			IAmoves[0] = 0 ; IAmoves[1] = 2 ;
			return 0;
		}
		else if(map[0][0] == map[0][2] && map[0][2] == 'X' && !isOccupied(0,1) ) {
			m = new User2(0,1);
			IAmoves[0] = 0 ; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[0][1] == map[0][2] && map[0][1] == 'X' && !isOccupied(0,0)) {
			m = new User2(0,0);
			IAmoves[0] = 0 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[2][2] == map[2][1] && map[2][1] == 'X' && !isOccupied(2,0)) {
			m = new User2(2,0);
			IAmoves[0] = 2 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[2][2] == map[2][0] && map[2][0] == 'X' && !isOccupied(2,1)) {
			m = new User2(2,1);
			IAmoves[0] = 2 ; IAmoves[1] = 1;
			return 0;
		}
		else if(map[2][0] == map[2][1] && map[2][0] == 'X' && !isOccupied(2,2)) {
			m = new User2(2,2);
			IAmoves[0] = 2; IAmoves[1] = 2;
			return 0;
		}
		else if(map[2][2] == map[1][2] && map[1][2] == 'X' && !isOccupied(0,2)) {
			m = new User2(0,2);
			IAmoves[0] = 0 ; IAmoves[1] = 2 ;
			return 0;
		}
		else if(map[2][2] == map[0][2] && map[0][2] == 'X' && !isOccupied(1,2)) {
			m = new User2(1,2);
			IAmoves[0] = 1 ; IAmoves[1] = 2 ;
			return 0;
		}
		else if(map[0][2] == map[1][2] && map[0][2] == 'X' && !isOccupied(2,2)) {
			m = new User2(2,2);
			IAmoves[0] = 2 ; IAmoves[1] = 2;
			return 0;
		}
		else if(map[0][2] == map[1][1] && map[0][2] == 'X' && !isOccupied(2,0)) {
			m = new User2(2,0);
			IAmoves[0] = 2 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[0][2] == map[2][0] && map[2][0] == 'X' && !isOccupied(1,1)) {
			m = new User2(1,1);
			IAmoves[0] = 1 ; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[2][0] == map[1][1] && map[2][0] == 'X' && !isOccupied(0,2)) {
			m = new User2(0,2);
			IAmoves[0] = 0 ; IAmoves[1] = 2 ;
			return 0;
		} 
		else if(map[1][1] == map[0][1] && map[0][1] == 'X' && !isOccupied(2,1)) {
			m = new User2(2,1);
			IAmoves[0] = 2 ; IAmoves[1] = 1 ;
			return 0;
		} 	
		else if(map[1][1] == map[2][1] && map[2][1] == 'X' && !isOccupied(0,1)) {
			m = new User2(0,1);
			IAmoves[0] = 0 ; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[2][1] == map[0][1] && map[2][1] == 'X' && !isOccupied(1,1)) {
			m = new User2(1,1);
			IAmoves[0] = 1 ; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[1][1] == map[1][0] && map[1][1] == 'X' && !isOccupied(1,2)) {
			m = new User2(1,2);
			IAmoves[0] = 1 ; IAmoves[1] = 2 ;
			return 0;
		} 	
		else if(map[1][1] == map[1][2] && map[1][1] == 'X' && !isOccupied(1,0)) {
			m = new User2(1,0);
			IAmoves[0] = 1 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[1][2] == map[1][0] && map[1][0] == 'X' && !isOccupied(1,1)) {
			m = new User2(1,1);
			IAmoves[0] = 1 ; IAmoves[1] = 1 ;
			return 0;
		}
		else {
			return -1;
		}
	}
	
	private static int calcIAlevel3() {
		Morpion m;
		if(map[0][0] == map[1][0] && map[0][0] == 'Y' && !isOccupied(2,0) ) {
			IAmoves[0] = 2 ; IAmoves[1] = 0 ;
			m = new User2(2,0);
			return 0;
		}
		else if(map[0][0] == map[2][0] && map[0][0] == 'Y' && !isOccupied(1,0)) {
			IAmoves[0] = 1 ; IAmoves[1] = 0;
			m = new User2(1,0);
			return 0;
		}
		else if(map[1][0] == map[2][0] && map[1][0] == 'Y' && !isOccupied(2,0)) {
			IAmoves[0] = 0 ; IAmoves[1] = 0;
			m = new User2(0,0);
			return 0;
		}
		else if(map[0][0] == map[1][1] && map[0][0] == 'Y' && !isOccupied(2,2)) {
			m = new User2(2,2);
			IAmoves[0] = 2 ; IAmoves[1] = 2 ;
			return 0;
		}
		else if(map[2][2] == map[0][0] && map[2][2] == 'Y' && !isOccupied(1,1)) {
			m = new User2(1,1);
			IAmoves[0] = 1; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[2][2] == map[1][1] && map[1][1] == 'Y' && !isOccupied(0,0)) {
			m = new User2(0,0);
			IAmoves[0] = 0 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[0][0] == map[0][1] && map[0][0] == 'Y' && !isOccupied(0,2)) {
			m = new User2(0,2);
			IAmoves[0] = 0 ; IAmoves[1] = 2 ;
			return 0;
		}
		else if(map[0][0] == map[0][2] && map[0][2] == 'Y' && !isOccupied(0,1) ) {
			m = new User2(0,1);
			IAmoves[0] = 0 ; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[0][1] == map[0][2] && map[0][1] == 'Y' && !isOccupied(0,0)) {
			m = new User2(0,0);
			IAmoves[0] = 0 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[2][2] == map[2][1] && map[2][1] == 'Y' && !isOccupied(2,0)) {
			m = new User2(2,0);
			IAmoves[0] = 2 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[2][2] == map[2][0] && map[2][0] == 'Y' && !isOccupied(2,1)) {
			m = new User2(2,1);
			IAmoves[0] = 2 ; IAmoves[1] = 1;
			return 0;
		}
		else if(map[2][0] == map[2][1] && map[2][0] == 'Y' && !isOccupied(2,2)) {
			m = new User2(2,2);
			IAmoves[0] = 2; IAmoves[1] = 2;
			return 0;
		}
		else if(map[2][2] == map[1][2] && map[1][2] == 'Y' && !isOccupied(0,2)) {
			m = new User2(0,2);
			IAmoves[0] = 0 ; IAmoves[1] = 2 ;
			return 0;
		}
		else if(map[2][2] == map[0][2] && map[0][2] == 'Y' && !isOccupied(1,2)) {
			m = new User2(1,2);
			IAmoves[0] = 1 ; IAmoves[1] = 2 ;
			return 0;
		}
		else if(map[0][2] == map[1][2] && map[0][2] == 'Y' && !isOccupied(2,2)) {
			m = new User2(2,2);
			IAmoves[0] = 2 ; IAmoves[1] = 2;
			return 0;
		}
		else if(map[0][2] == map[1][1] && map[0][2] == 'Y' && !isOccupied(2,0)) {
			m = new User2(2,0);
			IAmoves[0] = 2 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[0][2] == map[2][0] && map[2][0] == 'Y' && !isOccupied(1,1)) {
			m = new User2(1,1);
			IAmoves[0] = 1 ; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[2][0] == map[1][1] && map[2][0] == 'Y' && !isOccupied(0,2)) {
			m = new User2(0,2);
			IAmoves[0] = 0 ; IAmoves[1] = 2 ;
			return 0;
		} 
		else if(map[1][1] == map[0][1] && map[0][1] == 'Y' && !isOccupied(2,1)) {
			m = new User2(2,1);
			IAmoves[0] = 2 ; IAmoves[1] = 1 ;
			return 0;
		} 	
		else if(map[1][1] == map[2][1] && map[2][1] == 'Y' && !isOccupied(0,1)) {
			m = new User2(0,1);
			IAmoves[0] = 0 ; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[2][1] == map[0][1] && map[2][1] == 'Y' && !isOccupied(1,1)) {
			m = new User2(1,1);
			IAmoves[0] = 1 ; IAmoves[1] = 1 ;
			return 0;
		}
		else if(map[1][1] == map[1][0] && map[1][1] == 'Y' && !isOccupied(1,2)) {
			m = new User2(1,2);
			IAmoves[0] = 1 ; IAmoves[1] = 2 ;
			return 0;
		} 	
		else if(map[1][1] == map[1][2] && map[1][1] == 'Y' && !isOccupied(1,0)) {
			m = new User2(1,0);
			IAmoves[0] = 1 ; IAmoves[1] = 0 ;
			return 0;
		}
		else if(map[1][2] == map[1][0] && map[1][0] == 'Y' && !isOccupied(1,1)) {
			m = new User2(1,1);
			IAmoves[0] = 1 ; IAmoves[1] = 1 ;
			return 0;
		}
		else {
			return -1;
		}
	}
}


