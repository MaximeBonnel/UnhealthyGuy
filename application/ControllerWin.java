package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class ControllerWin 
{
	
	public Label label = new Label(); // creation d'un label pour afficher du texte --> ici pour spam
	public Label labeltxtWin= new Label();
	public int spamcounter;
	
	
	double  initposx=label.getLayoutX();
	double  initposy=label.getLayoutY();
	
	public void spambutton(ActionEvent e) throws IOException
	{
		// System.out.println(" spam ");
		spamcounter +=1 ;
		
		label.setText(" SPAM !!!");
		//label.setLayoutX(label.getLayoutX()+10);
		
		
		// on va faire un moove tout les 3 clicks en fonction du compteur 
		
		int moduletrois = spamcounter % 3 ;
		
		// les 3 cas de figures diff :
		
	
		if( moduletrois == 0)
		{
			label.setLayoutX(label.getLayoutX()+100);
		}
		if(moduletrois==1)
		{
			label.setLayoutX(label.getLayoutX()-50);
			label.setLayoutY(label.getLayoutY()+150);
		}
		if(moduletrois==2)
		{
			label.setLayoutX(label.getLayoutX()-50);
			label.setLayoutY(label.getLayoutY()-150);
		}

		if( spamcounter == 15) 
		{
			afficherTxt("src/application/Wintext.txt");
		}
		
	}

	 public void ecrireTxt(String fichiertxt , String txtAEcrire)
	 {
		 File file = new File(fichiertxt);
		 
		 if(!file.exists()) {
			 
			 System.out.println(" le fichier n'est pas la ");
			 
		 }
		 
		 try 
		 {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),false);
			BufferedWriter bw = new BufferedWriter(fw);
			//bw.write("\n ");
		    bw.write(txtAEcrire);
			bw.close();
			
		} catch (IOException e)
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("probleme lors de l'ecriture");
		}
		 
		 
	 }
	
	public void afficherTxt( String fichiertxt) throws IOException
	{
		
		
		try 
		{
			
			// fichier d'entre :
			
			File text= new File(fichiertxt);
			
			// creation de l'objet
			
			FileReader fr = new FileReader(text);
			// creation du bufferReader
			 BufferedReader br = new BufferedReader(fr);  
		     StringBuffer sb = new StringBuffer();    
		     String line;
		     
		      while((line = br.readLine()) != null)
		      {
		        // ajoute la ligne au buffer
		        sb.append(line);      
		    //    sb.append("\n");     
		      }
		      fr.close();    
		  //    System.out.println("Contenu du fichier: ");
		      System.out.println(sb.toString());
		      labeltxtWin.setText(sb.toString());
			
		} 
		catch (FileNotFoundException e) 
		
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
