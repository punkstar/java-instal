package uk.ac.bath.cs.agents.instal;

public class PowerFluent extends Fluent {
	public PowerFluent(Fluent f) {
		super(f.getName().toString());
	}
	
	public String asVariablesToString(String[] variables) {
		return String.format("pow(%s)", super.asVariablesToString(variables));
	}
}
