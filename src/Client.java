/**
 * L'execution complète de la transformation d'un fichier txt (généré à partir du solveur GLPK avec fichier .mod 
 * et fichier .dat) en Objet Solution et l'affichage de celui-ci. 
 * @author Morane Otilia
 *
 */
public class Client {

	public static void main(String[] args) {
		FichierSol fs = new FichierSol("./src/fictest.txt");
		fs.lire();
		Solution sol= fs.fichierToSolution();
		System.out.print(sol.printSolution());

	}

}
