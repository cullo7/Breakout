package breakout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Breakout extends JPanel implements Runnable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main(String args[])
	{
	JFrame window = new JFrame("Breakout");//create frame
	Breakout content = new Breakout();
	window.setContentPane(content);//assign instance of class to pane
	window.setSize(1000,1000);//size of window
	window.setLocation(0,0);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//end when window is exited
	window.setVisible(true);
	window.setResizable(false);
	}

	public static final int RECT_HEIGHT = 20;//bar height
	public static final int RECT_LENGTH = 130;//bar width
	public static final int CIRCLE = 20;//circle diameter
	public static final Color squareColor = Color.BLUE;
	public Bar bar;// public Bar bar
	public Ball ball;// public Ball ball
	public Block blocks[];
	public int width, height;
	public Timer timer;//public timer variable to keep track of time
	boolean exists[] = new boolean[70];
	int xDirection;
	public Thread t1;
	public boolean winner;
	public int score = 0;


	public Breakout(){//constructor

		Thread t1 = new Thread(this);
		t1.start();//thread for bar movement
		setBackground(Color.BLACK);

		ActionListener action =  new ActionListener() {
			public void actionPerformed(ActionEvent evt){//action performed when action is called
				if(bar != null)
				{
					bar.updateForNewFrame();
					ball.updateForNewFrame();
					if(ball.ballX <= 850 && ball.ballX >= 150 && ball.ballY <= 375 && ball.ballY >= 80)
					{
						for(int i = 0;i < 70;i++)
						{
							if(exists[i])
							{
								blocks[i].updateForNewFrame();
							}
						}
					}
				}
			repaint();
			}
		};

		timer = new Timer(30, action);//call action every 30 milliseconds

		addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
        requestFocus();
            }
		});//listen for mouse clicks

		addFocusListener(new FocusListener() {
		public void focusGained(FocusEvent evt) {
			timer.start();
			repaint();
		}

		public void focusLost(FocusEvent evt) {
			timer.stop();
			repaint();
		}
		});//Listen for focus - if window is the main window on the computer

		addKeyListener(new KeyAdapter(){

		public void keyPressed(KeyEvent evt){
			int key = evt.getKeyCode();

			if(key == KeyEvent.VK_LEFT){//left key on keyboard
				setXDirection(-2);
			}
			else if(key == KeyEvent.VK_RIGHT){//right key on keyboard bar moves 2 pixels every 5 miliseconds
				setXDirection(2);
			}
			else if(key == KeyEvent.VK_SPACE && ball.balls > 0 && winner == false){//spacebar, ball only moves is game isn't won and there are still balls left
				if(!ball.isMoving){
					ball.isMoving = true;
					ball.northWest = true;//ball moves northwest
				}
			}
		}
		
		public void keyReleased(KeyEvent evt){//when key is released bar stops moving
			int key = evt.getKeyCode();
			if(key == KeyEvent.VK_LEFT){
				setXDirection(0);
			}
			if(key == KeyEvent.VK_RIGHT){
				setXDirection(0);
			}
		}
			
		});//listen for keyboard
		for(int k = 0;k < 70;k++)
		{
			exists[k] = true;
		}
	} //end of constructor
		
	public void run()//runs when thread starts
	{
		try{
			while(true){

				move();

				Thread.sleep(5);
			}
		}
		catch(Exception e){
			System.out.print("Error");
		}
	}
	
	public void move()//runs every 5 milliseconds
	{
		if(bar!=null)//if  bar exists
		{
		bar.squareLeft+=xDirection;
		}
	}
	
	public void setXDirection(int xdir)
	{
		xDirection = xdir;
	}

	public void paintComponent(Graphics g){
		
		super.paintComponent(g);//import paintComponent of superclass
		Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//makes diagnols/curves cleaner

		if(bar == null)
		{
		width = getWidth();
		height = getHeight();
		bar = new Bar(this);
		ball = new Ball(bar,this);
		blocks= new Block[70];
		for(int i = 0;i < 70;i++)
		{
			blocks[i] = new Block(ball, i, this);//instantiating each block
		}
		}
		//instantiates classes if just starting the program

		if(hasFocus())
			g.setColor(Color.CYAN);
		else
			g.setColor(Color.GREEN);

		g.setColor(Color.MAGENTA);
		if(hasFocus()){
			g.drawString("Arrow Keys Move Square",7,20);
			g.drawString("K, R, G, B Change Color",7,40);
		}
		else
			g.drawString("Click to activate",7,20);

		g.drawString("Balls: "+ball.balls,7,60);

		if(ball.balls == 0)
		{
			for(int i = 0;i < 70;i++)
			{
				exists[i] = false;
			}
			g.setColor(Color.RED);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
			g.drawString("Game Over", 100, 200);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
			g.drawString("Your score was "+score,100,400);
		}
		if(winner)
		{
			ball.isMoving=false;
			g.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 150));
			g.drawString("Winner!", 100, 200);
		}

		g.drawRect(0,0,width-1,height-1);
		g.drawRect(1,1,width-3,height-3);
		g.drawRect(2,2,width-5,height-5);

		bar.draw(g);
		ball.draw(g);
		for(int i = 0;i < 70; i++)
		{
			if(exists[i])
			{
			blocks[i].draw(g, i);//if it exists draw it
			}
		}
	}
}
