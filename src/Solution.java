/**
 * Une Solution abstraite pour d�finir une solution g�n�rique qui peut �tre fractionnaire ou enti�re 
 * @author Morane Otilia
 *
 */
public abstract class Solution {
	protected Problem pb;
	protected double sum;
	protected double [][] affectTache;
	protected int [][] affectTacheEntier;
	
	
	/**
	 * getters et setters
	 * @return
	 */	
	public double getSum() {
		return this.sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}

	public double[][] getAffectTache() {
		return this.affectTache;
	}
	
	public void setAffectTache(double[][] affectTache) {
		this.affectTache = affectTache;
	}
	
	public Problem getPB(){
		return this.pb;
	}
	public void setPB(Problem pb){
		this.pb = pb;
	}
	public int [][] getAffectTacheEntier() {
		return affectTacheEntier;
	}
	public void setAffectTacheEntier(int [][] affectTacheEntier) {
		this.affectTacheEntier = affectTacheEntier;
	}

	/**
	 * Imprime la solution, en transformant les tableaux de double en entiers � la sortie
	 * @return
	 */
	public String printSolution(){
		return null;
	}
	

}
