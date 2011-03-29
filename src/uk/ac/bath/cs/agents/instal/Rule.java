package uk.ac.bath.cs.agents.instal;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public abstract class Rule extends Conditional {
	public static int TYPE_INITIATES = 2;
	public static int TYPE_TERMINATES = 3;
	public static int TYPE_GENERATES = 4;
	
	Event _sourceEvent;
	String[] _sourceEventVariables;
	
	ArrayList<Parameters> _resultAtoms = new ArrayList<Parameters>();
	ArrayList<String[]> _resultAtomVariables = new ArrayList<String[]>();
	
	public Rule(Event e, String[] args, int atom, int type) {
		super("", atom, type);
		this._sourceEvent = e;
		this._sourceEventVariables = args;
	}
	
	public Rule result(Parameters e, String ... args) {
	    this._resultAtomVariables.add(args);
		this._resultAtoms.add(e);
		return this;
	}
	
	public Rule result(Parameters e) {
		return this.result(e, new String[] {});
	}
	
	protected String _resultAtomsToString() {
	    if (this._resultAtoms != null && this._resultAtomVariables != null && this._resultAtoms.size() > 0 && this._resultAtomVariables.size() > 0) {
	        return this.__join(this._resultAtoms, this._resultAtomVariables, ", ");
	    } else {
	        return "";
	    }
	}
	
	public ArrayList<Parameters> getResultAtoms() {
	    return this._resultAtoms;
	}
	
	public ArrayList<String[]> getResultAtomVariables() {
	    return this._resultAtomVariables;
	}
	
	public String[] getResultAtomsWithVariables() {
	    ArrayList<String> atoms = new ArrayList<String>(this._resultAtoms.size());
	    
	    for (int i = 0; i < this._resultAtoms.size(); i++) {
	        Parameters a = this._resultAtoms.get(i);
	        
	        if (a != null) {
	            atoms.add(a.asVariablesToString(this._resultAtomVariables.get(i)));
	        }
	    }
	    
	    return atoms.toArray(new String[] {});
	}
	
	public Hashtable<String, Type> getResultAtomsTypeMap() {
	    Hashtable<String, Type> table = new Hashtable<String, Type>();
	    
	    for(int i = 0; i < this._resultAtoms.size(); i++) {
	        Parameters p = this._resultAtoms.get(i);
	        String[] vars = this._resultAtomVariables.get(i);
	        
	        if (p == null || vars == null) continue;
	        
	        Hashtable<String, Type> semi_map = p.getParameterVariablesTypeMap(vars);
	        
	        if (semi_map != null) {
	            Iterator<String> iter = semi_map.keySet().iterator();
	            while (iter.hasNext()) {
	                String key = iter.next();
	                table.put(key, semi_map.get(key));
	            }
	        }
	    }
	    
	    return table;
	}
	
	public Event getSourceEvent() {
	    return this._sourceEvent;
	}
	
	public String[] getSourceEventVariables() {
	    return this._sourceEventVariables;
	}
	
	public String _getSourceEventWithVariables() {
		return this.getSourceEvent().asVariablesToString(this._sourceEventVariables);
	}
    
    private String __join(AbstractCollection<Parameters> s, AbstractCollection<String[]> v, String delimiter) {
        if (s == null || s.isEmpty() || s.size() == 0 || v == null || v.isEmpty() || v.size() ==  0) return "";
        Iterator<Parameters> iter = s.iterator();
        Iterator<String[]> iter_v = v.iterator();
        
        Parameters first_s = iter.next();
        String[] first_v = iter_v.next();
        
        if (first_s == null || first_v == null) return "";
        
        StringBuilder builder = new StringBuilder(first_s.asVariablesToString(first_v));
        
        while( iter.hasNext() && iter_v.hasNext() )
        {
            builder.append(delimiter).append(iter.next().asVariablesToString(iter_v.next()));
        }
        return builder.toString();
    }
}
