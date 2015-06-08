import java.util.HashMap;

import model.Problem;
import model.Solution;

/**
 * GestionSolution récupère des entitées solutions à un problème et lance des calculs
 * de vérification, réparation et optimisation de laslution
 * @author Morane Otilia
 *
 */
public class GestionSolution {
	protected Problem pb;
	
	private HashMap<Double, Solution> optim= new HashMap<Double,Solution>();
	/*Repertoire des solutions non-realisables*/
	private HashMap<Double, Solution> optimRealisable= new HashMap<Double,Solution>();
	/*Repertoire des solutions realisables*/
	
	
	/**
	 * 
	 * @param sol
	 */
	public void verifRamSol(Solution sol){
		boolean isSol = pb.verifRamSol(sol);
		/**
		 * Pendant la vérification, création d'un repertoire de solutions réalisable et non réalisable 
		 * avec leur résultat en tant que clé
		 */
		if(isSol){
			optimRealisable.put(sol.getSum(), sol);
		}else{
			optim.put(sol.getSum(), sol);
		}
	}
	

}
