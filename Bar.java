package breakout;

import java.awt.*;

public class Bar{
	public Ball ball;
	int squareLeft;
	int squareTop;
	boolean left = false;
	boolean right = false;
	private final Breakout breakout;
		
	public Bar(Breakout breakinst){
		this.breakout = breakinst;
		squareLeft = 120;
		squareTop = 620;
	}
		
	void updateForNewFrame() { 
        	if(squareLeft > breakout.width-3-breakout.RECT_LENGTH){//if bar is off screen put it back on screen
            		squareLeft = (breakout.width - 3 - breakout.RECT_LENGTH);}
		else if(squareLeft < 3){
			squareLeft = 3;
        	}
		void draw(Graphics g){//draws bar
			g.setColor(Breakout.squareColor);
			g.fillRoundRect(squareLeft, squareTop, breakout.RECT_LENGTH, breakout.RECT_HEIGHT,20,20);
		}
	}
}
