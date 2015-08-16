package Generator;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;



public class Draw extends Applet implements Runnable {
	private Graphics second;
	private Image image;
	private URL base;
	Gen mazec; // glowny obiekt labiryntu

	@Override
	public void init() {
		System.out.println("START");
		mazec = new Gen(100, 100); // tworzy tablice x na x
		mazec.genMaze(); // generuje labirynt w tablicy
		setSize(500, 500); // rozmiar ekranu
		setBackground(Color.BLACK);
		setFocusable(true); // nie wiem
		try {
			base = getDocumentBase();
		} catch (Exception e) {

		}
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Maze draw");

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
					g.fillRect(i*10 ,j *10 , 10, 10); // rysuje sciezke sciezke// wspolrzedna x,y *10, rozmiar 10/10
				} else {
					g.setColor(Color.BLACK);// kolor czarny
					g.fillRect(i*10,j*10 , 10, 10); // rysuje sciane
				}
			}
		}

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

}
