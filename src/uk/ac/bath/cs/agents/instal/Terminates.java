package uk.ac.bath.cs.agents.instal;

public class Terminates extends Consequence {
	public Terminates(Event e, String ... params) {
		super(e, Consequence.TYPE_TERMINATES, params);
	}
	
	public String toString() {
		return String.format(
			"%s\n\tterminates\n\t\t%s\n\t\t%s;\n",
			this._getSourceEventWithVariables(),
			this._resultAtomsToString(),
			this._conditionsToString()
		);
	}
}
