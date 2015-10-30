package breakout;

import java.awt.*;

public class Block{
		int bWidth;
		int bHeight;
		int yposition;
		int xposition;//pixels to the left side of the screen
		private Ball ball;
		private Breakout breakout;
		int i;
		int bottom;
		int top;
		int left;
		int right;
		int centerX;
		int centerY;
		boolean noTiles;
		Block(Ball ball,int i, Breakout breakout){
			this.i = i;
			this.breakout = breakout;
			this.ball = ball;
			bWidth = 60;
			bHeight = 20;
			/*next 8 lines determine position and row of block based on i*/
			int y = 0;
			int x = i;
			if(i > 9){
				int b =	i/10;
				y = b;
				x = i-(b*10);
			}
			xposition = (180+(x*bWidth));//pixels to the left side of the screen
			yposition = (100+(y*bHeight));
			left = 180;
			right = left + 10*bWidth;
			top = 100;
			bottom = top + bHeight;
		}
		void updateForNewFrame(){
			int leftX= ball.ballX;
			int rightX = ball.ballX+20;
			int topY= ball.ballY;
			int bottomY= ball.ballY+20;
			if(ball.northEast){
				if(topY > yposition && topY <= (yposition+bHeight) && rightX < (xposition+bWidth) && rightX >= xposition){//if ball hits block
					if(topY == yposition+bHeight){//if top of ball is equal to y plane of block
						ball.northEast = false;
						ball.southEast = true;//change direction
						breakout.exists[i] = false;//erase block
						breakout.score+=5;//add 5 to score
						noTiles = true;
						for(int i = 0;i < 70;i++){
							if(breakout.exists[i] == true){
								noTiles = false;
							}
						}//check if all blocks are erased
						if(noTiles){
							breakout.winner=true;
						}
					}
					else{
						ball.northEast = false;
						ball.northWest = true;
						breakout.exists[i] = false;
						breakout.score+=5;
						noTiles = true;
						for(int i = 0;i < 70;i++){
							if(breakout.exists[i] == true){
								noTiles = false;
							}
						}
						if(noTiles){
							breakout.winner=true;
						}
					}
				}
			}
			else if(ball.northWest){
				if(topY > yposition && topY <= (yposition+bHeight) && leftX <= (xposition+bWidth) && leftX > xposition){
					if(topY == yposition+bHeight){
						ball.northWest = false;
						ball.southWest = true;
						breakout.exists[i] = false;
						breakout.score+=5;
						noTiles = true;
						for(int i = 0;i < 70;i++){
							if(breakout.exists[i] == true){
								noTiles = false;
							}
						}
						if(noTiles){
							breakout.winner=true;
						}
					}
					else{
						ball.northWest = false;
						ball.northEast = true;
						breakout.exists[i] = false;
						breakout.score+=5;
						noTiles = true;
						for(int i = 0;i < 70;i++){
							if(breakout.exists[i] == true){
								noTiles = false;
							}
						}
						if(noTiles){
							breakout.winner=true;
						}
					}
				}
			}
			else if(ball.southEast){
				if(bottomY >= yposition && bottomY < (yposition+bHeight) && rightX < (xposition+bWidth) && rightX >= xposition){
					if(bottomY == yposition){
						ball.southEast = false;
						ball.northEast = true;
						breakout.exists[i] = false;
						breakout.score+=5;
						noTiles = true;
						for(int i = 0;i < 70;i++){
							if(breakout.exists[i] == true){
								noTiles = false;
							}
						}
						if(noTiles){
							breakout.winner=true;
						}
					}
					else{
						ball.southEast = false;
						ball.southWest = true;
						breakout.exists[i] = false;
						breakout.score+=5;
						noTiles = true;
						for(int i = 0;i < 70;i++){
							if(breakout.exists[i] == true){
								noTiles = false;
							}
						}
						if(noTiles){
							breakout.winner=true;
						}
					}
				}
			}
			else if(ball.southWest){
				if(bottomY >= yposition && bottomY < (yposition+bHeight) && leftX <= (xposition+bWidth) && leftX > xposition){
					if(bottomY == yposition){
						ball.southWest = false;
						ball.northWest = true;
						breakout.exists[i] = false;
						breakout.score+=5;
						noTiles = true;
						for(int i = 0;i < 70;i++){
							if(breakout.exists[i] == true){
								noTiles = false;
							}
						}
						if(noTiles){
							breakout.winner=true;
						}
					}
					else{
						ball.southWest = false;
						ball.southEast = true;
						breakout.exists[i] = false;
						breakout.score+=5;
						noTiles = true;
						for(int i = 0;i < 70;i++){
							if(breakout.exists[i] == true){
								noTiles = false;
							}
						}
						if(noTiles){
							breakout.winner=true;
						}
					}
				}
			}
		}
		void draw(Graphics g, int i){
			int y = 0;
			int x = i;
			if(i > 9){
				int b =	i/10;
				y = b;
				x = i-(b*10);
				}
			if(i< 10){
				g.setColor(Color.RED);
			}
			else if(i< 20){
				g.setColor(Color.ORANGE);
			}
			else if(i< 30){
				g.setColor(Color.YELLOW);
			}
			else if(i< 40){
				g.setColor(Color.GREEN);
			}
			else if(i< 50){
				g.setColor(Color.BLUE);
			}
			else if(i< 60){
				g.setColor(Color.CYAN);
			}
			else if(i< 70){
				g.setColor(Color.MAGENTA);
			}
			g.fillRect(180+(x*bWidth), 100+(y*bHeight), bWidth, bHeight);
			g.setColor(Color.BLACK);
			g.drawRect(180+(x*bWidth), 100+(y*bHeight), bWidth, bHeight); 
		}
		
	}
