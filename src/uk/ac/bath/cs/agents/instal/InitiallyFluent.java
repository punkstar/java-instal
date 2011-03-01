package uk.ac.bath.cs.agents.instal;

public class InitiallyFluent extends Fluent {
    public InitiallyFluent(Fluent f, String[] vars) {
        super(f.getName().toString());
        this.setParameterVariables(vars);
    }
    
    public String definitionToString() {
        return String.format("initally %s;", this.toString());
    }
    
    public String toString() {
        return this.asVariablesToString(this._variables.toArray(new String[] {}));
    }
}
