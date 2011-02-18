package uk.ac.bath.cs.agents.instal;

public abstract class Consequence extends Rule {	
	public Consequence(Event e, int type, String ... args) {
		super(e, args, Atom.ATOM_CONSEQUENCE, type);
	}
}
