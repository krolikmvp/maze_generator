package Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class topMask {
	public int id = 0;
	public static int[][] mask = { { 2, 2, 2 }, { 2, 2, 2 }, { 0, 0, 0 } };
}

class bottomMask {
	public int id = 1;
	public static int[][] mask = { { 0, 0, 0 }, { 2, 2, 2 }, { 2, 2, 2 } };
}

class leftMask {
	public int id = 2;
	public static int[][] mask = { { 2, 2, 0 }, { 2, 2, 0 }, { 2, 2, 0 } };
}

class rightMask {
	public int id = 3;
	public static int[][] mask = { { 0, 2, 2 }, { 0, 2, 2 }, { 0, 2, 2 } };
}

public class Gen {

	int[][] maze;
	Random generator;
	int sizex, sizey;
	boolean flag;

	public Gen(int x, int y) {
		this.sizex = x;
		this.sizey = y;
		generator = new Random();
		this.maze = new int[x][y];

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (i == 0 || i == y - 1 || j == 0 || j == x - 1) {

					maze[i][j] = 1;
				} else {
					maze[i][j] = 0;
				}
			}

		}

	}

	public int getSizex() {
		return sizex;
	}

	public void setSizex(int sizex) {
		this.sizex = sizex;
	}

	public int getSizey() {
		return sizey;
	}

	public void setSizey(int sizey) {
		this.sizey = sizey;
	}

	public void genMaze() {

		int spos[] = { 5, 5 };
		flag = false;
		maze[5][5] = 1;
		while (flag == false) {

			if (checkNeighbour(3, spos) == 0 || checkNeighbour(3, spos) == 1)
				break;
		}

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

	public int putMask(int[][] mask, int[] pos, int dir) {
		int sum = 0;
		// /top
		switch (dir) {
		case 0:
			sum += maze[pos[0] - 1][pos[1] - 2] * mask[0][0];
			sum += maze[pos[0] - 1][pos[1] - 1] * mask[1][0];
			sum += maze[pos[0]][pos[1] - 2] * mask[0][1];
			sum += maze[pos[0]][pos[1] - 1] * mask[1][1];
			sum += maze[pos[0] + 1][pos[1] - 2] * mask[0][2];
			sum += maze[pos[0] + 1][pos[1] - 1] * mask[1][2];
			break;
		// bottom
		case 1:
			sum += maze[pos[0] - 1][pos[1] + 2] * mask[1][0];
			sum += maze[pos[0] - 1][pos[1] + 1] * mask[2][0];
			sum += maze[pos[0]][pos[1] + 2] * mask[1][1];
			sum += maze[pos[0]][pos[1] + 1] * mask[2][1];
			sum += maze[pos[0] + 1][pos[1] + 2] * mask[1][2];
			sum += maze[pos[0] + 1][pos[1] + 1] * mask[2][2];
			break;
		// left
		case 2:
			sum += maze[pos[0] - 1][pos[1] - 1] * mask[0][1];
			sum += maze[pos[0] - 2][pos[1] - 1] * mask[0][0];
			sum += maze[pos[0] - 1][pos[1]] * mask[1][1];
			sum += maze[pos[0] - 2][pos[1]] * mask[1][0];
			sum += maze[pos[0] - 1][pos[1] + 1] * mask[2][1];
			sum += maze[pos[0] - 2][pos[1] + 1] * mask[2][0];
			break;

		// right
		case 3:
			sum += maze[pos[0] + 1][pos[1] - 1] * mask[0][1];
			sum += maze[pos[0] + 2][pos[1] - 1] * mask[0][2];
			sum += maze[pos[0] + 1][pos[1]] * mask[1][1];
			sum += maze[pos[0] + 2][pos[1]] * mask[1][2];
			sum += maze[pos[0] + 1][pos[1] + 1] * mask[2][1];
			sum += maze[pos[0] + 2][pos[1] + 1] * mask[2][2];
			break;
		}
		return sum;
	}

	public int checkNeighbour(int dir, int[] pos) {
		int in = 0;
		int result = 1;
		int[] newpos = new int[2];
		switch (dir) {
		case 0:

			if ((result = putMask(topMask.mask, pos, dir)) < 1) {
				System.out.println("result :" + result + " oldposx " + pos[0]
						+ "   oldposy" + pos[1]);
				newpos[0] = pos[0];
				newpos[1] = pos[1] - 1;
				System.out.println("result :" + result + " posx " + newpos[0]
						+ "   posy" + newpos[1]);
			}
			break;
		case 1:
			if ((result = putMask(bottomMask.mask, pos, dir)) < 1) {
				System.out.println("result :" + result + " oldposx " + pos[0]
						+ "   oldposy" + pos[1]);
				newpos[0] = pos[0];
				newpos[1] = pos[1] + 1;
				System.out.println("result :" + result + " posx " + newpos[0]
						+ "   posy" + newpos[1]);
			}
			break;
		case 2:
			if ((result = putMask(leftMask.mask, pos, dir)) < 1) {
				System.out.println("result :" + result + " oldposx " + pos[0]
						+ "   oldposy" + pos[1]);
				newpos[0] = pos[0] - 1;
				newpos[1] = pos[1];
				System.out.println("result :" + result + " posx " + newpos[0]
						+ "   posy" + newpos[1]);
			}
			break;
		case 3:
			if ((result = putMask(rightMask.mask, pos, dir)) < 1) {
				System.out.println("result :" + result + " oldposx " + pos[0]
						+ "   oldposy" + pos[1]);
				newpos[0] = pos[0] + 1;
				newpos[1] = pos[1];
				System.out.println("result :" + result + " posx " + newpos[0]
						+ "   posy" + newpos[1]);
			}
			break;
		}

		if (newpos[0] < 0 || newpos[1] < 0 || newpos[1] > sizey
				|| newpos[0] > sizex) {
			return 0;
		}
		if (result > 0) {
			if (in == 0) {
				return 0;
			} else {
				return 1;
			}

		} else {
			in++;
			maze[newpos[0]][newpos[1]] = 1;
			int[] array = { 0, 1, 2, 3 };
			ShuffleArray(array);
			for (int i = 0; i < 4; ++i) {

				checkNeighbour(array[i], newpos);

			}
		}

		return 1;
	}

}
