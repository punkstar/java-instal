package uk.ac.bath.cs.agents.instal;

public class Obligation extends Parameters {
    protected Event _act, _before, _otherwise;
    protected String[] _actVars, _beforeVars, _otherwiseVars;

    public Obligation() {
        super("", Atom.ATOM_OBLIGATION, 0);
    }
    
    public Obligation act(Event e, String ... vars) {
        this._act = e;
        this._actVars = vars;
        e.setParameterVariables(vars);
        return this;
    }
    
    public Obligation before(Event e, String ... vars) {
        this._before = e;
        this._beforeVars = vars;
        e.setParameterVariables(vars);
        return this;
    }
    
    public Obligation otherwise(Event e, String ... vars) {
        this._otherwise = e;
        this._otherwiseVars = vars;
        e.setParameterVariables(vars);
        return this;
    }
    
    public Event getAct() {
        return this._act;
    }
    
    public String[] getActVars() {
        return this._actVars;
    }
    
    public Event getBefore() {
        return this._before;
    }
    
    public String[] getBeforeVars() {
        return this._beforeVars;
    }
    
    public Event getOtherwise() {
        return this._otherwise;
    }
    
    public String[] getOtherwiseVars() {
        return this._otherwiseVars;
    }
    
    public String toString() {
        return String.format(
            "%s;\n",
            this.asVariablesToString(new String[] {})
        );
    }
    
    public String asVariablesToString(String[] vars) {
        return String.format(
            "obl(%s, %s, %s)",
            this._act.asVariablesToString(this._actVars),
            this._before.asVariablesToString(this._beforeVars),
            this._otherwise.asVariablesToString(this._otherwiseVars)
        );
    }
}
