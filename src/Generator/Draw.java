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
	Gen mazec;

	@Override
	public void init() {
		System.out.println("START");
		mazec = new Gen(50, 50);
		mazec.genMaze();
		setSize(500, 500);
		setBackground(Color.BLACK);
		setFocusable(true);
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
				//int[] array = { 0, 1, 2, 3 };
				//ShuffleArray(array);
				//switch (array[0]) {

				//case 0:
					g.setColor(Color.YELLOW);
					//break;
				//case 1:
					//g.setColor(Color.RED);
					//break;
				//case 2:
				//	g.setColor(Color.GREEN);
					//break;
				//case 3:
				//	g.setColor(Color.BLUE);
					//break;

				//}

				if (mazec.maze[i][j] == 1) {
					g.fillRect(i*10 ,j *10 , 10, 10);
				} else {
					g.setColor(Color.BLACK);
					g.fillRect(i*10,j*10 , 10, 10);
				}
			}
		}

		// super.paint(g);
	}

	private void ShuffleArray(int[] array) {
		int index, temp;
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}

	@Override
	public void update(Graphics g) {
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
