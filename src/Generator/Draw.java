package Generator;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.applet.Applet;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;



public class Draw extends Applet implements Runnable, KeyListener {
	

	public static final int xMapSize = 40;
	public static final int yMapSize = 40;
	public static final int blockSize = 20;
	
	private int[][] maze;


	
	
	private Graphics second;
	private Image image;
	private URL base;
	private Gen mazec; // glowny obiekt labiryntu
	private Player player;
	private MapDraw drawer;
	private BufferedImage board;
	
	private int[][] shadow;
	

	@Override
	public void init() {
		System.out.println("START");
		addKeyListener(this);
		mazec = new Gen(xMapSize, yMapSize); // tworzy tablice x na x
		mazec.genMaze(); // generuje labirynt w tablicy
		maze = mazec.getMaze();

		try {
			drawer=new MapDraw(maze,"test.png");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		board=drawer.getMap();
		setSize(500, 500); // rozmiar ekranu

		setSize(xMapSize*blockSize, yMapSize*blockSize); // rozmiar ekranu

		setBackground(Color.BLACK);
		setFocusable(true); // nie wiem
		try {
			base = getDocumentBase();
		} catch (Exception e) {

		}
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Maze draw");

		player = new Player(2*blockSize, 2*blockSize);

		super.init();
	}

	public void start() {

		Thread thread = new Thread(this);
		thread.start();

		// super.start();
	}

	@Override
	public void paint(Graphics g) {

	
		
		for (int i = 0; i < mazec.getSizex(); i++) {
			for (int j = 0; j < mazec.getSizey(); j++) {

					g.setColor(Color.YELLOW); // kolor sciezki zolty
					
				if (mazec.maze[i][j] == 1) {

					g.fillRect(i*blockSize,j *blockSize , blockSize, blockSize); // rysuje sciezke sciezke// wspolrzedna x,y *10, rozmiar 10/10

					g.fillRect(i*blockSize ,j *blockSize , blockSize, blockSize); // rysuje sciezke sciezke// wspolrzedna x,y *10, rozmiar 10/10

				} else {
					g.setColor(Color.BLACK);// kolor czarny
					g.fillRect(i*blockSize,j*blockSize , blockSize, blockSize); // rysuje sciane
				}
			}
		}
		
		g.setColor(new Color(255, 0, 0)); //set color to red (r, g, b) 
		g.drawImage(board, 0, 0, this);
		g.fillRect(player.getPosX(), player.getPosY(), blockSize, blockSize);
		// super.paint(g);
	}

	
	@Override
	public void update(Graphics g) {// nie wiem co to robi
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:	
			System.out.println("posY:");

			System.out.println(player.getPosY()/10-1);
			//System.out.println("maze:");
			System.out.println(maze[player.getPosX()/10][player.getPosY()/10-1]);

			System.out.println(player.getPosY()/blockSize-1);
			//System.out.println("maze:");
			System.out.println(maze[player.getPosX()/blockSize][player.getPosY()/blockSize-1]);

			if(maze[player.getPosX()/blockSize][player.getPosY()/blockSize-1] == 1 && player.getPosY()-blockSize>=0){
				player.moveUp(blockSize);
				System.out.println(player.getPosY());
				repaint();
			}
			break;

		case KeyEvent.VK_DOWN:
			if(maze[player.getPosX()/blockSize][player.getPosY()/blockSize+1] == 1 && player.getPosY()+blockSize>=0){
			player.moveDown(blockSize);
			System.out.println(player.getPosY());
			repaint();
			}
			break;

		case KeyEvent.VK_LEFT:
			if(maze[player.getPosX()/blockSize-1][player.getPosY()/blockSize] == 1 && player.getPosY()-blockSize>=0){
				player.moveLeft(blockSize);
				System.out.println(player.getPosX());
				repaint();
			}
			break;

		case KeyEvent.VK_RIGHT:
			if(maze[player.getPosX()/blockSize+1][player.getPosY()/blockSize] == 1 && player.getPosY()-blockSize>=0){
			player.moveRight(blockSize);
			System.out.println(player.getPosX());
			repaint();
			break;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
