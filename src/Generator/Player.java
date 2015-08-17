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
	}
	
}
