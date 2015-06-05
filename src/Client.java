/**
 * L'execution complète de la transformation d'un fichier txt (généré à partir du solveur GLPK avec fichier .mod 
 * et fichier .dat) en Objet Solution et l'affichage de celui-ci. 
 * @author Morane Otilia
 *
 */
public class Client {

	public static void main(String[] args) {
		FichierSol fs = new FichierSol("./src/fictesnprim.txt");
		fs.lire();
		//fs.afficherStream();
		try{
			Solution sol= fs.fichierToSolution();
			System.out.print(sol.getPB().toString());
			System.out.print(sol.printSolution());
			System.out.print(sol.getPB().verifRamSol( sol));
		}catch(Exception e){
			System.out.print(e);
		}
		
	}

}
