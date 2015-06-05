/**
 * Une Solution est créé à partir d'un fichier txt après une génération de celui-ci avec glpk
 * @author Morane Otilia
 *
 */
public class SolutionFractionnaire extends Solution{
	private double [][] affectTache;
	
	/**
	 * Constructeur de solution 
	 * @param nbTaches
	 * @param nbProcesseurs
	 * @param sum
	 * @param nbTacheMin
	 * @param affectTache
	 * @param execTache
	 */
	public SolutionFractionnaire(Problem pb, double sum,
			double[][] affectTache ){
		this.pb = pb;
		this.sum= sum;
		this.affectTache = affectTache;
		this.affectTacheEntier = this.transformeSolEnEntiers();
	}

	/**
	 * getters et setters
	 * @return
	 */	

	public double[][] getAffectTache() {
		return this.affectTache;
	}
	
	public void setAffectTache(double[][] affectTache) {
		this.affectTache = affectTache;
	}
	

	/**
	 * Imprime la solution, en transformant les tableaux de double en entiers à la sortie
	 * @return
	 */
	public String printSolution(){
		String sol="";
		sol +="La solution d'affectation directement arrondi d'une sortie glpsol\n";
		sol += "cout minimal:"+ this.sum + "\n";
		sol += "La solution tache par processeur: \n";
		sol += this.solEnEntiers();
	
		return sol;
		
	}
	/**
	 * transforme le tableau d'affectation de la solution en entiers par String
	 * @return
	 */
	public String solEnEntiers(){
		String sol ="";
		for(int i= 0; i<this.affectTache.length ;i++){
			for(int j = 0; j< this.affectTache[i].length; j++){
				int imprimer = (int) Math.round(this.affectTache[i][j]);
				sol += imprimer +" ";
			}
			sol += ("\n");
		}
		return sol;
	}
	/**
	 * 
	 * @return
	 */
	public int[][] transformeSolEnEntiers(){
		int[][] entiers = new int[this.pb.n][this.pb.m];
		for(int i= 0; i<this.affectTache.length ;i++){
			for(int j = 0; j< this.affectTache[i].length; j++){
				int imprimer = (int) Math.round(this.affectTache[i][j]);
				entiers[i][j] = imprimer;
			}
		}
		
		return entiers;
	}

}
