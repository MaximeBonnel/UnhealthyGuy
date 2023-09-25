package application;

public class controller {
	
	public static Module module = new Module();
	public static View view = new View();
	public static ControllerWin controllerWIn = new ControllerWin();
	public static GameTimer timer = new GameTimer();
	
	// Loose
	public static boolean loose = false;
	
	// fonctions pour les mouvements , droite ou gauche du joueur
	
	public static void Left() {
		module.moveLeft();
		//module.printArray();
	}
	
	public static void Right() {
		module.moveRight();
		//module.printArray();
	}	
	
}
