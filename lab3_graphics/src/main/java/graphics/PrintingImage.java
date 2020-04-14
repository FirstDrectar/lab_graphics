package graphics;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrintingImage extends Application{

	private HeaderBitmapImage image; 
	private int numberOfPixels;
	Color yellow = Color.rgb(247, 228, 24);
	Color Mouth = Color.rgb(249, 163, 22);
	Color blue = Color.rgb(202, 228, 248);
	Color dark = Color.rgb(0,0,0);
	public PrintingImage()
	{}
	
	public PrintingImage(HeaderBitmapImage image)
	{
		this.image = image;
	}
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ReadingImageFromFile.loadBitmapImage("src/main/resources/trajectory.bmp");
		this.image = ReadingImageFromFile.pr.image;
		int width = (int)this.image.getWidth();
		int height = (int)this.image.getHeight();
		int half = (int)image.getHalfOfWidth();
		Group root = new Group();
		Scene scene = new Scene (root, width, height);
		Circle cir;
		int let = 0;
		int let1 = 0;
		int let2 = 0;
		char[][] map = new char[width][height];

		BufferedInputStream reader = new BufferedInputStream (new FileInputStream("pixels.txt"));

		for(int i=0;i<height;i++)
		{
			for(int j=0;j<half;j++)
			{
				let = reader.read();
				let1 = let;
				let2 = let;
				let1 = let1&(0xf0);
				let1 = let1>>4;
				let2 = let2&(0x0f);
				if(j*2<width)
				{
					cir = new Circle ((j)*2,(height-1-i),1,Color.valueOf((returnPixelColor(let1))));

					if (returnPixelColor(let1) == "BLACK")
					{
						map[j*2][height-1-i] = '1';
						numberOfPixels++;
					}
					else
					{
						map[j*2][height-1-i] = '0';
					}
				}

				if(j*2+1<width)
				{
					cir = new Circle ((j)*2+1,(height-1-i),1,Color.valueOf((returnPixelColor(let2))));
					if (returnPixelColor(let2) == "BLACK")
					{
						map[j*2+1][height-1-i] = '1';
						numberOfPixels++;
					}
					else
					{
						map[j*2+1][height-1-i] = '0';
					}
				}
			}
		}
		primaryStage.setScene(scene);
		primaryStage.show();
		reader.close();

		int[][] black;
		black = new int[numberOfPixels][2];
		int lich = 0;

		BufferedOutputStream writer = new BufferedOutputStream (new FileOutputStream("map.txt"));
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				if (map[j][i] == '1')
				{
					black[lich][0] = j;
					black[lich][1] = i;
					lich++;
				}
				writer.write(map[j][i]);
			}
			writer.write(10);
		}
		writer.close();

		System.out.println("number of black color pixels = " + numberOfPixels);

		Path path2 = new Path();
		for (int l=0; l<numberOfPixels-1; l++)
		{
			path2.getElements().addAll(
					new MoveTo(black[l][0],black[l][1]),
					new LineTo(black[l+1][0],black[l+1][1])
			);
		}

		Circle sun = new Circle(200, 160, 80);
		sun.setFill(yellow);
		root.getChildren().add(sun);

		Path hand1= new Path(
				new MoveTo(110, 160),
				new ArcTo(110, 160,0, 70, 130, false, false ),
				new ArcTo(70, 130, 0, 75, 140, false, false ),
				new ArcTo(75, 140, 0, 60, 145, false, false ),
				new ArcTo(60, 145, 0, 75, 150, false, false ),
				new ArcTo(75, 150, 0, 60, 160, false, false ),
				new ArcTo(60, 160, 0, 75, 165, false, false ),
				new ArcTo(75, 165, 0, 60, 180, false, false ),
				new ArcTo(60, 180, 50, 110, 160, false, false )



		);
		hand1.fillProperty().set(yellow);
		root.getChildren().add(hand1);

		Path hand2= new Path(
				new MoveTo(290, 160),
				new ArcTo(290, 160,0, 330, 130, false, false ),
				new ArcTo(330, 130, 0, 325, 140, false, false ),
				new ArcTo(325, 140, 0, 340, 145, false, false ),
				new ArcTo(340, 145, 0, 325, 150, false, false ),
				new ArcTo(325, 150, 0, 340, 160, false, false ),
				new ArcTo(340, 160, 0, 325, 165, false, false ),
				new ArcTo(325, 165, 0, 340, 180, false, false ),
				new ArcTo(340, 180, 50, 290, 160, false, false )



		);
		hand2.fillProperty().set(yellow);
		root.getChildren().add(hand2);

		Path eye1 = new Path(
				new MoveTo(180, 140),
				new ArcTo(180, 140, 0, 160, 155, false, false ),
				new ArcTo(160, 155, 0, 166, 130, false, false )
		);
		eye1.fillProperty().set(dark);
		eye1.strokeWidthProperty().set(0);
		root.getChildren().add(eye1);

		Path eye2 = new Path(
				new MoveTo(220, 140),
				new ArcTo(220, 140, 0, 240, 155, false, false ),
				new ArcTo(240, 155, 0, 234, 130, false, false )
		);
		eye2.fillProperty().set(dark);
		eye2.strokeWidthProperty().set(0);
		root.getChildren().add(eye2);


		Path mouth = new Path(
				new MoveTo(170, 175),
				new ArcTo(170, 175, 0, 190, 200, false, false ),
				new ArcTo(190, 200, 0, 205, 195, false, false ),
				new ArcTo(210, 190, 0, 235, 175, false, false )
		);
		mouth.fillProperty().set(Mouth);
		mouth.strokeWidthProperty().set(0);
		root.getChildren().add(mouth);




		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(5000));
		pathTransition.setPath(path2);
		pathTransition.setNode(root);
		pathTransition.setAutoReverse(true);

		FadeTransition fadeTransitionHand1 = new FadeTransition(Duration.millis(3000), hand1);
		fadeTransitionHand1.setFromValue(1.0f);
		fadeTransitionHand1.setToValue(0.0f);
		FadeTransition fadeTransitionHand2 = new FadeTransition(Duration.millis(3000), hand2);
		fadeTransitionHand2.setFromValue(0.0f);
		fadeTransitionHand2.setToValue(1.0f);
		RotateTransition rotateTransitionEye1 = new RotateTransition(Duration.millis(1500), eye1);
		rotateTransitionEye1.setByAngle(360f);
		rotateTransitionEye1.setCycleCount(2);

		Path pathEye1 = new Path();
		pathEye1.getElements().add(new MoveTo(180, 140));
		pathEye1.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
		pathEye1.getElements().add(new CubicCurveTo(0, 120, 0, 240, 180, 140));

		PathTransition pathTransitioneye1 = new PathTransition();
		pathTransitioneye1.setDuration(Duration.millis(5000));
		pathTransitioneye1.setPath(pathEye1);
		pathTransitioneye1.setNode(eye1);

		pathTransitioneye1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransitioneye1.setCycleCount(Timeline.INDEFINITE);
		pathTransitioneye1.setAutoReverse(true);
		pathTransitioneye1.play();

		RotateTransition rotateTransitionHand1 = new RotateTransition(Duration.millis(1500), hand1);
		rotateTransitionHand1.setByAngle(360f);
		rotateTransitionHand1.setCycleCount(2);

		RotateTransition rotateTransitionHand2 = new RotateTransition(Duration.millis(1500), hand2);
		rotateTransitionHand2.setByAngle(-360f);
		rotateTransitionHand2.setCycleCount(2);



		RotateTransition rotateTransitionMouth = new RotateTransition(Duration.millis(2500), mouth);
		rotateTransitionMouth.setByAngle(-360f);
		rotateTransitionMouth.setCycleCount(2);

		ScaleTransition scaleTransitionRoot = new ScaleTransition(Duration.millis(5000), root);
		scaleTransitionRoot.setToX(0.4f);
		scaleTransitionRoot.setToY(0.4f);
		scaleTransitionRoot.setAutoReverse(true);

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(
				fadeTransitionHand1,
				fadeTransitionHand2,
				rotateTransitionHand1,
				rotateTransitionHand2,
				scaleTransitionRoot,
				rotateTransitionEye1,
				rotateTransitionMouth,
				pathTransition
		);
		parallelTransition.setCycleCount(Timeline.INDEFINITE);
		parallelTransition.setAutoReverse(true);
		parallelTransition.play();
	}
	
	private String returnPixelColor (int color)
	{
		String col = "BLACK";
		switch(color)
		   {
		      case 0: return "BLACK";
		      case 1: return "LIGHTCORAL";
		      case 2: return "GREEN";
		      case 3: return "BROWN";
		      case 4: return "BLUE";
		      case 5: return "MAGENTA";
		      case 6: return "CYAN";
		      case 7: return "LIGHTGRAY";
		      case 8: return "DARKGRAY";
		      case 9: return "RED";
		      case 10:return "LIGHTGREEN";
		      case 11:return "YELLOW";
		      case 12:return "LIGHTBLUE";
		      case 13:return "LIGHTPINK";
		      case 14:return "LIGHTCYAN";
		      case 15:return "WHITE";
		   }
		   return col;
	}
		
	public static void main (String args[]) 
	{
	   launch(args);
	}

}
