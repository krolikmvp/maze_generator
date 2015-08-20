package Generator;

public class Player {
	private int posX;
	private int posY;

	
	public Player(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
<<<<<<< HEAD
	public void moveRight(int dist) {
		this.posX+=dist;
	}

	public void moveLeft(int dist) {
		this.posX-=dist;
	}
	
	public void moveUp(int dist) {
		posY-=dist;;
	}

	public void moveDown(int dist) {
		posY+=dist;
=======
	public void moveRight(int x) {
		this.posX+=x;
	}

	public void moveLeft(int x) {
		this.posX-=x;
	}
	
	public void moveUp(int y) {
		posY-=y;
	}

	public void moveDown(int y) {
		posY+=y;
>>>>>>> ae411162d745b3929333a38231c9dc6bc42ac384
	}
	
}
