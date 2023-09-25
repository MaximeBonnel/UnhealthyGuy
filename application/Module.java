package application;

import java.io.IOException;
import java.lang.Math;

public class Module {
	
	// on implemente les 2 classes pour les messages perso :

	ObjectMessageWater messWater = new ObjectMessageWater();
	ObjectMessageCola messCola = new ObjectMessageCola();
	
	
	private int stickman = 1;
	private int waterObject = 2;
	private int colaObject = 3;
	
	private int[] array;
	private int[] arraySize = {10, 30}; // Y, X
	private int arrayLength = arraySize[0] * arraySize[1] -1;
	
	private int StickmanPosition = (arraySize[0]-1) * arraySize[1] + (arraySize[1]/2) - 1;
	
	private int score;
	
	public Module() {
		array = new int[arraySize[0]*arraySize[1]];
		
		for (int i = 0; i < arraySize[0]; i++) { // Y
			for (int j = 0; j < arraySize[1]; j++) { // X
				array[(arraySize[1]*i) + j] = 0;
			}
		}
		
		// Stick man
		array[this.StickmanPosition] = this.stickman;
		
		// Score
		score = 0;
	}
	
	public void printArray() {
		for (int i = 0; i < arraySize[0]; i++) {
			for (int j = 0; j < arraySize[1]; j++) {
				System.out.print(array[(arraySize[1]*i) + j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public int[] getArray() {
		return this.array;
	}
	
	public int[] getArraySize() {
		return this.arraySize;
	}
	
	public int getArrayLength() {
		return this.arrayLength;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getStickmanPos() {
		return this.StickmanPosition;
	}
	
	public void moveRight() {
		if (this.StickmanPosition % arraySize[1] != arraySize[1] - 1) {
			array[this.StickmanPosition++] = 0;
			array[this.StickmanPosition] = this.stickman;
		}
	}
	
	public void moveLeft() {
		if (this.StickmanPosition % arraySize[1] != 0) {
			array[this.StickmanPosition--] = 0;
			array[this.StickmanPosition] = this.stickman;
		}
	}
	
	public void newObject() {
		int random = (int) (Math.random()*100) % (arraySize[1]-1);
		int randomObject = (int) (Math.random()*10 % 2) + 2; // 2 ou 3
		
		array[random] = randomObject;
	}
	
	public void actualiseObjects() throws IOException {
		for (int i = arrayLength; i >= 0 ; i--) {
			if (array[i] == this.colaObject) {
				array[i] = 0;
				if (i + this.arraySize[1] <= this.arrayLength) { // Si ca ne sort pas du tableau
					if (array[i + this.arraySize[1]] != this.stickman) { // Si le coca ne touche pas le stickman
						array[i + this.arraySize[1]] = this.colaObject;
					} else {
						// Coca est bu
						this.score++;
						 messCola.affichermessage();
					}
				}
			} else if (array[i] == this.waterObject) {
				array[i] = 0;
				if (i + this.arraySize[1] <= this.arrayLength) { // Si ca ne sort pas du tableau
					if (array[i + this.arraySize[1]] != this.stickman) { // Si l'eau ne touche pas le stickman
						array[i + this.arraySize[1]] = this.waterObject;
					} else {
						// L'eau est bu
						// Perdu
						messWater.affichermessage();
					 	controller.controllerWIn.ecrireTxt("src/application/Wintext.txt", String.valueOf(score));
					 	System.out.println("Ton score est le suivant ");
					 	controller.controllerWIn.afficherTxt( "src/application/Wintext.txt");
					 	controller.loose = true;
						
					 	// System.exit(1);
					}
				}
			}
		}
	}
}
