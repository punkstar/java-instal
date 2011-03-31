package uk.ac.bath.cs.agents.instal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Institution implements Serializable {
	//protected RopeBuilder _rope = new RopeBuilder();
	//protected Rope _name;
	protected String _name;
    protected int _timeSteps;
	
    protected ArrayList<Type> _types = new ArrayList<Type>();
    protected ArrayList<Event> _events = new ArrayList<Event>();
    protected ArrayList<Fluent> _fluents = new ArrayList<Fluent>();
    protected ArrayList<Generates> _generates = new ArrayList<Generates>();
    protected ArrayList<Initiates> _initiates = new ArrayList<Initiates>();
    protected ArrayList<Terminates> _terminates = new ArrayList<Terminates>();
    protected ArrayList<InitiallyFluent> _initially = new ArrayList<InitiallyFluent>();
    protected ArrayList<Obligation> _obligations = new ArrayList<Obligation>();
    protected ArrayList<NoninertialFluent> _noninertials = new ArrayList<NoninertialFluent>();
	
	public Institution(String name, int time_steps) {
		//this._name = this._rope.build(name.toCharArray());
		this._name = name;
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
	
	public Institution initially(InitiallyFluent f) {
	    this._initially.add(f); return this;
	}
	
	public Institution obl(Obligation o) {
	    this._obligations.add(o); return this;
	}
	
	public Institution noninertial(NoninertialFluent f) {
	    this._noninertials.add(f); return this;
	}
	
	public String getName() {
	    return this._name.toString();
	}
	
	public Type[] getTypes() {
		return this._types.toArray(new Type[] {});
	}
	
	public Event[] getEvents() {
		return this._events.toArray(new Event[] {});
	}
	
	public CreationEvent[] getCreationEvents() {
	    ArrayList<CreationEvent> list = new ArrayList<CreationEvent>();
	    
	    for(Event e: this.getEvents()) {
	        if (e.getType() == Event.TYPE_CREATION) {
	            list.add((CreationEvent) e);
	        }
	    }
	    
	    return list.toArray(new CreationEvent[] {});
	}
	
   public DissolutionEvent[] getDissolutionEvents() {
        ArrayList<DissolutionEvent> list = new ArrayList<DissolutionEvent>();
        
        for(Event e: this.getEvents()) {
            if (e.getType() == Event.TYPE_DISSOLUTION) {
                list.add((DissolutionEvent) e);
            }
        }
        
        return list.toArray(new DissolutionEvent[] {});
    }
	
	public Fluent[] getFluents() {
		return this._fluents.toArray(new Fluent[] {});
	}
	
	public Generates[] getGenerates() {
		return this._generates.toArray(new Generates[] {});
	}
	
	public Initiates[] getInitiates() {
		return this._initiates.toArray(new Initiates[] {});
	}
	
	public Terminates[] getTerminates() {
		return this._terminates.toArray(new Terminates[] {});
	}
	
	public InitiallyFluent[] getInitiallyFluents() {
	    return this._initially.toArray(new InitiallyFluent[] {});
	}
	
	public Obligation[] getObligations() {
	    return this._obligations.toArray(new Obligation[] {});
	}
	
	public NoninertialFluent[] getNoninertialFluents() {
	    return this._noninertials.toArray(new NoninertialFluent[] {});
	}
	
	public int getTimeSteps() {
	    return this._timeSteps;
	}
	
	public String toString() {
		String output = "";
		
		output += String.format("inst %s;\n\n", this.getName());
		
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
		
        Iterator<InitiallyFluent> iter_init = this._initially.iterator();
        while (iter_init.hasNext()) {
            InitiallyFluent init = iter_init.next();
            output += init.definitionToString();
        }
        
        Iterator<Obligation> iter_obl = this._obligations.iterator();
        while (iter_obl.hasNext()) {
            Obligation obl = iter_obl.next();
            output += obl.toString();
        }
		
		return output;
	}
}
