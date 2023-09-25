package application;

import java.io.IOException;
import javafx.scene.control.Label;

public class GameTimer extends Thread{
	
	private int seconds;
	
	public GameTimer() {
		seconds = 0;
		start();
	}
	
	@Override
	public void run() {
		while(controller.loose == false){
			
			seconds++;
			
			controller.module.newObject();
			try {
				controller.module.actualiseObjects();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				this.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getSeconds() {
		return this.seconds;
	}
}


