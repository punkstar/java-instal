package uk.ac.bath.cs.agents.instal;

public class Fluent extends Parameters {
	public static int TYPE_PLAIN = 2;
	public static int TYPE_NONINERTIAL = 3;
	
	protected Fluent(String name, int type) {
		super(name, Atom.ATOM_FLUENT, type);
	}
	
	public Fluent(String name) {
		this(name, Fluent.TYPE_PLAIN);
	}
	
    public String definitionToString() {
        return String.format("fluent %s;\n", this.toString());
    }
    
    public PowerFluent pow() {
    	return new PowerFluent(this);
    }
    
    public PermissionFluent perm() {
    	return new PermissionFluent(this);
    }
    
    public InitiallyFluent initially(String ... vars) {
        return new InitiallyFluent(this, vars);
    }
}
