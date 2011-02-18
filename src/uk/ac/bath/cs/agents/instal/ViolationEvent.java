package uk.ac.bath.cs.agents.instal;

public class ViolationEvent extends Event {
	// Whether or not this event was created from another event, or if it is an event in it's own right
	private boolean __fromEvent = false;
	
	public ViolationEvent(String name) {
		super(name, Event.TYPE_VIOLATION);
	}
	
	public ViolationEvent(Event e) {
		this(e.getName().toString());
		this.__fromEvent = true;
	}
	
	public String asVariablesToString(String[] variables) {
		if (this.__fromEvent) {
			return String.format("viol(%s)", super.asVariablesToString(variables));
		} else {
			return super.asVariablesToString(variables);
		}
	}
}
