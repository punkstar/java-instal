package uk.ac.bath.cs.agents.instal;


public class Generates extends Rule {
	public static int TYPE_PLAIN = 2;
	
	public Generates(Event e) {
		this(e, new String [] {});
	}
	
	public Generates(Event e, String ... args) {
		super(e, args, Atom.ATOM_GENERATES, Generates.TYPE_PLAIN);
	}
	
	public String toString() {
		return String.format(
			"%s\n\tgenerates\n\t\t%s\n\t\t%s;\n",
			this._getSourceEventWithVariables(),
			this._resultAtomsToString(),
			this._conditionsToString()
		);
	}
}