package uk.ac.bath.cs.agents.instal;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public abstract class Parameters extends Atom implements Cloneable {
	ArrayList<Type> _parameters = new ArrayList<Type>();
	ArrayList<String> _parameterNames = new ArrayList<String>();
	ArrayList<String[]> _parameterConstraintTuples = new ArrayList<String[]>();
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
		return this.addParameter(t, null);
	}
	
	public Parameters addParameter(Type t, String name) {
	    this._parameters.add(t);
	    this._parameterNames.add(name);
	    return this;
	}
	
	public boolean hasParameterConditions() {
	    return this._parameterConstraintTuples.size() > 0;
	}
	
	public Parameters constraint(String x, String operation, String y) {
	    String[] tuple = new String[] { x, operation, y };
	    this._parameterConstraintTuples.add(tuple);
	    return this;
	}
	
	public Parameters setParameters(ArrayList<Type> types) {
	    this._parameters = types; return this;
	}
	
	public ArrayList<Type> getParameters() {
	    return this._parameters;
	}
	
	public void setParameterVariables(String ... vars) {
	    if (vars != null && vars.length > 0) {
	        this._variables = new ArrayList<String>(vars.length);
	        for (String var : vars) {
	            this._variables.add(var);
	        }
	    }
	}
	
	public String[] getParameterVariables() {
		if (this.hasVariables()) {
			return this._variables.toArray(new String[] {});
		} else {
			return new String[] {};
		}
	}
	
	public Hashtable<String, Type> getParameterVariablesTypeMap(String[] variables) {
	    Hashtable<String, Type> table = new Hashtable<String,Type>();
	    
	    if (variables == null || variables.length == 0) {
	    	return null;
	    }
	    
	    for (int i = 0; i < variables.length; i++) {
	        Type t = this.getParameterTypeAtPosition(i);
	        
	        if (t == null) {
	            System.err.println("Type is null");
	            continue;
	        }
	        
	        table.put(variables[i], t);
	    }
	    
	    return table;
	}
	
	public Hashtable<String, Type> getParameterNameTypeMap() {
	    Hashtable<String, Type> type_map = new Hashtable<String, Type>();
	    
	    for (int i = 0; i < this._parameterNames.size(); i++) {
	        String name = this._parameterNames.get(i);
	        
	        if (name != null) {
	            type_map.put(name, this._parameters.get(i));
	        }
	    }
	    
	    return type_map;
	}
	
	/**
	 * For each constraint tuple we have, swap in the signature parameter with our variables, in the argument.
	 * 
	 * @param variables
	 * @return
	 */
	public ArrayList<String[]> getParameterConstraints(String[] variables) {
	    ArrayList<String[]> constraints = new ArrayList<String[]>();
	    
	    for (int i = 0; i < this._parameterConstraintTuples.size(); i++) {
	        try {
	            String[] tuple = this._parameterConstraintTuples.get(i).clone();
	            
    	        tuple[0] =  variables[this._getPositionOfParameterName(tuple[0])];
    	        tuple[2] =  variables[this._getPositionOfParameterName(tuple[2])];
	        
    	        constraints.add(tuple);
	        } catch (Exception e) {
	            System.err.println("Exception, getParameterConstraints(): " + e.getMessage());
	        }
	    }
	    
	    return constraints;
	}
	
	protected int _getPositionOfParameterName(String name) throws Exception {
	    for (int i = 0; i < this._parameterNames.size(); i++) {
	        if (this._parameterNames.get(i).equals(name)) {
	            return i;
	        }
	    }
	    
	    throw new Exception("Could not find parameter constraint with name: " + name);
	}
	
	protected int _getPositionOfVariableName(String name) {
	    for (int i = 0; i < this._variables.size(); i++) {
	        if (this._variables.get(i).equals(name)) {
	            return i;
	        }
	    }
	    
	    return -1;
	}
	
    public String toString() {
    	return String.format(
    		"%s%s%s",
    		this._name.toString(),
    		this._parametersWithParenthesisToString(),
    		this.parameterConditionsToString()
    	);
    }
    
    public String parameterConditionsToString() {
        StringBuilder builder = new StringBuilder();
        Iterator<String[]> iter = this._parameterConstraintTuples.iterator();
        while (iter.hasNext()) {
            String[] type = iter.next();
            
            builder.append(" ")
                   .append(type[0]).append(" ")
                   .append(type[1]).append(" ")
                   .append(type[2]);
        }
        
        if (builder.length() > 0) {
            builder.insert(0, " where");
        }
        
        return builder.toString();
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
        ArrayList<String> namePrefixedParameters = new ArrayList<String>();
        for (int i = 0; i < this._parameters.size(); i++) {
            String param = this._parameters.get(i).toString();
            if (this._parameterNames.get(i) != null) {
                String name = this._parameterNames.get(i);
                param = name + ":" + param;
            }
            namePrefixedParameters.add(param);
        }
        return this.__join(namePrefixedParameters, ", ");
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
