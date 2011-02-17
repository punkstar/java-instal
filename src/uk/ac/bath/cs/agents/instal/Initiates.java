package uk.ac.bath.cs.agents.instal;

public class Initiates extends Consequence {
	public Initiates(Event e, String ... params) {
		super(e, Consequence.TYPE_INITIATES, params);
	}
	
	public String toString() {
		return String.format(
			"%s\n\tinitiates\n\t\t%s\n\t\t%s;\n",
			this._getSourceEventWithVariables(),
			this._resultAtomsToString(),
			this._conditionsToString()
		);
	}
}
