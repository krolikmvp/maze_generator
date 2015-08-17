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
	
	public void moveRight() {
		this.posX+=10;
	}

	public void moveLeft() {
		this.posX-=10;
	}
	
	public void moveUp() {
		posY-=10;;
	}

	public void moveDown() {
		posY+=10;
	}
	
}
