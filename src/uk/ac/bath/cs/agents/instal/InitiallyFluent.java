package uk.ac.bath.cs.agents.instal;

import java.util.Hashtable;

public class InitiallyFluent {
    protected Fluent _f;
    
    public InitiallyFluent(Fluent f, String[] vars) {
        this._f = f.clone();
        this._f.setParameterVariables(vars);
    }
    
    public InitiallyFluent(Fluent f) {
        this(f, new String[] {});
    }
    
    public String definitionToString() {
        return String.format("initally %s;", this._f.toString());
    }
    
    public String toString() {
        return this._f.asVariablesToString(this._f.getParameterVariables());
    }
    
    public Fluent getFluent() {
        return this._f;
    }
    
    public boolean hasUngroundedVariables() {
        for(String s: this._f.getParameterVariables()) {
            if (Character.isUpperCase(s.charAt(0))) {
                return true;
            }
        }
        
        return false;
    }
    
    public Hashtable<String, Type> getUngroundedVariableTypeMap() {
        Hashtable<String, Type> type_map = new Hashtable<String, Type>();
        
        for(int i = 0; i < this._f.getParameterVariables().length; i++) {
            String v = this._f.getParameterVariables()[i];
            if (Character.isUpperCase(v.charAt(0))) {
                Type t = this._f.getParameterTypeAtPosition(i);
                type_map.put(v, t);
            }
        }
        
        return type_map;
    }
}
