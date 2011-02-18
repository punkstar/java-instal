package uk.ac.bath.cs.agents.instal;

public class Initiates extends Consequence {
	public Initiates(Event e, String ... params) {
		super(e, Rule.TYPE_INITIATES, params);
	}
	
	public String toString() {
		return String.format(
			"%s initiates %s %s;\n",
			this._getSourceEventWithVariables(),
			this._resultAtomsToString(),
			this._conditionsToString()
		);
	}
}
