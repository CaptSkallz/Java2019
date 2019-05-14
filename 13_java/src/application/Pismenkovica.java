package application;
	
import java.io.*;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;


public class Pismenkovica extends Application {
	
	boolean running;
	StavHry hra;
	Kanv kanv;
	Label cas, skore, slovo;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			running = true;
			hra = new StavHry("pismenkovica1.txt");

			BorderPane root = new BorderPane();
			Button load = new Button("Load");
			Button save = new Button("Save");
			load.setOnAction(event -> { load(); });
			save.setOnAction(event -> { save(); });
			skore = new Label("skore: ");
			cas = new Label("cas:");
			slovo = new Label("Vytvor slovo: " + hra.slova[hra.inds]);
			FlowPane bottom = new FlowPane( load, save, skore, cas, slovo );
			bottom.setHgap(5);
			kanv = new Kanv(this, hra);
			root.setBottom(bottom);
			root.setCenter(kanv);			
			Scene scene = new Scene(root);
			scene.widthProperty().addListener(ov -> kanv.setW(scene.getWidth()));
	        scene.heightProperty().addListener(ov -> kanv.setH(scene.getHeight() - bottom.getHeight()));
	        primaryStage.setOnCloseRequest(event -> { running = false; });
			primaryStage.setScene(scene);
			primaryStage.show();
			(new Thread(kanv)).start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void load()
	{
		try { hra = StavHry.precitaj(); } catch (Exception e) { e.printStackTrace(); }
		kanv.setStav(hra);
	}
	
	private void save()
	{
		try { hra.zapis(); } catch (Exception e) { e.printStackTrace(); }
	}
	
	public void aktualizujCas()
	{
		Platform.runLater(new Runnable() { public void run() { 
											cas.setText("cas: " + hra.zostavaSekund); }});
	}

	public void aktualizujSlovo()
	{
		Platform.runLater(new Runnable() { public void run() {
			slovo.setText(running?("Vytvor slovo: " + hra.slova[hra.inds]):"Koniec"); }});
	}
	
	public void aktualizujSkore()
	{
		Platform.runLater(new Runnable() { public void run() { 
											skore.setText("skore: " + hra.skore); }});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

class Kanv extends Canvas implements Runnable
{
	Pismenkovica app;
	StavHry hra;
	double sirkaPola, vyskaPola;
	
	public Kanv(Pismenkovica app, StavHry hra)
	{
		this.app = app;
		this.hra = hra;
		setW(500);
		setH(500);
		vykresli();
		setOnMouseClicked(e->{klik(e.getX(), e.getY()); e.consume();});
	}
	
	public void klik(double x, double y)
	{
		if (!app.running) return;
		int r = (int)(y / vyskaPola) - 1;
		int s = (int)(x / sirkaPola) - 1;
		if ((r == -1) && (s >= 0) && (s < hra.n)) smeromDolu(s);
		else if ((r == hra.n) && (s >= 0) && (s < hra.n)) smeromHore(s);
		else if ((s == -1) && (r >= 0) && (r < hra.n)) smeromVpravo(r);
		else if ((s == hra.n) && (r >= 0) && (r < hra.n)) smeromVlavo(r);
		najdiSlovo();
		vykresli();
	}
	
	private void smeromDolu(int s)
	{
		char odloz = hra.plocha[hra.n - 1][s];
		for (int i = hra.n - 1; i >= 1; i--)
			hra.plocha[i][s] = hra.plocha[i - 1][s];
		hra.plocha[0][s] = odloz;		
	}
	
	private void smeromHore(int s)
	{
		char odloz = hra.plocha[0][s];
		for (int i = 0; i < hra.n - 1; i++)
			hra.plocha[i][s] = hra.plocha[i + 1][s];
		hra.plocha[hra.n - 1][s] = odloz;
	}
	
	private void smeromVpravo(int r)
	{
		char odloz = hra.plocha[r][hra.n - 1];
		for (int i = hra.n - 1; i >= 1; i--)
			hra.plocha[r][i] = hra.plocha[r][i - 1];
		hra.plocha[r][0] = odloz;
	}
	
	private void smeromVlavo(int r)
	{
		char odloz = hra.plocha[r][0];
		for (int i = 0; i < hra.n - 1; i++)
			hra.plocha[r][i] = hra.plocha[r][i + 1];
		hra.plocha[r][hra.n - 1] = odloz;
	}

	private static final int[] dr = new int[] { 0, 1, 1, -1 };
	private static final int[] ds = new int[] { 1, 0, 1, 1 };
	
	private void najdiSlovo()
	{
		String slovo = hra.slova[hra.inds];
		for (int smer = 0; smer < 4; smer++)
		  for (int r = 0; r < hra.n; r++)
			for (int s = 0; s < hra.n; s++)
			{
				boolean nasiel = true;
			    int r1 = r, s1 = s;
			    int c;
				for (c = 0; c < slovo.length(); c++, r1 += dr[smer], s1 += ds[smer])
					if ((r1 == hra.n) || (s1 == hra.n) || (r1 == -1)) { nasiel = false; break; }
					else if (slovo.charAt(c) != hra.plocha[r1][s1]) { nasiel = false; break; }
				if (nasiel)
				{
					dalsieSlovo(r, s, smer);
					break;
				}
			}
	}
	
	private void dalsieSlovo(int r, int s, int smer)
	{
		hra.skore++;
		if (hra.inds < hra.slova.length - 1) 
		{
			for (int i = 0; i < hra.slova[hra.inds].length(); i++)
				hra.plocha[r + dr[smer] * i][s + ds[smer] * i] = hra.pismena.charAt(hra.indp++);
			hra.inds++;
		}
		else app.running = false;
		hra.startujCas();
		app.aktualizujCas();
		app.aktualizujSlovo();
		app.aktualizujSkore();
		vykresli();
	}

	@Override
	public void run()
	{
		long cas = System.currentTimeMillis();
		hra.startujCas();
		while (app.running)
		{
			try { Thread.sleep(20); } catch (Exception e) { e.printStackTrace();}
			long novyCas = System.currentTimeMillis();
			if (novyCas != cas)
			{
				hra.aktualizujCas(novyCas);
				app.aktualizujCas();
				if (hra.zostavaSekund == 0) 
				{
					app.running = false;
					break;
				}
			}
		}
	}
	
	void setW(double newW)
	{
		setWidth(newW);
		sirkaPola = newW / (hra.n + 2);
		vykresli();
	}
	
	void setH(double newH)
	{
		setHeight(newH);
		vyskaPola = newH / (hra.n + 2);
		vykresli();
	}
	
    public void setStav(StavHry s)
    {
    	hra = s;
    	app.aktualizujSkore();
    	app.aktualizujSlovo();
    	app.aktualizujCas();
    	vykresli();
    }
	
	public void vykresli()
	{
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0,  0,  getWidth(), getHeight());
		gc.setFill(Color.BLACK);
		for (int i = 1; i <= hra.n + 1; i++)
		{
			gc.fillRect(i * sirkaPola, 0, 2, getHeight());
			gc.fillRect(0,  i * vyskaPola, getWidth(), 2);
		}
		
		gc.setFill(Color.BLUE);
		for (int i = 0; i < hra.n; i++)
			for (int j = 0; j < hra.n; j++)
				gc.fillText("" + hra.plocha[i][j], (j + 1.4) * sirkaPola, (i + 1.7) * vyskaPola);
		
		gc.setFill(Color.ORANGE);
		for (int i = 1; i <= hra.n; i++)
		{
			gc.fillPolygon(new double[] { i * sirkaPola + 5, (i + 1) * sirkaPola - 5, (i + 0.5) * sirkaPola }, 
					       new double[] { 5, 5, vyskaPola - 5}, 3);
			gc.fillPolygon(new double[] { i * sirkaPola + 5, (i + 1) * sirkaPola - 5, (i + 0.5) * sirkaPola }, 
				       new double[] { getHeight() - 5, getHeight() - 5, getHeight() - vyskaPola + 5}, 3);
			gc.fillPolygon(new double[] { 5, 5, sirkaPola - 5},
					       new double[] { i * vyskaPola + 5, (i + 1) * vyskaPola - 5, (i + 0.5) * vyskaPola }, 
				           3);
			gc.fillPolygon(new double[] { getWidth() - 5, getWidth() - 5, getWidth() - sirkaPola + 5},
				       new double[] { i * vyskaPola + 5, (i + 1) * vyskaPola - 5, (i + 0.5) * vyskaPola }, 
			           3);
		}
	}
}

class StavHry implements Serializable
{
	private static final long serialVersionUID = -5579048469224340943L;

	long zaciatokCasu;
	char[][] plocha;
	String pismena;
	int indp;
	int n;
	String[] slova;
	int inds;
	long zostavaSekund;
	int skore;
	
	public static final long casNaTah = 60L;
	
	public void startujCas()
	{
		zaciatokCasu = System.currentTimeMillis();
		zostavaSekund = casNaTah;
	}
	
	public void aktualizujCas(long teraz)
	{
		zostavaSekund = casNaTah - (teraz - zaciatokCasu) / 1000;
	}
	
	public StavHry(String fileName)
	{
		nacitaj(fileName);
		skore = 0;
	}
	
	public void nacitaj(String fileName)
	{
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			
			String ln = in.readLine();
			n = Integer.parseInt(ln);
			plocha = new char[n][n];
			for (int i = 0; i < n; i++)
			{
				ln = in.readLine();
				for (int j = 0; j < n; j++)
					plocha[i][j] = ln.charAt(j);
			}
			pismena = in.readLine();
			indp = 0;
			
			ArrayList<String> slv = new ArrayList<String>();
			while (in.ready())
				slv.add(in.readLine());
			slova = slv.toArray(new String[0]);
			inds = 0;
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void zapis() throws Exception
    {
    	ObjectOutputStream w = new ObjectOutputStream(new FileOutputStream("pismenkovica.bin"));
    	w.writeObject(this);
    	w.close();
    }
    
    public static StavHry precitaj() throws Exception
    {
    	ObjectInputStream r = new ObjectInputStream(new FileInputStream("pismenkovica.bin"));
    	StavHry s = (StavHry)r.readObject();
    	r.close();
    	s.zaciatokCasu = System.currentTimeMillis() - (casNaTah - s.zostavaSekund) * 1000;
    	return s;
    }
}
