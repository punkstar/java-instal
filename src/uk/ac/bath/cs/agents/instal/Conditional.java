package uk.ac.bath.cs.agents.instal;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Conditional extends Atom {
	protected ArrayList<Condition> _conditions = new ArrayList<Condition>();
	protected ArrayList<String[]> _conditionsVariables = new ArrayList<String[]>();
	
	protected Conditional(String name, int atom, int type) {
		super(name, atom, type);
	}
	
	public Conditional condition(boolean condition, Fluent f, String ... params) {
		this._conditions.add(new Condition(condition, f));
		this._conditionsVariables.add(params);
		return this;
	}
	
	public Conditional condition(Fluent f,  String ... params) {
		return this.condition(true, f, params);
	}
	
    protected String _conditionsToString() {
    	if (this._conditions.size() > 0) {
            return "if " + this.__join(this._conditions, this._conditionsVariables, ", ");
    	} else {
    		return "";
    	}
    }
    
    public String[] getConditionsWithVariables() {
        String[] atoms = new String[this._conditions.size()];
        
        for (int i = 0; i < this._conditions.size(); i++) {
            atoms[i] = this._conditions.get(i).asVariablesToStringWithCondition(this._conditionsVariables.get(i));
        }
        
        return atoms;
    }
    
    public Condition[] getConditions() {
        Condition[] c = new Condition[this._conditions.size()];
        
        for (int i = 0; i < this._conditions.size(); i++) {
            c[i] = this._conditions.get(i).setVariables(this._conditionsVariables.get(i));
        }
        
        return c;
    }
    
    private String __join(AbstractCollection<Condition> s, AbstractCollection<String[]> v, String delimiter) {
        if (s == null || s.isEmpty()) return "";
        Iterator<Condition> iter = s.iterator();
        Iterator<String[]> iter_v = v.iterator();
        StringBuilder builder = new StringBuilder(iter.next().asVariablesToStringWithCondition(iter_v.next()));
        while( iter.hasNext() && iter_v.hasNext() )
        {
            builder.append(delimiter).append(iter.next().asVariablesToStringWithCondition(iter_v.next()));
        }
        return builder.toString();
    }
}
