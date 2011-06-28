package uk.ac.bath.cs.agents.instal;

public class NoninertialFluent extends Fluent {
    public static final int NONINERT_MAIN = 2;
    public static final int NONINERT_CONDITIONS = 3;
    
    private NoninertialConditions _conditions;
    
	public NoninertialFluent(String name, String ... variables) {
		super(name, Atom.ATOM_NONINERTIAL);
		
		this.setParameterVariables(variables);
		this._conditions = new NoninertialConditions(this);
	}
	
    public NoninertialFluent condition(boolean condition, Fluent f, String ... params) {
        this._conditions.condition(condition, f, params);
        return this;
    }
    
    public NoninertialFluent condition(Fluent f,  String ... params) {
        return this.condition(true, f, params);
    }
    
    public Conditional getConditions() {
        return this._conditions;
    }
    
    /**
     * We need to override the following so that we get a NoninertialFluent back when we chain the methods,
     * instead of a Parameters.
     */
    
    @Override
    public NoninertialFluent addParameter(Type t, String name) {
        return (NoninertialFluent) super.addParameter(t, name);
    }
    
    @Override
    public NoninertialFluent addParameter(Type t) {
        return (NoninertialFluent) super.addParameter(t);
    }
}
