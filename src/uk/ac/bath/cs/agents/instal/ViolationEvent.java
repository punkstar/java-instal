package uk.ac.bath.cs.agents.instal;

public class ViolationEvent extends Event {
	public ViolationEvent(String name) {
		super(name, Event.TYPE_VIOLATION);
	}
}
