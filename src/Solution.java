/**
 * Une Solution est créé à partir d'un fichier txt après une génération de celui-ci avec glpk
 * @author Coyotito
 *
 */
public class Solution {
	private int nbTaches;
	private int nbProcesseurs;
	private double sum;
	private int nbTacheMin;
	private double [][] affectTache;
	private double [] execTache;
	private boolean isMinTaches;
	
	/**
	 * Constructeur de solution avec n' tâches (minimum de tâches à exécuter)
	 * @param nbTaches
	 * @param nbProcesseurs
	 * @param sum
	 * @param nbTacheMin
	 * @param affectTache
	 * @param execTache
	 */
	public Solution(int nbTaches,int nbProcesseurs,double sum, int nbTacheMin,
			double[][] affectTache, double[] execTache ){
		this.nbTaches= nbTaches;
		this.nbProcesseurs =nbProcesseurs;
		this.sum= sum;
		this.nbTacheMin = nbTacheMin;
		this.affectTache = affectTache;
		this.execTache = execTache;
		this.isMinTaches = true;
	}
	/**
	 * Constructeur de solution sans n' tâches
	 * @param nbTaches
	 * @param nbProcesseurs
	 * @param sum
	 * @param affectTache
	 */
	public Solution(int nbTaches,int nbProcesseurs,double sum,
			double[][] affectTache){
		this.nbTaches= nbTaches;
		this.nbProcesseurs =nbProcesseurs;
		this.sum= sum;
		this.affectTache = affectTache;
		this.isMinTaches = false;
		
	}
	
	public int getNbTaches() {
		return nbTaches;
	}
	public void setNbTaches(int nbTaches) {
		this.nbTaches = nbTaches;
	}
	public int getNbProcesseurs() {
		return nbProcesseurs;
	}
	public void setNbProcesseurs(int nbProcesseurs) {
		this.nbProcesseurs = nbProcesseurs;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public int getNbTacheMin() {
		return nbTacheMin;
	}
	public void setNbTacheMin(int nbTacheMin) {
		this.nbTacheMin = nbTacheMin;
	}
	public double[][] getAffectTache() {
		return affectTache;
	}
	public void setAffectTache(double[][] affectTache) {
		this.affectTache = affectTache;
	}
	public double[] getExecTache() {
		return execTache;
	}
	public void setExecTache(double[] execTache) {
		this.execTache = execTache;
	}
	
	public String printSolution(){
		String sol="";
		sol +="Problème d'affectation\n";
		sol += "cout minimal:"+ this.sum + "\n";
		sol +="nombre de taches (n): "+ this.nbTaches + "\n";
		if(this.isMinTaches){
			sol +="nombre minimal de taches (n'): "+ this.nbTacheMin+ "\n";
		}
		sol += "nombre de processeurs (m):" + this.nbProcesseurs + "\n";
		sol += "La solution tache par processeur: \n";
		sol += this.solEnEntiers();
		if(this.isMinTaches){
			sol +="execution des taches (y): \n";
			sol += this.solExecEnEntiers();
		}
		return sol;
		
	}
	public String solEnEntiers(){
		String sol ="";
		for(int i= 0; i<this.affectTache.length ;i++){
			for(int j = 0; j< this.affectTache[i].length; j++){
				int imprimer = (int) Math.round(this.affectTache[i][j]);
				sol += imprimer +" ";
			}
			sol+=("\n");
		}
		
		return sol;
	}
	public String solExecEnEntiers(){
		String sol ="";
		for(int i= 0; i<this.execTache.length ;i++){
			int imprimer = (int) Math.round(this.execTache[i]);
			sol+= imprimer;
			sol+=("\n");
		}
		return sol;
	}
}
