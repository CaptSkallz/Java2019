package Skuska1_2015;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;


public class Zajace extends Application {
	
	Label otoceni; 
	Label cas;
	Label hotovo;
	StavHry hra;
	Kanv kanv;
	
	boolean running;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			running = true;
			BorderPane root = new BorderPane();
			Button load = new Button("Load");
			Button save = new Button("Save");
			load.setOnAction(event -> { load(); });
			save.setOnAction(event -> { save(); });
			otoceni = new Label("otoceni: ");
			cas = new Label("cas: ");
			hotovo = new Label("      ");
			FlowPane bottom = new FlowPane(load, save, otoceni, cas, hotovo);
			bottom.setHgap(5);
			hra = new StavHry("zajace.txt");
			kanv = new Kanv(500, 500, hra, this);
			root.setBottom(bottom);
			root.setCenter(kanv);			
			Scene scene = new Scene(root);
			scene.widthProperty().addListener(ov -> kanv.setW(scene.getWidth()));
	        scene.heightProperty().addListener(ov -> kanv.setH(scene.getHeight() - bottom.getHeight()));
			primaryStage.setOnCloseRequest(event -> { running = false; });	
			primaryStage.setScene(scene);
			primaryStage.show();			
			(new Thread(kanv)).start();
			if (hra.hotovo) zobrazHotovo();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	void zobrazHotovo()
	{
		hotovo.setText("hotovo");
		running = false;
	}
	
	void load(){
		try {
			kanv.setStav(StavHry.precitaj("zajace.bin"));
			kanv.vykresli();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	void save(){
		try {
			kanv.hra.zapis("zajace.bin");
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	void aktualizujOtoceni()
	{
		otoceni.setText("otoceni: " + hra.otoceni);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

class StavHry implements Serializable
{	

	private static final long serialVersionUID = 1L;

	char[][] plocha;
	
	ArrayList<Integer> xZajace;
	ArrayList<Integer> yZajace;
	ArrayList<Integer> xKapusty;
	ArrayList<Integer> yKapusty;
	
	int otoceni;
	long zaciatok;
	int doKonca; 
	int celkovo;
	
	boolean hotovo;
	
	public StavHry(String menoSuboru) 
	{		
		hotovo = false;
		Random rnd = new Random();
		BufferedReader in;		
		char c;
		try {
			in = new BufferedReader(new FileReader(menoSuboru));
			int n = Integer.parseInt( in.readLine());
			doKonca = 60;
			celkovo = doKonca;
			plocha = new char[n][n];
			xKapusty = new ArrayList<Integer>();
			yKapusty = new ArrayList<Integer>();
			xZajace = new ArrayList<Integer>();
			yZajace = new ArrayList<Integer>();
			
			for (int i=0; i<n; i++){
				String ln=in.readLine();
				for (int j=0;j<n;j++){
					c=ln.charAt(j);
					if (c!='.'){
						plocha[i][j]=c;
						if (c == 'z')
						{
							xZajace.add(j);
							yZajace.add(i);
						}
						else if (c == 'k')
						{
							xKapusty.add(j);
							yKapusty.add(i);
						}
					}
					else plocha[i][j] = (char)(rnd.nextInt(4) + '0');								
				}
			}
			
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		zistiZajace();
		zaciatok = System.currentTimeMillis();
	}
	
    public void zapis(String subor) throws Exception
    {
    	ObjectOutputStream w = new ObjectOutputStream(
    			new FileOutputStream(subor));
    	w.writeObject(this);
    	w.close();
    }
    
    public static StavHry precitaj(String subor) throws Exception
    {
    	ObjectInputStream r = new ObjectInputStream(
    			new FileInputStream(subor));
    	StavHry hra = (StavHry)r.readObject();
    	r.close();
    	hra.zaciatok = System.currentTimeMillis() - 
    			(hra.celkovo - hra.doKonca)*1000;
    	return hra;
    }
    
    void zistiZajace()
    {
    	int pocetHotovo = 0;
    	for (int i = 0; i < xZajace.size(); i++)
    	{
    		int xz = xZajace.get(i);
    		int yz = yZajace.get(i);
    		boolean najedeny = false;
    		if (xz > 0) najedeny = kuKapuste(xz - 1, yz);
    		if (!najedeny)
    			if (xz < plocha.length - 1) najedeny = kuKapuste(xz + 1, yz);
    		if (!najedeny)
    			if (yz > 0) najedeny = kuKapuste(xz, yz - 1);
    		if (!najedeny)
    			if (yz < plocha.length - 1) najedeny = kuKapuste(xz, yz + 1);
    		if (najedeny) 
    		{
    			plocha[yz][xz] = 'Z';
    			pocetHotovo++;
    		}
    		else plocha[yz][xz] = 'z';
    	}
    	if (pocetHotovo == xZajace.size()) hotovo = true;
    }
    
    HashSet<Integer> navstivene = new HashSet<Integer>();
    
    boolean kuKapuste(int s, int r)
    {
    	navstivene = new HashSet<Integer>();
    	return kuKapuste2(s, r);
    }
    
    boolean kuKapuste2(int s, int r)
    {
    	if ((s < 0) || (s >= plocha.length)) return false;
    	if ((r < 0) || (r >= plocha.length)) return false;
    	if (navstivene.contains(s * 1024 + r)) return false;
    	navstivene.add(s * 1024 + r);    	
    	if (plocha[r][s] == 'k') return true;
    	else if (plocha[r][s] == 'z') return false;
    	else if (plocha[r][s] == '<') return kuKapuste2(s - 1, r);
    	else if (plocha[r][s] == '>') return kuKapuste2(s + 1, r);
    	else if (plocha[r][s] == 'v') return kuKapuste2(s, r + 1);
    	else if (plocha[r][s] == '^') return kuKapuste2(s, r - 1);
    	else if (plocha[r][s] == '0') return kuKapuste2(s, r - 1);
    	else if (plocha[r][s] == '1') return kuKapuste2(s + 1, r);
    	else if (plocha[r][s] == '2') return kuKapuste2(s, r + 1);
    	else return kuKapuste2(s - 1, r);    	
    }
}

class Kanv extends Canvas implements Runnable
{
	StavHry hra;
	Zajace app;
	Image[] modreSipky;
	Image[] cerveneSipky;
	Image zajac;
	Image kapusta;
		
	public Kanv(int width, int height, StavHry hra, Zajace main)
	{
		modreSipky = new Image[4];
		cerveneSipky = new Image[4];
		zajac = new Image("zajacik.png");
		kapusta = new Image("kapusta.png");
		modreSipky[0] = new Image("modra_hore.png");
		modreSipky[1] = new Image("modra_vpravo.png");
		modreSipky[2] = new Image("modra_dole.png");
		modreSipky[3] = new Image("modra_vlavo.png");
		cerveneSipky[0] = new Image("cervena_hore.png");
		cerveneSipky[1] = new Image("cervena_vpravo.png");
		cerveneSipky[2] = new Image("cervena_dole.png");
		cerveneSipky[3] = new Image("cervena_vlavo.png");

		this.hra=hra;
		this.app = main;
		setWidth(width);
		setHeight(height);
		setOnMouseClicked(e->{klik(e.getX(), e.getY());});			
	}
	
	private void klik(double x, double y)
	{
		if ((hra.doKonca == 0) || (hra.hotovo)) return;
		int r = spracujSuradnicuY(y);
		int s = spracujSuradnicuX(x);
		if ((hra.plocha[r][s] >= '0') && (hra.plocha[r][s] <= '3'))
		{
			hra.plocha[r][s] = (char)('0' + (hra.plocha[r][s] - '0' + 1) % 4);
			hra.otoceni++;
			app.aktualizujOtoceni();
			hra.zistiZajace();
			if (hra.hotovo) app.zobrazHotovo();
			vykresli();
		}
	}
	
	private int spracujSuradnicuX(double x) {
		double sirkaPola= this.getWidth()/hra.plocha.length;
		return (int) (x/sirkaPola);
	}
	
	private int spracujSuradnicuY(double y) {
		double vyskaPola= this.getHeight()/hra.plocha.length;
		return (int) (y/vyskaPola);		
	}
	
	void setW(double newW)
	{
		setWidth(newW);
		vykresli();
	}
	
	void setH(double newH)
	{
		setHeight(newH);
		vykresli();
	}
	
	void setStav(StavHry novaHra)
	{
		hra = novaHra;
		vykresli();
	}
	
	public void run(){
		hra.zaciatok = System.currentTimeMillis();
		while (app.running){
			try{
				Thread.sleep(50);
			}
			catch(Exception e){
				
			}
			if (hra.doKonca == 0) continue;
			long novyCas = (System.currentTimeMillis() - hra.zaciatok)/1000;
			if ((hra.celkovo - novyCas) < hra.doKonca){				
				hra.doKonca--;
				Platform.runLater(new Runnable(){
					public void run(){
						app.cas.setText("cas: " + hra.doKonca);
					}
				});;
			}
		}
	}
	
	void vykresli() 
	{
		double sirkaPola= this.getWidth()/hra.plocha.length;
		double vyskaPola= this.getHeight()/hra.plocha.length;
		double posunX = (sirkaPola - 55.0) / 2.0;
		if (posunX < 0) posunX = 0;
		double posunY = (vyskaPola - 55.0) / 2.0;
		if (posunY < 0) posunY = 0;
		GraphicsContext gc= getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, getWidth(), getHeight());
		
		for (int i=0; i<hra.plocha.length; i++){
			for (int j=0; j<hra.plocha.length; j++){
				double xe = sirkaPola * i + posunX;
				double ye = vyskaPola * j + posunY;
				if (hra.plocha[j][i] == 'Z')
				{
					gc.setFill(Color.LIGHTGREEN);
					gc.fillRect(sirkaPola*i, vyskaPola*j, sirkaPola, vyskaPola);
				}
				if (Character.toLowerCase(hra.plocha[j][i]) == 'z')
					gc.drawImage(zajac, xe, ye);
				else if (hra.plocha[j][i] == 'k')
					gc.drawImage(kapusta, xe, ye);
				else if (hra.plocha[j][i] == '^')
					gc.drawImage(cerveneSipky[0], xe, ye);
				else if (hra.plocha[j][i] == '>')
					gc.drawImage(cerveneSipky[1], xe, ye);
				else if (hra.plocha[j][i] == 'v')
					gc.drawImage(cerveneSipky[2], xe, ye);
				else if (hra.plocha[j][i] == '<')
					gc.drawImage(cerveneSipky[3], xe, ye);
				else gc.drawImage(modreSipky[hra.plocha[j][i] - '0'], xe, ye);
//				gc.setFill(farby[hra.plocha[i][j]]);
//				gc.fillRect(sirkaPola*i, vyskaPola*j, sirkaPola, vyskaPola);			
			}
		}
		gc.setFill(Color.BLACK);
		for (int i=0; i<hra.plocha.length; i++){			
			gc.fillRect(sirkaPola*i, 0, 2, getHeight());
			gc.fillRect(0, vyskaPola*i, getWidth(), 2);
		}		
	}
}
