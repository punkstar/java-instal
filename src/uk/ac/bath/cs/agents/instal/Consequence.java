package uk.ac.bath.cs.agents.instal;

public abstract class Consequence extends Rule {
	public static int TYPE_INITIATES = 2;
	public static int TYPE_TERMINATES = 3;
	
	public Consequence(Event e, int type, String ... args) {
		super(e, args, Atom.ATOM_CONSEQUENCE, type);
	}
}
