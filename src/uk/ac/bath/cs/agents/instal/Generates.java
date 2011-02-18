package uk.ac.bath.cs.agents.instal;


public class Generates extends Rule {
	public Generates(Event e) {
		this(e, new String [] {});
	}
	
	public Generates(Event e, String ... args) {
		super(e, args, Atom.ATOM_GENERATES, Rule.TYPE_GENERATES);
	}
	
	public String toString() {
		return String.format(
			"%s generates %s %s;\n",
			this._getSourceEventWithVariables(),
			this._resultAtomsToString(),
			this._conditionsToString()
		);
	}
}