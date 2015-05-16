
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



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
			System.out.println("lecture fichier nouveau fichier à créer");
		} catch (IOException e){
			System.out.println ("lecture fichier problem de lecture");
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
		int nbTaches = 0;
		int nbProcesseurs = 0;
		double sum= 0;
		int nbTacheMin =0;
		int affectTacheLigne = 0;
		double[][] affectTache;
		int execTacheLigne = 1000;
		double [] execTache =null;
		boolean isNprim;
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
					
				//retrover la ligne de la solution d'affectation
				}else if(tab[i].startsWith("affectation")){
					affectTacheLigne = i;
				//retrover la ligne de la solution d'execution
				}else if(tab[i].startsWith("execution")|| tab[i].startsWith("Model") ){
					execTacheLigne = i;
				
				}
			}
			
			affectTache = this.affectTab(affectTacheLigne, execTacheLigne, tab, 
					 nbTaches, nbProcesseurs);
			if(nbTacheMin != 0){
				execTache = this.execTab(execTacheLigne, tab, 
						 nbTaches);
				sol = new Solution(nbTaches, nbProcesseurs, sum,  nbTacheMin,
				 affectTache,  execTache);
			}else{
				sol = new Solution(nbTaches, nbProcesseurs, sum, 
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
	public double[][] affectTab(int affectTacheLigne, int execTacheLigne, String[] tab, int nbTaches, int nbProcesseurs){
		double[][] affect = new double[nbTaches][nbProcesseurs];
		
		for(int i=affectTacheLigne+1; i<execTacheLigne ;i++){
			String[] temp = tab[i].split(" ");
			for(int j = 0; j< temp.length-1; j++){
				double ajouter= Double.parseDouble(temp[j].trim());
				affect[i-affectTacheLigne-1][j] = ajouter;
				
			}
			
		}
		
		return affect;
	}
	/**
	 * Transforme le texte en tableaux de double
	 * @param execTacheLigne
	 * @param tab
	 * @param nbTaches
	 * @return
	 */
	public double[] execTab(int execTacheLigne, String[] tab, int nbTaches){
		double[] exec = new double[nbTaches];
		
		for(int i=execTacheLigne+1; i<tab.length-1 ;i++){
			double ajouter= Double.parseDouble(tab[i].trim());
			exec[i-execTacheLigne-1]= ajouter;
			
		}
		return exec;
	}
	
}