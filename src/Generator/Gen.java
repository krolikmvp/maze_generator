package Generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
//////////////Klasy maski
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
//////////////Klasy maski - KONIEC
/////////////Glowna klasa generatora
public class Gen {

	int[][] maze;//glowna tablica przechowuje 0,1 (1 sciezka, 0 sciana)
	Random generator;// generator liczb losowych
	int sizex, sizey;//rozmiar labiryntu x,y
	int depth;// zmienna do sprawdzania glebokosci rekurencji

	public Gen(int x, int y) {
		this.sizex = x;
		this.sizey = y;
		generator = new Random();
		this.maze = new int[x][y];
		this.depth = 0;
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
//getery, setery
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

		int spos[] = { 5, 5 };//punkt startowy
		maze[5][5] = 1;//punkt startowy -1 czyli odwiedzony
		while (1 == 1) {

			if (checkNeighbour(3, spos) == 0 || checkNeighbour(3, spos) == 1)//wykonuje rekurencujnie dopoki nie zwroci jakiejkolwiek wartosci
				break;
		}

	}
/////////////Glowna klasa generatora KONIEC
	////////Funkcja do mieszania tablicy
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
	////////Funkcja do mieszania tablicy KONIEC
	////////Funkcja do nakladania maski	
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
	////////Funkcja do nakladania maski	KONIEC
	////////Funkcja sprawdzajaca sasiada
	public int checkNeighbour(int dir, int[] pos) {
		depth++;
		System.out.println(depth);
		int in = 0;
		int result = 1;
		int[] newpos = new int[2];
		switch (dir) {//switch, ktory na podstawie dir(kierunku przesuniecia) naklada odpowiedznia maske, zeby sprawdzic czy nie ma kolizji
		case 0:

			if ((result = putMask(topMask.mask, pos, dir)) < 1) {
				newpos[0] = pos[0];
				newpos[1] = pos[1] - 1;
			}
			break;
		case 1:
			if ((result = putMask(bottomMask.mask, pos, dir)) < 1) {
				newpos[0] = pos[0];
				newpos[1] = pos[1] + 1;
			}
			break;
		case 2:
			if ((result = putMask(leftMask.mask, pos, dir)) < 1) {
				newpos[0] = pos[0] - 1;
				newpos[1] = pos[1];
			}
			break;
		case 3:
			if ((result = putMask(rightMask.mask, pos, dir)) < 1) {
				newpos[0] = pos[0] + 1;
				newpos[1] = pos[1];
			}
			break;
		}
		////Sprawdza czy nowa pozycja nie wychodzi za plansze
		if (newpos[0] < 0 || newpos[1] < 0 || newpos[1] > sizey
				|| newpos[0] > sizex) {
			return 0;
		}
		///Jesli result >0 cofa siê (result>0 znaczy, ze maska zwrocila wartosc>0, czyli napotkala przeszkode)
		if (result > 0) {
			return 1;

		} else {
			//Jesli nie napotkala przeszkody to losuje nowy kierunek 0 gora 1 dol 2 lewo 3 prawo
			maze[newpos[0]][newpos[1]] = 1;//ustawia aktualna pozycje jako sciezke
			int[] array = { 0, 1, 2, 3 };
			ShuffleArray(array);
			for (int i = 0; i < 4; ++i) {//4 kierunki
				checkNeighbour(array[i], newpos);//reukrencyjna funkcja
			}
		}

		return 1;
	}
	////////Funkcja sprawdzajaca sasiada KONIEC
}
