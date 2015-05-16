import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Client {

	public static void main(String[] args) {
		FichierSol fs = new FichierSol("./src/fictest.txt");
		fs.lire();
		Solution sol= fs.fichierToSolution();
		System.out.print(sol.printSolution());

	}

}
