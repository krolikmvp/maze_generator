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
	}
	
}
