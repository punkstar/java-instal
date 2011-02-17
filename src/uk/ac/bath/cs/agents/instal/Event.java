package uk.ac.bath.cs.agents.instal;


public abstract class Event extends Parameters {
	public static final int TYPE_EXOGENEOUS = 2;
	public static final int TYPE_NORMATIVE  = 3;
	public static final int TYPE_CREATION   = 4;
	public static final int TYPE_VIOLATION  = 5;
	
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
		default:
			return "";  
		}
    }
}
