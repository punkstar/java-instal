package uk.ac.bath.cs.agents.instal.asp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Some simple permutation of terms
 * 
 * @author nrj
 *
 */

//
// EXAMPLE USAGE
//
//ArrayList<String[]> solutions = new ArrayList<String[]>();
//ArrayList<String[]> list = new ArrayList<String[]>();
//
//list.add(new String[] { "test", "notest" });
//list.add(new String[] { "a", "b", "c", "d", "e", "f" });
//list.add(new String[] { "1", "2", "3" });
//list.add(new String[] { "X", "Y", "Z" });
//
//Permuter p = new Permuter(list.size());
//p._dynamic(list);
//
//for (String[] s: p.permute()) {
//	this.solutions.add(s);
//}
//
//StringBuffer buf = new StringBuffer();
//for (String[] solution: this._solutions) {
//	buf.append("Solution: ");
//	buf.append(solution[0]);
//	for (int i = 1; i < solution.length; i++)  {
//		buf.append(", ").append(solution[i]);
//	}
//	buf.append("\n");
//}
//
//System.out.println(buf.toString());
//
// Produces:
//Solution: test, a, 1, X
//Solution: test, a, 1, Y
//Solution: test, a, 1, Z
//Solution: test, a, 2, X
//Solution: test, a, 2, Y
//Solution: test, a, 2, Z
//Solution: test, a, 3, X
//Solution: test, a, 3, Y
//Solution: test, a, 3, Z
//Solution: test, b, 1, X
//Solution: test, b, 1, Y
//Solution: test, b, 1, Z
//Solution: test, b, 2, X
//Solution: test, b, 2, Y
//Solution: test, b, 2, Z
//Solution: test, b, 3, X
//Solution: test, b, 3, Y
//Solution: test, b, 3, Z
//Solution: test, c, 1, X
//Solution: test, c, 1, Y
//Solution: test, c, 1, Z
//Solution: test, c, 2, X
//Solution: test, c, 2, Y
//Solution: test, c, 2, Z
//Solution: test, c, 3, X
//Solution: test, c, 3, Y
//Solution: test, c, 3, Z
//Solution: test, d, 1, X
//Solution: test, d, 1, Y
//Solution: test, d, 1, Z
//Solution: test, d, 2, X
//Solution: test, d, 2, Y
//Solution: test, d, 2, Z
//Solution: test, d, 3, X
//Solution: test, d, 3, Y
//Solution: test, d, 3, Z
//Solution: test, e, 1, X
//Solution: test, e, 1, Y
//Solution: test, e, 1, Z
//Solution: test, e, 2, X
//Solution: test, e, 2, Y
//Solution: test, e, 2, Z
//Solution: test, e, 3, X
//Solution: test, e, 3, Y
//Solution: test, e, 3, Z
//Solution: test, f, 1, X
//Solution: test, f, 1, Y
//Solution: test, f, 1, Z
//Solution: test, f, 2, X
//Solution: test, f, 2, Y
//Solution: test, f, 2, Z
//Solution: test, f, 3, X
//Solution: test, f, 3, Y
//Solution: test, f, 3, Z
//Solution: notest, a, 1, X
//Solution: notest, a, 1, Y
//Solution: notest, a, 1, Z
//Solution: notest, a, 2, X
//Solution: notest, a, 2, Y
//Solution: notest, a, 2, Z
//Solution: notest, a, 3, X
//Solution: notest, a, 3, Y
//Solution: notest, a, 3, Z
//Solution: notest, b, 1, X
//Solution: notest, b, 1, Y
//Solution: notest, b, 1, Z
//Solution: notest, b, 2, X
//Solution: notest, b, 2, Y
//Solution: notest, b, 2, Z
//Solution: notest, b, 3, X
//Solution: notest, b, 3, Y
//Solution: notest, b, 3, Z
//Solution: notest, c, 1, X
//Solution: notest, c, 1, Y
//Solution: notest, c, 1, Z
//Solution: notest, c, 2, X
//Solution: notest, c, 2, Y
//Solution: notest, c, 2, Z
//Solution: notest, c, 3, X
//Solution: notest, c, 3, Y
//Solution: notest, c, 3, Z
//Solution: notest, d, 1, X
//Solution: notest, d, 1, Y
//Solution: notest, d, 1, Z
//Solution: notest, d, 2, X
//Solution: notest, d, 2, Y
//Solution: notest, d, 2, Z
//Solution: notest, d, 3, X
//Solution: notest, d, 3, Y
//Solution: notest, d, 3, Z
//Solution: notest, e, 1, X
//Solution: notest, e, 1, Y
//Solution: notest, e, 1, Z
//Solution: notest, e, 2, X
//Solution: notest, e, 2, Y
//Solution: notest, e, 2, Z
//Solution: notest, e, 3, X
//Solution: notest, e, 3, Y
//Solution: notest, e, 3, Z
//Solution: notest, f, 1, X
//Solution: notest, f, 1, Y
//Solution: notest, f, 1, Z
//Solution: notest, f, 2, X
//Solution: notest, f, 2, Y
//Solution: notest, f, 2, Z
//Solution: notest, f, 3, X
//Solution: notest, f, 3, Y
//Solution: notest, f, 3, Z
//

class Permuter {
	ArrayList<String[]> _permutations = new ArrayList<String[]>();
	ArrayList<String[]> _dynamic = new ArrayList<String[]>();
	String _static;
	int _solutionSize;
	
	public Permuter() {
		this._solutionSize = -1;
	}
	
	public Permuter(int solution_size) {
		this._solutionSize = solution_size;
	}
	
	public Permuter _static(String var) {
		this._static = var;
		return this;
	}
	
	public Permuter _dynamic(List<String[]> list) {
		Iterator<String[]> iter = list.iterator();
		while (iter.hasNext()) {
			this._dynamic.add(iter.next());
		}
		return this;
	}
	
	public String[][] permute() {
		if (this._dynamic == null || this._dynamic.size() == 0) {
			return new String[][] {};
		}
		
		List<String[]> newDynamics = this._dynamic.subList(1, this._dynamic.size());
		
		if (this._static == null && this._dynamic.size() > 0) {
			String[] static_candidates = this._dynamic.get(0);
			
			for (int i = 0; i < static_candidates.length; i++) {
				Permuter p = new Permuter();
				p._static(static_candidates[i]);
				p._dynamic(newDynamics);
				
				String[][] solutions = p.permute();
				for (int j = 0; j < solutions.length; j++) {
					this._permutations.add(solutions[j]);
				}
			}
		} else if (this._static == null) {
			return new String[][]{};
		} else if (this._static != null){
			
			for (String s: this._dynamic.get(0)) {
				String[] solution = new String[2];
				solution[0] = this._static;
				solution[1] = s;
				
				this._permutations.add(solution);
			}
			
			if (this._dynamic.size() > 1) {
				for (int i = 0; i < this._dynamic.get(0).length; i++) {
					Permuter p = new Permuter();
					p._static(this._dynamic.get(0)[i]);
					p._dynamic(newDynamics);
					
					String[][] solutions = p.permute();
					for (int j = 0; j < solutions.length; j++) {
						this._permutations.add(this._appendStatic(this._static, solutions[j]));
					}
				}
			}
		}
		
		if (this._solutionSize > 0) {
			for(int i = this._permutations.size() - 1; i >= 0; i--) {
				if (this._permutations.get(i).length < this._solutionSize) {
					this._permutations.remove(i);
				}
			}
		}
		
		return this._permutations.toArray(new String[][] {});
	}
	
	protected String[] _appendStatic(String static_var, String[] perm) {
		String[] result = new String[perm.length+1];
		
		result[0] = static_var;
		for (int i = 0; i < perm.length; i++) {
			result[i+1] = perm[i];
		}
		
		return result;
	}
}