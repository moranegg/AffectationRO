
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Un objet de lecture de fichier txt et sa transformation en solution
 * @author Morane Otilia
 *
 */
public class FichierSol  {
	private String nomPhysique; //nom du fichier 
	private File fichier;
	String fichierToString;
	/**
	 * Constructeur Fichier sol
	 * @param nomPhysique
	 */
	public FichierSol(String nomPhysique){
        this.nomPhysique = nomPhysique;
        this.fichier =new File(this.nomPhysique);
    }
	/**
	 * getter du nom physique qui lie le logiciel avec le fichier
	 * @return
	 */
	public String getNomPhysique() {
		return nomPhysique;
	}
	/**
	 * setter du nomPhysique qui lie le logiciel avec le fichier
	 * @param nomPhysique
	 */
	public void setNomPhysique(String nomPhysique) {
		this.nomPhysique = nomPhysique;
	}
	/**
	 * méthode de lecture du fichier qui suvegarde la structure sur laquelle ont veut travailler
	 * @return
	 */
	public Solution lire()
	{
		Solution sol = null;
		try {
			FileInputStream fis = new FileInputStream(this.fichier) ;
 
			int content;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				fichierToString+=((char) content);
			}

		} catch (FileNotFoundException e ) {
			System.out.println("Le fichier n'est pas trouvé");
		} catch (IOException e){
			System.out.println ("problem de lecture de fichier");
		}
		return sol;
	}
	/**
	 * afficher le fichier complètement
	 */
	public void afficherStream(){
		System.out.println(fichierToString);
	}
	/**
	 * transformer fichier en Solution
	 */
	public Solution fichierToSolution(){
		Solution sol= null;
		Problem pb = null;
		int nbTaches = 0;
		int nbProcesseurs = 0;
		double sum= 0;
		int nbTacheMin =0;
		int affectTacheLigne = 0;
		double[][] affectTache;

		
		int ramDispoLigne = 10000;
		int coutLigne=10000;
		int[][] c;
		int demandeRamLigne=10000;
		int[][] a;
		int endLigne= 10000;
		
		try{
			String[] tab = fichierToString.split("\n");
			
			for(int i=0; i< tab.length; i++){
				//retrouver la valeur de la solution optimale sum
				if(tab[i].startsWith("sum")){
					String[] temp = tab[i].split("=");
					sum = Double.parseDouble(temp[1]);				
				//retrouver la valeur de nbTaches
				}else if(tab[i].startsWith("nbTaches")){
					String[] temp = tab[i].split("=");
					nbTaches =(int)Math.round(Double.parseDouble(temp[1].trim()));
					
				//retrouver la valeur de nb processeurs
				}else if(tab[i].startsWith("nbProcesseurs")){
					String[] temp = tab[i].split("=");
					nbProcesseurs = (int)Math.round(Double.parseDouble(temp[1].trim()));
					
				//retrouver la valeur de nbTacheMin si elle existe
				}else if(tab[i].startsWith("nbTacheMin")){
					String[] temp = tab[i].split("=");
					nbTacheMin = (int)Math.round(Double.parseDouble(temp[1].trim()));
					
				//retrouver la ligne de la solution d'affectation
				}else if(tab[i].startsWith("affectation")){
					affectTacheLigne = i;
				
				//retrouver la ligne de sorti des contraintes sur les processeurs
				}else if(tab[i].startsWith("ram dispo") ){
					ramDispoLigne = i;
				
				//retrouver la ligne de la table des couts
				}else if(tab[i].startsWith("cout") ){
					coutLigne = i;
				
				//retrouver la ligne de la demade de ram par tache
				}else if(tab[i].startsWith("demande ram") ){
					demandeRamLigne = i;		
				
				}else if(tab[i].startsWith("Model") ){
					endLigne = i;
				}
			}
			//pour la solution glpk
			affectTache = this.affectTab(affectTacheLigne, ramDispoLigne, tab, 
					 nbTaches, nbProcesseurs, endLigne);
			//pour le probleme
			int ramDispo[] = this.ramTab(ramDispoLigne,tab,nbProcesseurs);
			int affectationRam [][]=this.tabInteger2D(demandeRamLigne, tab, nbProcesseurs, nbTaches);
			int cout[][]= this.tabInteger2D(coutLigne, tab, nbProcesseurs, nbTaches);
			
			if(nbTacheMin != 0){
				//creation du problem
				pb = new Problem(nbTaches,nbProcesseurs,nbTacheMin, ramDispo,affectationRam,cout);
				 // creation de solution
				sol = new SolutionFractionnaire(pb,sum,  
				 affectTache);
				
			}else{
				//creation du problem
				pb = new Problem(nbTaches,nbProcesseurs, ramDispo,affectationRam,cout);
				// creation de solution
				sol = new SolutionFractionnaire(pb, sum, 
						 affectTache);
				
			}
		}catch(Exception e){
			System.out.println(e.toString());
			
		}
		return sol;
	}
	/**
	 * Transforme le texte en tableaux de double
	 * @param affectTacheLigne
	 * @param execTacheLigne
	 * @param tab
	 * @param nbTaches
	 * @param nbProcesseurs
	 * @return
	 */
	public double[][] affectTab(int affectTacheLigne, int execTacheLigne, String[] tab, int nbTaches, 
			int nbProcesseurs, int endLigne){
		double[][] affect = new double[nbTaches][nbProcesseurs];
		
		for(int i=affectTacheLigne+1; i<execTacheLigne && i<endLigne ;i++){
			String[] temp = tab[i].split(" ");
			for(int j = 0; j< temp.length-1; j++){
				double ajouter= Double.parseDouble(temp[j].trim());
				affect[i-affectTacheLigne-1][j] = ajouter;
				
			}
			
		}
		
		return affect;
	}


	/**
	 * Retourne le tableau des disponibilités de Ram sur les processeurs
	 */
	public int[] ramTab(int debutLigne, String[] tab, int nbProcesseurs){
		int[] ram = new int[nbProcesseurs];
		int finTable = debutLigne +1 + nbProcesseurs;
		
		for(int i=debutLigne+1; i<finTable ;i++){
			int ajouter= Integer.parseInt(tab[i].trim());
			ram[i-debutLigne-1]= ajouter;
			
		}
		return ram;
	}
	/**
	 * Retourne un tableau d'entiers, pour les données de coût et d'affectation du problème
	 */
	public int[][] tabInteger2D(int debutLigne, String[] tab, int nbProcesseurs, int nbTaches){
		int[][] result = new int[nbTaches][nbProcesseurs];
		int finTable = debutLigne +1 + nbTaches;
		
		for(int i=debutLigne+1; i<finTable ;i++){
			String[] temp = tab[i].split(" ");
			for(int j = 0; j< temp.length-1; j++){
				int ajouter= Integer.parseInt(temp[j].trim());
				result[i-debutLigne-1][j] = ajouter;
				
			}
			
		}
		return result;
	}
	
}