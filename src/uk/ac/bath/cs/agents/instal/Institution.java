package uk.ac.bath.cs.agents.instal;

import java.util.ArrayList;
import java.util.Iterator;

import org.ahmadsoft.ropes.Rope;
import org.ahmadsoft.ropes.RopeBuilder;

public class Institution {
	protected RopeBuilder _rope = new RopeBuilder();
	protected Rope _name;
	protected int _timeSteps;
	
    protected ArrayList<Type> _types = new ArrayList<Type>();
    protected ArrayList<Event> _events = new ArrayList<Event>();
    protected ArrayList<Fluent> _fluents = new ArrayList<Fluent>();
    protected ArrayList<Generates> _generates = new ArrayList<Generates>();
    protected ArrayList<Initiates> _initiates = new ArrayList<Initiates>();
    protected ArrayList<Terminates> _terminates = new ArrayList<Terminates>();
	
	public Institution(String name, int time_steps) {
		this._name = this._rope.build(name.toCharArray());
		this._timeSteps = time_steps;
	}
	
	public Institution type(Type t) {
		this._types.add(t); return this;
	}
	
	public Institution event(Event e) {
		this._events.add(e); return this;
	}
	
	public Institution fluent(Fluent f) {
		this._fluents.add(f); return this;
	}
	
	public Institution generates(Generates g) {
		this._generates.add(g); return this;
	}
	
	public Institution initiates(Initiates i) {
		this._initiates.add(i); return this;
	}
	
	public Institution terminates(Terminates t) {
		this._terminates.add(t); return this;
	}
	
	public Type[] getTypes() {
		return (Type[]) this._types.toArray();
	}
	
	public Event[] getEvents() {
		return (Event[]) this._events.toArray();
	}
	
	public Fluent[] getFluents() {
		return (Fluent[]) this._fluents.toArray();
	}
	
	public Generates[] getGenerates() {
		return (Generates[]) this._generates.toArray();
	}
	
	public Initiates[] getInitiates() {
		return (Initiates[]) this._initiates.toArray();
	}
	
	public Terminates[] getTerminates() {
		return (Terminates[]) this._terminates.toArray();
	}
	
	public String toString() {
		String output = "";
		
		output += String.format("inst %s;\n\n", this._name.toString());
		
		Iterator<Type> iter_t = this._types.iterator();
		while (iter_t.hasNext()) {
			Type t = iter_t.next();
			output += t.definitionToString();
		}
		
		output += String.format("\n");
		
		Iterator<Event> iter_e = this._events.iterator();
		while (iter_e.hasNext()) {
			Event e = iter_e.next();
			output += e.definitionToString();
		}
		
		output += String.format("\n");
		
		Iterator<Fluent> iter_f = this._fluents.iterator();
		while (iter_f.hasNext()) {
			Fluent f = iter_f.next();
			output += f.definitionToString();
		}
		
		output += String.format("\n");
		
		Iterator<Generates> iter_g = this._generates.iterator();
		while (iter_g.hasNext()) {
			Generates g = iter_g.next();
			output += g.toString();
		}
		
		Iterator<Initiates> iter_i = this._initiates.iterator();
		while (iter_i.hasNext()) {
			Initiates i = iter_i.next();
			output += i.toString();
		}
		
		Iterator<Terminates> iter_te = this._terminates.iterator();
		while (iter_te.hasNext()) {
			Terminates t = iter_te.next();
			output += t.toString();
		}
		
		return output;
	}
}
