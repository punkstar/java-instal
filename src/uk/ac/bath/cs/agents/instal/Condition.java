package uk.ac.bath.cs.agents.instal;

import java.io.Serializable;

public class Condition implements Cloneable, Serializable {
    protected boolean _condition;
    protected Fluent _fluent;
    protected String[] _variables;
    
    public Condition(boolean condition, Fluent f) {
        this._condition = condition;
        this._fluent = f;
    }
    
    public boolean isNegated() {
        return !this._condition;
    }
    
    public Condition setVariables(String[] variables) {
        this._variables = variables; return this;
    }
    
    public String asVariablesToStringWithCondition(String[] variables) {
        return String.format(
            "%s%s",
            (this._condition) ? "" : "not ",
            this._fluent.asVariablesToString(variables)
        );
    }
    
    public String asVariablesToString() {
        return this._fluent.asVariablesToString(this._variables);
    }
    
    public Fluent getFluent() {
        return this._fluent;
    }
    
    public String[] getVariables() {
        return this._variables;
    }
    
    public Condition clone() {
        try {
            return (Condition) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
}