package uk.ac.bath.cs.agents.instal;

public class PermissionFluent extends Fluent {
	public PermissionFluent(Fluent f) {
		super(f, Fluent.TYPE_PERMISSION);
	}
	
	public String asVariablesToString(String[] variables) {
		return String.format("perm(%s)", super.asVariablesToString(variables));
	}
}
