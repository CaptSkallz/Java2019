package demos;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TenisCanvas extends Canvas implements Runnable
{
    double loptaX, loptaY;
	
    /** sirka*/
	int sizeX = 600;
	/** vyska */
	int sizeY = 600;
	int odrazacLX,odrazacPX,odrazacLY,odrazacPY;
	double dx, dy;
	public boolean Lhore,Ldole,Phore,Pdole;
    public TenisCanvas()
    {
    	this.setWidth(sizeX);
		this.setHeight(sizeY);
    	reset();
    	odrazacLY=sizeY/2;
    	odrazacPY=sizeY/2;
    	odrazacLX=20;
    	odrazacPX=sizeX-30;
    	setOnMouseMoved(event ->
    	{
    		if(loptaX<sizeX/2){
    			if(event.getSceneX()<sizeX/2)odrazacLX=(int)event.getSceneX();
    		}
    		if(loptaX>sizeX/2){
    			if(event.getSceneX()>sizeX/2)odrazacPX=(int)event.getSceneX();
    		}
    		
    	});
    	new Thread(this).start();
    	requestFocus();
    }
    private void reset(){
    	loptaX = sizeX / 2.0;
    	loptaY = sizeY / 2.0;
    	dx = 3 * Math.random()+2;
    	dy = 3 * Math.random();
    }
    
    
    void nakresli()
    {
    	GraphicsContext gc = getGraphicsContext2D();
    	gc.setFill(Color.BLACK);
    	gc.fillRect(0, 0, sizeX, sizeY);
    	gc.setFill(Color.RED);
    	gc.fillOval(loptaX, loptaY, 15 ,15);
    	gc.fillRect(sizeX/2, 0, 5, sizeY);
    	gc.setFill(Color.WHITE);
    	gc.setFont(new Font(20));
    	gc.fillText(String.valueOf(skoreL), sizeX/4, 20);
    	gc.fillText(String.valueOf(skoreP), sizeX*3/4, 20);
    	
    	gc.fillRect(odrazacLX, odrazacLY, 10, 100);
    	gc.fillRect(odrazacPX, odrazacPY, 10, 100);
    	
    }
    int skoreL,skoreP;
	public void run()
	{
		while(true)
		{
			
			if(loptaY+dy<0 || loptaY+dy>sizeY){
				dy*=-1;
			}
			if(loptaX+dx<0){ //zvysit skore
				skoreP++;
				reset();
			}
			if(loptaX+dx>sizeX){
				skoreL++;
				reset();
			}
			loptaX+=dx;
			loptaY+=dy;
			
			if(loptaX+dx<odrazacLX+10 &&
				loptaX+dx>odrazacLX
					&& loptaY>odrazacLY && 
					loptaY<odrazacLY+100) dx*=-1.01;
			if(loptaX+dx+15>odrazacPX &&
				loptaX+dx+15<odrazacPX+10 &&
				 loptaY>odrazacPY && 
					loptaY<odrazacPY+100) dx*=-1.01;
			
			if(Lhore && odrazacLY>0) odrazacLY-=5;
			if(Ldole && odrazacLY+100<sizeY) odrazacLY+=5;
			if(Phore && odrazacPY>0) odrazacPY-=5;
			if(Pdole && odrazacPY+100<sizeY) odrazacPY+=5;
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		
			Platform.runLater(new Runnable() {
	            @Override
	            public void run() {nakresli();}});
		}
	}
} 