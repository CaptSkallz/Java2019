package Skuska4_2015;

import java.io.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;


public class Atomix extends Application 
{
	Label hotovo;
	Label cas;
	CheckBox bonus;
	
	boolean running;
	
	StavHry hra;
	Kanv kanv;
	
	@Override
	public void start(Stage primaryStage) 
	{
		try {		
			running = true;
			BorderPane root = new BorderPane();
			Button load = new Button("Load");
			Button save = new Button("Save");
			bonus = new CheckBox("bonus");
			load.setOnAction(event -> { load(); });
			save.setOnAction(event -> { save(); });
			hotovo = new Label("      ");
			cas = new Label("cas: ");			
			FlowPane bottom = new FlowPane( load, save, cas, bonus, hotovo );
			bottom.setHgap(5);
			hra = new StavHry(1);
			kanv = new Kanv(this);
			root.setBottom(bottom);
			root.setCenter(kanv);			
			Scene scene = new Scene(root);
	        primaryStage.setOnCloseRequest(event -> { running = false; });
			primaryStage.setScene(scene);
			primaryStage.setTitle("Vodný atomix");
			primaryStage.show();
			Thread t = new Thread(kanv);
			t.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void load()
	{
		if (kanv.animovany != 0) return;
		hra = StavHry.precitaj();
		kanv.vykresli();
	}
	
	private void save()
	{
		if (kanv.animovany != 0) return;
		hra.zapis();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

class Kanv extends Canvas implements Runnable
{
	Atomix atomix;
	Image HR, HL, HT, HB, OTB, OLR;
	boolean selected, novyLevel;
	int selRow, selCol;
	
	char animovany;
	int animRow, animCol, animDestRow, animDestCol, animDx, animDy;
	
	public Kanv(Atomix atomix)
	{
		this.atomix = atomix; 
		animovany = 0;
		HR = new Image("HR.png");
		HL = new Image("HL.png");
		HT = new Image("HT.png");
		HB = new Image("HB.png");
		OTB = new Image("OTB.png");
		OLR = new Image("OLR.png");
		setWidth(55 * atomix.hra.plocha[0].length);
		setHeight(55 * atomix.hra.plocha.length);
		setOnMouseClicked(e->{click(e.getX(), e.getY());});
		vykresli();
	}
	
	public void setStav(StavHry hra)
	{
		atomix.hra = hra;
	}	

	private synchronized void click(double x, double y)
	{
		if (novyLevel) return;
		if (animovany != 0) return;
		int row = spracujSuradnicuY(y);
		int col = spracujSuradnicuX(x);		
		if ((!selected) && (atomix.hra.jeAtom(row, col)))
		{			 
			selected = true;
			selRow = row;
			selCol = col;
		}
		else
		{
			selected = false;
			if ((selRow == row) && (selCol != col))
			{
				int dx = (col - selCol) / Math.abs(col - selCol);				
				int k = 0;
				while (atomix.hra.plocha[selRow][selCol + (k + 1) * dx] == 'u') k++;				
				if (k > 0)
				{
					if (atomix.bonus.isSelected())
					{
						animovany = atomix.hra.plocha[selRow][selCol];
						animRow = selRow * 20;
						animCol = selCol * 20;
						animDestRow = selRow * 20;
						animDestCol = (selCol + k * dx) * 20;
						animDx = dx;
						animDy = 0;
						atomix.hra.plocha[selRow][selCol] = 'u';
					}
					else
					{
						atomix.hra.plocha[selRow][selCol + k * dx] = atomix.hra.plocha[selRow][selCol];
						atomix.hra.plocha[selRow][selCol] = 'u';
						if (atomix.hra.hotovo(selRow, selCol + k * dx)) novyLevel = true;
					}
				}
			}
			else if ((selRow != row) && (selCol == col))
			{
				int dy = (row - selRow) / Math.abs(row - selRow);				
				int k = 0;
				while (atomix.hra.plocha[selRow + (k + 1) * dy][selCol] == 'u') k++;				
				if (k > 0)
				{
					if (atomix.bonus.isSelected())
					{
						animovany = atomix.hra.plocha[selRow][selCol];
						animRow = selRow * 20;
						animCol = selCol * 20;
						animDestRow = (selRow + k * dy) * 20;
						animDestCol = selCol * 20;
						animDx = 0;
						animDy = dy;
						atomix.hra.plocha[selRow][selCol] = 'u';
					}
					else
					{
						atomix.hra.plocha[selRow + k * dy][selCol] = atomix.hra.plocha[selRow][selCol];
						atomix.hra.plocha[selRow][selCol] = 'u';
						if (atomix.hra.hotovo(selRow + k * dy, selCol)) novyLevel = true;
					}
				}
			}
		}
		vykresli();
	}
	
	private int spracujSuradnicuX(double x) 
	{
		double sirkaPola= this.getWidth()/atomix.hra.plocha[0].length;
		return (int) (x/sirkaPola);		
	}
	
	private int spracujSuradnicuY(double y) 
	{
		double vyskaPola= this.getHeight()/atomix.hra.plocha.length;
		return (int) (y/vyskaPola);		
	}
	
	public void run()
	{
		atomix.hra.zaciatok = System.currentTimeMillis();
		while (atomix.running)
		{
			try{ Thread.sleep(20); } catch(Exception e) { e.printStackTrace(); }
			if (novyLevel)
			{				
				Platform.runLater(new Runnable() { public void run() {
					atomix.hotovo.setText("Level splnený");
				}});
				if (atomix.hra.level < atomix.hra.maxLevel)
				{
					try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace();}
					atomix.hra = new StavHry(atomix.hra.level + 1);
					Platform.runLater(new Runnable() { public void run() {
						atomix.hotovo.setText(" ");
						vykresli();
					}});			
					novyLevel = false;
				}
				else return;
			}
			if (atomix.hra.dokonca == 0) continue;
			long novyCas = (System.currentTimeMillis() - atomix.hra.zaciatok)/1000;
			if ((atomix.hra.celkovo - novyCas) < atomix.hra.dokonca){				
				atomix.hra.dokonca--;
				Platform.runLater(new Runnable(){
					public void run(){
						atomix.cas.setText("cas: " + atomix.hra.dokonca + " ");
					}
				});;
			}
			if (animovany != 0)
			{
				animRow += animDy;
				animCol += animDx;
				if ((animRow == animDestRow) && (animCol == animDestCol))
				{
					atomix.hra.plocha[animRow / 20][animCol / 20] = animovany;
					animovany = 0;
					if (atomix.hra.hotovo(animRow / 20, animCol / 20)) novyLevel = true;
				}
				Platform.runLater(new Runnable() { public void run() { vykresli(); } });
			}
		}
	}
	
	public void vykresli()
	{
		double w = getWidth();
		double h = getHeight();
		
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.PINK);
		gc.fillRect(0, 0, w, h);
		if (selected)
		{
			gc.setFill(Color.ORANGE);
			gc.fillRect(55 * selCol, 55 * selRow, 55, 55);
		}
		
		for (int i=0; i<atomix.hra.plocha.length; i++){
			for (int j=0; j<atomix.hra.plocha[0].length; j++){
				vykresliBunku(gc, i, j, atomix.hra.plocha[i][j]);
			}
		}
		if (atomix.bonus.isSelected())
			vykresliBunku(gc, animRow / 20.0, animCol / 20.0, animovany);
	}
	
	private void vykresliBunku(GraphicsContext gc, double i, double j, char bunka)
	{
		switch (bunka)
		{
		case 'p': gc.setFill(Color.GREEN);
		          gc.fillRect(j * 55, i * 55, 55, 55);
			break;
		case 's': gc.setFill(Color.BLUE);
                  gc.fillRect(j * 55, i * 55, 55, 55);
			break;
		case '|': gc.drawImage(OTB, j * 55, i * 55);
			break;
		case '-': gc.drawImage(OLR, j * 55, i * 55);				
			break;
		case '>': gc.drawImage(HR, j * 55, i * 55);
			break;
		case '<': gc.drawImage(HL, j * 55, i * 55);
			break;
		case '^': gc.drawImage(HT, j * 55, i * 55);
			break;
		case 'v': gc.drawImage(HB, j * 55, i * 55);
			break;
		}
	}
}

class StavHry implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	char[][] plocha;
	int n, m;
	int zostava;
	int level;
	int maxLevel = 3;
	
	int celkovo, dokonca;
	long zaciatok;
	
	public boolean jeAtom(int row, int col)
	{
		char c = plocha[row][col];
		return (c != 'p') && (c != 'u') && (c != 's');
	}
	
	public boolean hotovo(int r, int s)
	{
		int minr = r - 2;
		int mins = s - 2;
		while (minr < 0) minr++;
		while (mins < 0) mins++;
		int maxr = r;
		int maxs = s;
		while (maxr + 2 > plocha.length) maxr--;
		while (maxs + 2 > plocha[0].length) maxs--;
				
		for (int row = minr; row <= maxr + 2; row++)
			for (int col = mins; col <= maxs; col++)
				if ((plocha[row][col] == '>') &&
					(plocha[row][col + 1] == '-') &&
					(plocha[row][col + 2] == '<'))
					return true;
		for (int row = minr; row <= maxr; row++)
			for (int col = mins; col <= maxs + 2; col++)
				if ((plocha[row][col] == 'v') &&
					(plocha[row + 1][col] == '|') &&
					(plocha[row + 2][col] == '^'))
					return true;
		return false;
	}
	
	public StavHry(int level)
	{
		try {
			this.level = level;
			String fileName = "level" + level + ".txt";
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String[] lns = in.readLine().split(" ");
			n = Integer.parseInt(lns[0]);
			m = Integer.parseInt(lns[1]);
			plocha = new char[n][m];
			for (int i = 0; i < n; i++)
			{
				String ln = in.readLine();
				for (int j = 0; j < m; j++)
					plocha[i][j] = ln.charAt(j);					
			}
			String ln = in.readLine();
			in.close();
			celkovo = Integer.parseInt(ln);
			dokonca = celkovo;
			zaciatok = System.currentTimeMillis();
		}
        catch (Exception e)
        {
        	e.printStackTrace();
        }
	}
	
	public void zapis() 
    {	
		try {
	    	ObjectOutputStream w = new ObjectOutputStream(
	    			new FileOutputStream("atomix.bin"));
	    	w.writeObject(this);
	    	w.close();
		} catch (Exception e) { e.printStackTrace(); }
    }
    
    public static StavHry precitaj()
    {
    	try {
	    	ObjectInputStream r = new ObjectInputStream(
	    			new FileInputStream("atomix.bin"));
	    	StavHry s = (StavHry)r.readObject();
	    	r.close();
	    	s.zaciatok = System.currentTimeMillis() - 
	    			(s.celkovo - s.dokonca)*1000;
	    	return s;
    	} catch (Exception e) { e.printStackTrace(); return null; } 
    }
}
