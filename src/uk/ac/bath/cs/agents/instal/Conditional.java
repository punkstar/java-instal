package uk.ac.bath.cs.agents.instal;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public abstract class Conditional extends Atom implements Cloneable {
	protected ArrayList<Condition> _conditions = new ArrayList<Condition>();
	protected ArrayList<String[]> _conditionsVariables = new ArrayList<String[]>();
	
	protected Conditional(String name, int atom, int type) {
		super(name, atom, type);
	}
	
	public Conditional condition(boolean condition, Fluent f, String ... params) {
	    Condition c = new Condition(condition, f);
	    
	    c.setVariables(params);
	    
		this._conditions.add(c);
		this._conditionsVariables.add(params);
		
		return this;
	}
	
	public Conditional condition(Fluent f,  String ... params) {
		return this.condition(true, f, params);
	}
	
    public String _conditionsToString() {
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
    
    public Condition[] getConditionsTypeWithVariables() {
        Condition[] conditions = new Condition[this._conditions.size()];
        
        for (int i = 0; i < this._conditions.size(); i++) {
            conditions[i] = this._conditions.get(i).clone().setVariables(this._conditionsVariables.get(i));
        }
        
        return conditions;
    }
    
    public Condition[] getConditions() {
        Condition[] c = new Condition[this._conditions.size()];
        
        for (int i = 0; i < this._conditions.size(); i++) {
            c[i] = this._conditions.get(i).setVariables(this._conditionsVariables.get(i));
        }
        
        return c;
    }
    
    public Hashtable<String, Type> getConditionalVariablesTypeMap() {
        Hashtable<String, Type> table = new Hashtable<String, Type>();
        
        Iterator<Condition> conditions = this._conditions.iterator();
        while(conditions.hasNext()) {
            Condition condition = conditions.next();
            Fluent f = condition.getFluent();
            
            if (f == null || condition == null) continue;
            
            // Join the tables
            Hashtable<String, Type> semi_table =  f.getParameterVariablesTypeMap(condition.getVariables());
            
            if (semi_table != null) {
                Iterator<String> semi_iter = semi_table.keySet().iterator();
                while(semi_iter.hasNext()) {
                    String key = semi_iter.next();
                    table.put(key, semi_table.get(key));
                }
            }
        }
        
        return table;
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
