package uk.ac.bath.cs.agents.instal;


public abstract class Event extends Parameters {
	public static final int TYPE_EXOGENEOUS  = 2;
	public static final int TYPE_NORMATIVE   = 3;
	public static final int TYPE_CREATION    = 4;
	public static final int TYPE_VIOLATION   = 5;
	public static final int TYPE_DISSOLUTION = 6;
	
	public Event(String name, int type) {
		super(name, Atom.ATOM_EVENT, type);
	}
	
    public String definitionToString() {
        return String.format("%s event %s;\n", this._typeToString(this._type), this.toString());
    }
    
    protected String _typeToString(int type) {
    	switch (type) {
    	case TYPE_EXOGENEOUS:
    		return "exogenous";
    	case TYPE_NORMATIVE:
    		return "normative";
    	case TYPE_CREATION:
    		return "creation";
    	case TYPE_VIOLATION:
    		return "violation";
    	case TYPE_DISSOLUTION:
    	    return "diss";
		default:
			return "";  
		}
    }
    
    public ViolationEvent viol() {
    	return new ViolationEvent(this);
    }
    
    public PermissionFluent perm(String ... variables) {
        PermissionFluent f = new Fluent(this.getName()).perm();
        f.setParameters(this.getParameters());
        f.setParameterVariables(variables);
        return f;
    }
    
    public PowerFluent pow(String ... variables) {
        PowerFluent f = new Fluent(this.getName()).pow();
        f.setParameters(this.getParameters());
        f.setParameterVariables(variables);
        return f;
    }
}
