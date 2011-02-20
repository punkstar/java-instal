package uk.ac.bath.cs.agents.instal;

public class Condition {
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
}