package project6;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import project6.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import project6.Morpion.MorpionShape;

public class MainController implements Initializable{

	ObservableList <String> players = FXCollections.observableArrayList("IA","Human");
	ObservableList <String> levels = FXCollections.observableArrayList("Easy","Medium","Hard");
	ObservableList <String> colors = FXCollections.observableArrayList("BLACK","RED","TAN","VIOLET","POWDERBLUE","GREEN");
	ObservableList <MorpionShape> shapes = FXCollections.observableArrayList(MorpionShape.DIAMOND,MorpionShape.STAR,MorpionShape.SQUARE,MorpionShape.TRIANGLE,MorpionShape.CIRCLE);
	//ObservableList <String> users = FXCollections.observableArrayList("User1","User2");
	@FXML
	private TextField player1Score;
	@FXML
	private TextField player2Score;
	@FXML
	private RadioButton Easy;
	@FXML
	private RadioButton Medium;
	@FXML 
	private RadioButton Hard;
	@FXML
	public ChoiceBox<String> player2 = new ChoiceBox <String>();
	@FXML
	public ChoiceBox<String> difficulty = new ChoiceBox <String>();
	@FXML
	public TextArea Prompt ;
	@FXML
	public TextArea Prompt2;
	@FXML
	public ChoiceBox<MorpionShape> shape1 = new ChoiceBox <MorpionShape>();
	@FXML
	public ChoiceBox<MorpionShape> shape2 = new ChoiceBox <MorpionShape>();
	@FXML
	public ChoiceBox<String> color1 = new ChoiceBox <String>();
	@FXML
	public ChoiceBox<String> color2 = new ChoiceBox <String>();
	@FXML
	public  GridPane grid = new GridPane();
	@FXML
	public Button button00 = new Button();
	@FXML
	public Button button01 = new Button();
	@FXML
	public Button button02 = new Button();
	@FXML
	public Button button10 = new Button();
	@FXML
	public Button button11 = new Button();
	@FXML
	public Button button12 = new Button();
	@FXML
	public Button button20 = new Button();
	@FXML
	public Button button21 = new Button();
	@FXML
	public Button button22 = new Button();

	//@FXML
	//public ChoiceBox<String> user = new ChoiceBox <String>();
	@FXML
	public ChoiceBox<String> color = new ChoiceBox <String>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Morpion.initWorld();
		shape1.setItems(shapes);
		shape1.setValue(shapes.get(4));
		shape2.setItems(shapes);
		shape2.setValue(shapes.get(1));
		color2.setItems(colors);
		color2.setValue(colors.get(5));
		color1.setItems(colors);
		color1.setValue(colors.get(1));
		shape2.setValue(shapes.get(1));
		player2.setItems(players);
		player2.setValue(players.get(0));
		difficulty.setItems(levels);
		difficulty.setValue(levels.get(0));
		try {
			newGame();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clearGrid() {
		double height = grid.getHeight()/3 - 10 ;
		double width = 	grid.getWidth()/3 - 10;
		for(int i = 0 ; i < 3 ; i++) {
			for (int j = 0 ; j < 3 ; j++) {
				Shape s = new Rectangle(width,height);
				s.setFill(javafx.scene.paint.Color.WHITESMOKE);
				s.setTranslateX(5);
				grid.add(s,i,j);
			}
		}
		button00.toFront();button01.toFront();button02.toFront();
		button10.toFront();button11.toFront();button12.toFront();
		button20.toFront();button21.toFront();button22.toFront();
	}

	public void replay(){
		clearGrid();
		for(int i = 0; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				Morpion.map[i][j] = '-';
			}
		}
		Morpion.popul.clear();
		Prompt.clear();
		Params.endGame = 3 ;
		Prompt2.clear();
		Morpion.cntPawn = 0 ;
	}

	public void newGame() throws InterruptedException {
		Params.endGame = 3 ;
		clearGrid() ;	
		for(int i = 0; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				Morpion.map[i][j] = '-';
			}
		}
		Morpion.popul.clear();
		Prompt2.clear();
		Prompt.clear();
		String level = difficulty.getSelectionModel().getSelectedItem();
		if(level.equals("Easy")) {
			Params.level = 1 ;
		} else if (level.equals("Medium")) {
			Params.level = 2 ;
		} else if (level.equals("Hard")) { 		
			Params.level = 3 ;
		}
		Prompt2.appendText("Difficulty : "+ level + "\n" + "Playing vs : " + player2.getSelectionModel().getSelectedItem()+ "\n");
		Params.IA = !(player2.getSelectionModel().getSelectedItem().equals("Human"));
		User1.score = 0 ;
		User2.score = 0 ;
		User1.setShape(shape1.getSelectionModel().getSelectedItem());
		Prompt2.appendText("User1 shape: " + shape1.getSelectionModel().getSelectedItem().toString() + "\n");
		User2.setShape(shape2.getSelectionModel().getSelectedItem());
		Prompt2.appendText("User2 shape: " + shape2.getSelectionModel().getSelectedItem().toString() + "\n");
		String coloru1 = color1.getSelectionModel().getSelectedItem();
		String coloru2 = color2.getSelectionModel().getSelectedItem();
		switch (coloru1){
		case "BLACK":
			User1.setColor (javafx.scene.paint.Color.BLACK);
			break;
		case "RED":
			User1.setColor (javafx.scene.paint.Color.RED);
			break;
		case "GREEN":
			User1.setColor (javafx.scene.paint.Color.GREEN);
			break;
		case "TAN":
			User1.setColor (javafx.scene.paint.Color.TAN);
			break;
		case "VIOLET":
			User1.setColor (javafx.scene.paint.Color.VIOLET);
			break;
		case "POWDERBLUE":
			User1.setColor (javafx.scene.paint.Color.POWDERBLUE);
			break;
		}
		switch (coloru2){
		case "BLACK":
			User2.setColor (javafx.scene.paint.Color.BLACK);
			break;
		case "RED":
			User2.setColor (javafx.scene.paint.Color.RED);
			break;
		case "GREEN":
			User2.setColor (javafx.scene.paint.Color.GREEN);
			break;
		case "TAN":
			User2.setColor (javafx.scene.paint.Color.TAN);
			break;
		case "VIOLET":
			User2.setColor (javafx.scene.paint.Color.VIOLET);
			break;
		case "POWDERBLUE":
			User2.setColor (javafx.scene.paint.Color.POWDERBLUE);
			break;
		}

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						player1Score.setText(User1.score+"");
						player2Score.setText(User2.score+"");
					}

				});

			}

		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 500);
	}


	public void MessageEnd(){
		Prompt2.clear();
		if(Params.endGame == 2) {
			Prompt2.appendText("Game won by User2 \n");
			User2.score += 1 ;
		}
		else if (Params.endGame == 1 ){
			Prompt2.appendText("Game won by User 1 \n");
			User1.score += 1 ;
		}
		else if (Params.endGame == 0){
			Prompt2.appendText("It's a draw! \n");
		}
	}


	public void play00() {
		if(Params.endGame == 3) {
			Prompt.clear();
			if(Morpion.isOccupied(0,0))
				Prompt.appendText("Spot already occupied!");
			else {
				Prompt.appendText("User " +Params.Player + " played");
				drawShape(Params.Player,0,0);
				Params.endGame = Morpion.endGame();
				Morpion.play(0,0);
				if((Params.endGame == 3 || Params.endGame == 2) && Params.IA)
					drawShape(2,Morpion.IAmoves[0],Morpion.IAmoves[1]);
				button00.toFront();
				MessageEnd();
			}
			Morpion.displayWorld();
		} else {
			Prompt.clear();
			Prompt.appendText("Game Ended. Replay or start a New Game");
			//System.out.println("Game Ended. Replay or start a New Game");
		}
	}

	public void play10() {
		if(Params.endGame == 3) {
			Prompt.clear();
			if(Morpion.isOccupied(1,0))
				Prompt.appendText("Spot already occupied!");
			else {
				Prompt.appendText("User " +Params.Player + " played");
				drawShape(Params.Player,1,0);
				Morpion.play(1,0);
				if( (Params.endGame == 3 || Params.endGame == 2)&& Params.IA)
					drawShape(2,Morpion.IAmoves[0],Morpion.IAmoves[1]);
				button10.toFront();
				MessageEnd();
			}
			Morpion.displayWorld();
		} else {
			Prompt.clear();
			Prompt.appendText("Game Ended. Replay or start a New Game");
		}
	}	

	public void play20() {
		if(Params.endGame == 3) {
			Prompt.clear();
			if(Morpion.isOccupied(2,0))
				Prompt.appendText("Spot already occupied!");
			else {
				Prompt.appendText("User " +Params.Player + " played");
				drawShape(Params.Player,2,0);
				Morpion.play(2,0);
				if((Params.endGame == 3 || Params.endGame == 2) && Params.IA)
					drawShape(2,Morpion.IAmoves[0],Morpion.IAmoves[1]);
				button20.toFront();
				MessageEnd();
			}
			Morpion.displayWorld();
		} else {
			Prompt.clear();
			Prompt.appendText("Game Ended. Replay or start a New Game");
		}
	}

	public void play01() {
		if(Params.endGame == 3) {
			Prompt.clear();
			if(Morpion.isOccupied(0,1))
				Prompt.appendText("Spot already occupied!");
			else {
				Prompt.appendText("User " +Params.Player + " played");
				drawShape(Params.Player,0,1);
				Morpion.play(0,1);
				if((Params.endGame == 3 || Params.endGame == 2) && Params.IA)
					drawShape(2,Morpion.IAmoves[0],Morpion.IAmoves[1]);
				button01.toFront();
				MessageEnd();
			}
			Morpion.displayWorld();
		} else {
			Prompt.clear();
			Prompt.appendText("Game Ended. Replay or start a New Game");
		}
	}

	public void play11() {
		if(Params.endGame == 3) {
			Prompt.clear();
			if(Morpion.isOccupied(1,1))
				Prompt.appendText("Spot already occupied!");
			else {
				Prompt.appendText("User " +Params.Player + " played");
				drawShape(Params.Player,1,1);
				Morpion.play(1,1);
				if((Params.endGame == 3 || Params.endGame == 2) && Params.IA)
					drawShape(2,Morpion.IAmoves[0],Morpion.IAmoves[1]);
				button11.toFront();
				MessageEnd();
			}
			Morpion.displayWorld();
		} else {
			Prompt.clear();
			Prompt.appendText("Game Ended. Replay or start a New Game");
		}
	}

	public void play21() {
		if(Params.endGame == 3) {
			Prompt.clear();
			if(Morpion.isOccupied(2,1))
				Prompt.appendText("Spot already occupied!");
			else {
				Prompt.appendText("User " +Params.Player + " played");
				drawShape(Params.Player,2,1);
				Morpion.play(2,1);
				if((Params.endGame == 3 || Params.endGame == 2) && Params.IA)
					drawShape(2,Morpion.IAmoves[0],Morpion.IAmoves[1]);
				button21.toFront();
				MessageEnd();
			}
			Morpion.displayWorld();
		} else {
			Prompt.clear();
			Prompt.appendText("Game Ended. Replay or start a New Game");
		}
	}

	public void play02() {
		if(Params.endGame == 3) {
			Prompt.clear();
			if(Morpion.isOccupied(0,2))
				Prompt.appendText("Spot already occupied!");
			else {
				Prompt.appendText("User " +Params.Player + " played");
				drawShape(Params.Player,0,2);
				Morpion.play(0,2);
				if((Params.endGame == 3 || Params.endGame == 2) && Params.IA)
					drawShape(2,Morpion.IAmoves[0],Morpion.IAmoves[1]);
				button02.toFront();
				MessageEnd();
			}
			Morpion.displayWorld();
		} else {
			Prompt.clear();
			Prompt.appendText("Game Ended. Replay or start a New Game");
		}
	}

	public void play12() {
		if(Params.endGame == 3) {
			Prompt.clear();
			if(Morpion.isOccupied(1,2))
				Prompt.appendText("Spot already occupied!");
			else {
				Prompt.appendText("User " +Params.Player + " played");
				drawShape(Params.Player,1,2);
				Morpion.play(1,2);
				if((Params.endGame == 3 || Params.endGame == 2) && Params.IA)
					drawShape(2,Morpion.IAmoves[0],Morpion.IAmoves[1]);
				button12.toFront();
				MessageEnd();
			}
			Morpion.displayWorld();
		} else {
			Prompt.clear();
			Prompt.appendText("Game Ended. Replay or start a New Game");
		}
	}

	public void play22() {
		if(Params.endGame == 3) {
			Prompt.clear();
			if(Morpion.isOccupied(2,2))
				Prompt.appendText("Spot already occupied!");
			else {
				Prompt.appendText("User " +Params.Player + " played");
				drawShape(Params.Player,2,2);
				Morpion.play(2,2);
				if((Params.endGame == 3 || Params.endGame == 2) && Params.IA)
					drawShape(2,Morpion.IAmoves[0],Morpion.IAmoves[1]);
				button22.toFront();
				MessageEnd();
			}
			Morpion.displayWorld();
		} else {
			Prompt.clear();
			Prompt.appendText("Game Ended. Replay or start a New Game");
		}
	}

	public void drawShape(int player,int y,int x) {
		double height = grid.getHeight()/3 - (20*(grid.getHeight()/3))/100 ;
		double width = 	grid.getWidth()/3 - (20*(grid.getWidth()/3))/100;
		Shape s;
		if (player == 1) {
			System.out.println("Drawing User 1");
			switch (User1.shape) {
			case CIRCLE:
				s = new Circle(Math.min(width, height)/2);
				s.setFill(User1.Color1);
				s.setTranslateX((10*(grid.getWidth()/3))/100);
				grid.add(s,x,y);
				break;
			case SQUARE:
				s = new Rectangle(width,height);
				s.setTranslateX((10*(grid.getWidth()/3))/100);
				s.setFill(User1.Color1);
				grid.add(s,x,y);
				break;		
			case STAR:
				Polygon star = new Polygon();
				star.getPoints().addAll(new Double[]{
						0.0*height, 0.5*width, 
						0.3*height, 0.6*width,
						0.25*height, 1.0*width,
						0.6*height, 0.75*width, 
						1.0*height, 1.0*width,
						0.7*height, 0.5*width,
						1.0*height,0.0*width,
						0.6*height, 0.25*width,
						0.25*height, 0.0*width,
						0.3*height,0.4*width,
				});
				star.setFill(User1.Color1);
				star.setTranslateX((10*(grid.getWidth()/3))/100);
				grid.add(star,x,y);
				break;
			case DIAMOND:
				Polygon diamond = new Polygon();
				diamond.getPoints().addAll(new Double[]{
						0.0*height, 0.5*width, 
						0.5*height, 1.0*width,
						1.0*height, 0.5*width,
						0.5*height,0.0});
				diamond.setTranslateX((10*(grid.getWidth()/3))/100);
				diamond.setFill(User1.Color1);
				grid.add(diamond,x,y);
				break;
			case TRIANGLE:
				Polygon polygon = new Polygon();
				polygon.getPoints().addAll(new Double[]{
						0.5*height,0.0,
						0.0, 1.0*height,
						1.0*width, 1.0*height });
				polygon.setFill(User1.Color1);
				polygon.setTranslateX((10*(grid.getWidth()/3))/100);
				grid.add(polygon,x,y);
				break;
			}
		} else {
			System.out.println("Drawing User 2");
			switch (User2.shape) {
			case CIRCLE:
				s = new Circle(Math.min(width, height)/2);
				s.setFill(User2.Color2);
				s.setTranslateX((10*(grid.getWidth()/3))/100);
				grid.add(s,x,y);
				break;
			case SQUARE:
				s = new Rectangle(width,height);
				s.setTranslateX((10*(grid.getWidth()/3))/100);
				s.setFill(User2.Color2);
				grid.add(s,x,y);
				break;		
			case STAR:
				Polygon star = new Polygon();
				star.getPoints().addAll(new Double[]{
						0.0*height, 0.5*width, 
						0.3*height, 0.6*width,
						0.25*height, 1.0*width,
						0.6*height, 0.75*width, 
						1.0*height, 1.0*width,
						0.7*height, 0.5*width,
						1.0*height,0.0*width,
						0.6*height, 0.25*width,
						0.25*height, 0.0*width,
						0.3*height,0.4*width,
				});
				star.setFill(User2.Color2);
				star.setTranslateX((10*(grid.getWidth()/3))/100);
				grid.add(star,x,y);
				break;
			case DIAMOND:
				Polygon diamond = new Polygon();
				diamond.getPoints().addAll(new Double[]{
						0.0*height, 0.5*width, 
						0.5*height, 1.0*width,
						1.0*height, 0.5*width,
						0.5*height,0.0});
				diamond.setTranslateX((10*(grid.getWidth()/3))/100);
				diamond.setFill(User2.Color2);
				grid.add(diamond,x,y);
				break;
			case TRIANGLE:
				Polygon polygon = new Polygon();
				polygon.getPoints().addAll(new Double[]{
						0.5*height,0.0,
						0.0, 1.0*height,
						1.0*width, 1.0*height });
				polygon.setFill(User2.Color2);
				polygon.setTranslateX((10*(grid.getWidth()/3))/100);
				grid.add(polygon,x,y);
				break;
			}
		}
	}

	public void setEasy(){
		Params.level = 1;
		Prompt.clear();
		Prompt.appendText("Difficulty is set to easy now");
	}

	public void setMedium(){
		Params.level = 2;
		Prompt.clear();
		Prompt.appendText("Difficulty is set to medium now");
	}
	public void setHard(){
		Params.level = 3;
		Prompt.clear();
		Prompt.appendText("Difficulty is set to hard now");
	}

	public void exit() {
		System.exit(0);
	}

}


