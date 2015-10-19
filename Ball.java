package breakout;

import java.awt.*;

public class Ball {
        public Color squareColor;// private Bar bar
		int ballX,ballY;
		boolean isMoving;
		boolean northEast;
		boolean northWest;
		boolean southEast;
		boolean southWest;
		int balls = 3;
		private Bar bar;
		private Breakout breakout;
		Ball(Bar bar,Breakout breakout){
			this.bar = bar;
			this.breakout = breakout;
			isMoving = false;
		}
		void updateForNewFrame() {//checks if ball hits wall or bar, if not moves it sqrt(200) pixels in current direction
            if(isMoving){
            	if(ballX-10 < 0)
            	{
            		if(northWest){
            			northWest = false;
            			northEast = true;
            		}
            		else if(southWest)
            		{
            			southWest = false;
            			southEast = true;
            		}
            	}
            	if(ballX+30 > breakout.width)
            	{
            		if(northEast){
            			northEast = false;
            			northWest = true;
            		}
            		else if(southEast)
            		{
            			southEast = false;
            			southWest = true;		
            		}
            	}
            	if(ballY-10 < 0)
            	{
            		if(northEast){
            			northEast = false;
            			southEast = true;	
            		}
            		else if(northWest)
            		{
            			northWest = false;
            			southWest = true;	
            		}
            	}
            	if(ballY+10 > breakout.height)
            	{
            		balls -=1;
            		isMoving = false;
            		southEast = false;
            		southWest = false;
            	}
            	if(ballY+10 > 620-Breakout.RECT_HEIGHT && ballY+10 < 620 && ballX < bar.squareLeft+(Breakout.RECT_LENGTH/3) && ballX > bar.squareLeft)
            	{
            		if(southEast){
            			southEast = false;
            			northWest = true;			
            		}
            		else if(southWest){
            			southWest = false;
            			northWest = true;			
            		}
            	}
            	if(ballY+10 > 620-Breakout.RECT_HEIGHT && ballY+10 < 620 && ballX < bar.squareLeft+Breakout.RECT_LENGTH && ballX > bar.squareLeft+(2*Breakout.RECT_LENGTH/3))            	
            	{
            		if(southWest){
            			southWest = false;
            			northEast = true;			
            		}
            		else if(southEast){
            			southEast = false;
            			northEast = true;			
            		}
            	}
            	if(ballY+10 > 620-Breakout.RECT_HEIGHT && ballY+10 < 620 && ballX < (bar.squareLeft+Breakout.RECT_LENGTH) && ballX > bar.squareLeft)
            	{
            		if(southEast){
            			northEast = true;
            			southEast = false;			
            		}
            		else if(southWest)
            		{
            			northWest = true;
            			southWest = false;	
            		}
            	}
            	if(northEast)
            	{
            		ballY-=10;
            		ballX+=10;
            	}
            	if(northWest)
            	{
            		ballY-=10;
            		ballX-=10;
            	}
            	if(southEast)
            	{
            		ballY+=10;
            		ballX+=10;
            	}
            	if(southWest)
            	{
            		ballY+=10;
            		ballX-=10;
            	}
        }
    }
		void draw(Graphics g){
			if(!isMoving)
			{
				ballY = bar.squareTop-20;
				ballX = bar.squareLeft+60;
			}
			g.setColor(Color.RED);
			g.fillOval(ballX, ballY, Breakout.CIRCLE, Breakout.CIRCLE);
		}
	}