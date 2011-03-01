package uk.ac.bath.cs.agents.instal;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public abstract class Parameters extends Atom implements Cloneable {
	ArrayList<Type> _parameters = new ArrayList<Type>();
	ArrayList<String> _variables = null;
	
	public Parameters(String name, int atom, int type) {
		super(name, atom, type);
	}
	
	public Type getParameterTypeAtPosition(int position) {
	    if (this._parameters.size() > position) {
	        return this._parameters.get(position);
	    } else {
	        return null;
	    }
	}
	
	public Parameters addParameter(Type t) {
		this._parameters.add(t); return this;
	}
	
	public void setParameterVariables(String ... vars) {
		this._variables = new ArrayList<String>(vars.length);
		for (String var : vars) {
			this._variables.add(var);
		}
	}
	
	public String[] getParameterVariables() {
		if (this.hasVariables()) {
			return this._variables.toArray(new String[] {});
		} else {
			return null;
		}
	}
	
	public Hashtable<String, Type> getParameterVariablesTypeMap(String[] variables) {
	    Hashtable<String, Type> table = new Hashtable<String,Type>();
	    
	    if (variables == null) {
	    	return null;
	    }
	    
	    for (int i = 0; i < variables.length; i++) {
	        table.put(variables[i], this.getParameterTypeAtPosition(i));
	    }
	    
	    return table;
	}
	
    public String toString() {
    	return String.format(
    		"%s%s",
    		this._name.toString(),
    		this._parametersWithParenthesisToString()
    	);
    }
    
    public String asVariablesToString(String[] variables) {
    	return String.format(
    		"%s%s",
    		this._name.toString(),
    		this.getVariablesWithParenthesisToString(variables)
    	);
    }
    
    protected String _parametersWithParenthesisToString() {
    	if (this._parameters.size() > 0) {
    		return String.format("(%s)", this._parametersToString());
    	}
    	
    	return "";
    }
    
    public String getVariablesWithParenthesisToString(String[] variables) {
    	if (variables.length > 0) {
    		return String.format("(%s)", this._variablesToString(variables));
    	}
    	
    	return "";
    }
    
    public boolean hasVariables() {
    	return this._variables != null;
    }
    
    protected String _parametersToString() {
        return this.__join(this._parameters, ", ");
    }
    
    protected String _variablesToString(String[] variables) {
    	return this.__join(variables, ", ");
    }
    
    private String __join(AbstractCollection s, String delimiter) {
        if (s == null || s.isEmpty()) return "";
        Iterator iter = s.iterator();
        StringBuilder builder = new StringBuilder(iter.next().toString());
        while( iter.hasNext() )
        {
            builder.append(delimiter).append(iter.next());
        }
        return builder.toString();
    }
    
    private String __join(String[] s, String deliminator) {
    	if (s == null || s.length == 0) return "";
    	StringBuilder builder = new StringBuilder(s[0]);
    	
    	for (int i = 1; i < s.length; i++) {
    		builder.append(deliminator);
    		builder.append(s[i]);
    	}
    	
    	return builder.toString();
    }
}
