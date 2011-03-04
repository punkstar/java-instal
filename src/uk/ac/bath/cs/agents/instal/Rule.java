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
		return this.__join(this._resultAtoms, this._resultAtomVariables, ", ");
	}
	
	public ArrayList<Parameters> getResultAtoms() {
	    return this._resultAtoms;
	}
	
	public ArrayList<String[]> getResultAtomVariables() {
	    return this._resultAtomVariables;
	}
	
	public String[] getResultAtomsWithVariables() {
	    String[] atoms = new String[this._resultAtoms.size()];
	    
	    for (int i = 0; i < this._resultAtoms.size(); i++) {
	        atoms[i] = this._resultAtoms.get(i).asVariablesToString(this._resultAtomVariables.get(i));
	    }
	    
	    return atoms;
	}
	
	public Hashtable<String, Type> getResultAtomsTypeMap() {
	    Hashtable<String, Type> table = new Hashtable<String, Type>();
	    
	    for(int i = 0; i < this._resultAtoms.size(); i++) {
	        Hashtable<String, Type> semi_map = this._resultAtoms.get(i).getParameterVariablesTypeMap(this._resultAtomVariables.get(i));
	        
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
        if (s == null || s.isEmpty()) return "";
        Iterator<Parameters> iter = s.iterator();
        Iterator<String[]> iter_v = v.iterator();
        StringBuilder builder = new StringBuilder(iter.next().asVariablesToString(iter_v.next()));
        while( iter.hasNext() && iter_v.hasNext() )
        {
            builder.append(delimiter).append(iter.next().asVariablesToString(iter_v.next()));
        }
        return builder.toString();
    }
}
