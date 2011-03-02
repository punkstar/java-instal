package uk.ac.bath.cs.agents.instal;

import java.io.Serializable;

public class Fluent extends Parameters implements Cloneable {
	public static int TYPE_PLAIN = 2;
	public static int TYPE_NONINERTIAL = 3;
	public static int TYPE_PERMISSION = 4;
	public static int TYPE_POWER = 5;
	
	protected Fluent(String name, int type) {
		super(name, Atom.ATOM_FLUENT, type);
	}
	
	public Fluent(String name) {
		this(name, Fluent.TYPE_PLAIN);
	}
	
	public Fluent(Fluent f, int type) {
	    this(f.getName(), type);
	    this.setParameters(f.getParameters());
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
    
    public InitiallyFluent initially() {
        return new InitiallyFluent(this, this.getParameterVariables());
    }
    
    public Fluent clone() {
        try {
            return (Fluent) super.clone();
        } catch (CloneNotSupportedException e) { e.printStackTrace(); }
        
        return null;
    }
}
