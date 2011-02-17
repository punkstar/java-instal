package uk.ac.bath.cs.agents.instal;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Parameters extends Atom {
	ArrayList<Type> _parameters = new ArrayList<Type>();
	ArrayList<String> _variables = null;
	
	public Parameters(String name, int atom, int type) {
		super(name, atom, type);
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
	
    public String toString() {
    	return String.format(
    		"%s(%s)",
    		this._name.toString(),
    		this._parametersToString()
    	);
    }
    
    public String asVariablesToString(String[] variables) {
    	return String.format(
    		"%s(%s)",
    		this._name.toString(),
    		this._variablesToString(variables)
    	);
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
    
    public Parameters pow() {
    	return this;
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
