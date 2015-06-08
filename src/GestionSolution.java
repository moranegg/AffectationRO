import java.util.HashMap;

import model.Problem;
import model.Solution;

/**
 * GestionSolution r�cup�re des entit�es solutions � un probl�me et lance des calculs
 * de v�rification, r�paration et optimisation de laslution
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
		 * Pendant la v�rification, cr�ation d'un repertoire de solutions r�alisable et non r�alisable 
		 * avec leur r�sultat en tant que cl�
		 */
		if(isSol){
			optimRealisable.put(sol.getSum(), sol);
		}else{
			optim.put(sol.getSum(), sol);
		}
	}
	

}
