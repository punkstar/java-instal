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
	
	protected class Condition {
        protected boolean _condition;
        protected Fluent _fluent;
        
        public Condition(boolean condition, Fluent f) {
            this._condition = condition;
            this._fluent = f;
        }
        
        public String asVariablesToString(String[] variables) {
        	return String.format(
        		"%s%s",
        		(this._condition) ? "" : "not ",
        		this._fluent.asVariablesToString(variables)
        	);
        }
	}
	
    protected String _conditionsToString() {
    	if (this._conditions.size() > 0) {
            return "if " + this.__join(this._conditions, this._conditionsVariables, ", ");
    	} else {
    		return "";
    	}
    }
    
    private String __join(AbstractCollection<Condition> s, AbstractCollection<String[]> v, String delimiter) {
        if (s == null || s.isEmpty()) return "";
        Iterator<Condition> iter = s.iterator();
        Iterator<String[]> iter_v = v.iterator();
        StringBuilder builder = new StringBuilder(iter.next().asVariablesToString(iter_v.next()));
        while( iter.hasNext() && iter_v.hasNext() )
        {
            builder.append(delimiter).append(iter.next().asVariablesToString(iter_v.next()));
        }
        return builder.toString();
    }
}
