import java.util.HashMap;

/**
 * 
 * @author Morane Otilia
 *
 */
public class Problem {
	protected int n;
	/*nombre de taches*/
	protected int m; 
	/* nombre de processeurs */
	protected int nprim; 
	/* nombre mininal de taches a executer */
	private int  b[] = new int[m]; 
	/*quantite de RAM disponible sur processeur*/ 
	private int  a[][] = new int[n][m];  
	/*RAM d'une tache sur un processeur*/
	private int  c[][] = new int[n][m];  
	/*cout d'affectation*/
	
	private HashMap<Double, Solution> optim= new HashMap<Double,Solution>();
	/*Repertoire des solutions non-realisables*/
	private HashMap<Double, Solution> optimRealisable= new HashMap<Double,Solution>();
	/*Repertoire des solutions realisables*/
	
	/**
	 * Constructeur de probleme avec nombre de taches minimal (nprim)
	 * @param nbTaches
	 * @param nbProcesseurs
	 * @param nbTacheMin
	 * @param ramDispo
	 * @param affectationRam
	 * @param cout
	 */
	public Problem(int nbTaches,int nbProcesseurs,int nbTacheMin, int[] ramDispo, 
			int[][] affectationRam,int[][] cout){
		this.n= nbTaches;
		this.m= nbProcesseurs;
		this.nprim = nbTacheMin;
		this.b = ramDispo;
		this.a =  affectationRam;
		this.c = cout;
		
	}
	/**
	 * Constructeur de probleme avec executionde toutes les taches
	 * @param nbTaches
	 * @param nbProcesseurs
	 * @param ramDispo
	 * @param affectationRam
	 * @param cout
	 */
	public Problem(int nbTaches,int nbProcesseurs, int[] ramDispo, 
			int[][] affectationRam,int[][] cout){
		this.n= nbTaches;
		this.m= nbProcesseurs;
		this.b = ramDispo;
		this.a =  affectationRam;
		this.c = cout;
		
	}
	
	public String toString(){
		String pb="";
		pb +="Problème d'affectation\n";
		pb +="nombre de taches (n): "+ this.n + "\n";
		if(this.nprim != 0){
			pb +="nombre minimal de taches (n'): "+ this.nprim+ "\n";
		}
		pb += "nombre de processeurs (m):" + this.m + "\n";
		pb += "Les contraintes d'affectation de RAM demandée: \n";
		pb+= this.tab2DString(a);
		pb += "Le cout d'une tache sur un processeur: \n";
		pb+= this.tab2DString(c);
		pb += "Ram disponible sur chaque processeur: \n";
		pb+= this.tab1DString(b);
		
	
		return pb;
		
	}
	/**
	 * transforme le tableau 2D en String
	 * @return
	 */
	public String tab2DString(int[][] tab){
		String s ="";
		for(int i= 0; i<tab.length ;i++){
			for(int j = 0; j< tab[i].length; j++){
				s += tab[i][j] + " ";
			}
			s+=("\n");
		}
		return s;
	}
	/**
	 * transforme le tableau 1D en String
	 * @return
	 */
	public String tab1DString(int[] tab){
		String s ="";
		for(int i= 0; i<tab.length ;i++){
			s += tab[i];
			s+=("\n");
		}
		return s;
	}
	/**
	 * Verification d'une solution si elle est réalisable
	 */
	public boolean verifRamSol(Solution sol){
		boolean isSol = true;
		int[][] affectTache =sol.getAffectTacheEntier();
		int[] ramTotal = new int[m];
		for(int i = 0; i<ramTotal.length;i++){
			ramTotal[i]=0;
		}
		for(int i= 0; i<affectTache.length ;i++){
			for(int j = 0; j< affectTache[i].length; j++){
				ramTotal[j] += (affectTache[i][j]) * (this.a[i][j]);
				
			}
		}
		for(int i = 0; i<ramTotal.length;i++){
			if (ramTotal[i] > b[i]){
				System.out.println("solution: "+ramTotal[i]+" > "+b[i]);
				isSol = false;
			}
			System.out.println("solution: "+ramTotal[i]+" < "+b[i]);
		}
		/**
		 * Pendant la vérification, création d'un repertoire de solutions réalisable et non réalisable 
		 * avec leur résultat en tant que clé
		 */
		if(isSol){
			optimRealisable.put(sol.getSum(), sol);
		}else{
			optim.put(sol.getSum(), sol);
		}
		return isSol;
	}
}
