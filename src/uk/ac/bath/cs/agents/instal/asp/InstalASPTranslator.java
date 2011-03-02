package uk.ac.bath.cs.agents.instal.asp;

import java.util.ArrayList;
import java.util.Iterator;

import uk.ac.bath.cs.agents.instal.CreationEvent;
import uk.ac.bath.cs.agents.instal.Domain;
import uk.ac.bath.cs.agents.instal.Event;
import uk.ac.bath.cs.agents.instal.Generates;
import uk.ac.bath.cs.agents.instal.InitiallyFluent;
import uk.ac.bath.cs.agents.instal.Initiates;
import uk.ac.bath.cs.agents.instal.Institution;
import uk.ac.bath.cs.agents.instal.Terminates;

public abstract class InstalASPTranslator {
    protected ArrayList<Atom> _program = new ArrayList<Atom>();
    protected Institution _instal;
    protected Domain _domain;
    
    abstract protected Atom _generateInstitutionName(String name);
    abstract protected Atom[] _generateInitiallyFluents(InitiallyFluent[] fluents);
    abstract protected Atom[] _generateEvents(Event[] events);
    abstract protected Atom[] _generateInitiateRules(Initiates[] rules);
    abstract protected Atom[] _generateTerminateRules(Terminates[] rules);
    abstract protected Atom[] _generateGenerateRules(Generates[] rules);
    abstract protected Atom[] _generateTimeSteps(int timesteps);
    abstract protected Atom[] _generateConcreteTypes(Domain d);
    abstract protected Atom[] _generateCreateEventRules(CreationEvent[] events, InitiallyFluent[] fluents);
    
	public InstalASPTranslator(Institution instal_spec, Domain domain) {
	    this._instal = instal_spec;
	    this._domain = domain;
	}
	
	protected InstalASPTranslator _addComment(String message) {
        this._addItem(new Comment(message)); return this;
    }
	
	protected InstalASPTranslator _addComment() {
	    return this._addComment("");
	}
	
	protected InstalASPTranslator _addDivider() {
	    String divider = "% % % % % % % % % % % % % % % % % % % % % % % % % % % % % %";
        this._addItem(new Newline());
        this._addComment(divider);
        this._addComment(divider);
        return this;
	}
	
	protected InstalASPTranslator _addItem(Atom a) {
	    this._program.add(a); return this;
	}
	
	public InstalASPTranslator generate() {
        this._addComment(String.format("Institution: %s", this._instal.getName()));
        this._addItem(this._generateInstitutionName(this._instal.getName()));
        
        this._addDivider();
        this._addComment("Concreting types..");
        this._addComment();
        for(Atom a: this._generateConcreteTypes(this._domain)) {
            this._addItem(a);
        }
        
        this._addDivider();
        this._addComment("Initial insitution fluents..");
        this._addComment();
        for(Atom a: this._generateInitiallyFluents(this._instal.getInitiallyFluents())) {
            this._addItem(a);
        }

        this._addDivider();
        this._addComment("Initial domain fluents..");
        this._addComment();
        for(Atom a: this._generateInitiallyFluents(this._domain.getInitiallyFluents())) {
            this._addItem(a);
        }
        
        this._addDivider();
        this._addComment("Events..");
        this._addComment();
        for(Atom a: this._generateEvents(this._instal.getEvents())) {
            this._addItem(a);
        }
        
        this._addDivider();
        this._addComment("Initiation rules..");
        for (Atom a: this._generateInitiateRules(this._instal.getInitiates())) {
            this._addItem(a);
        }

        this._addDivider();
        this._addComment("Termination rules..");
        for (Atom a: this._generateTerminateRules(this._instal.getTerminates())) {
            this._addItem(a);
        }

        
        this._addDivider();
        this._addComment("Generation rules..");
        for (Atom a: this._generateGenerateRules(this._instal.getGenerates())) {
            this._addItem(a);
        }
        
        this._addDivider();
        this._addComment("Creation rules..");
        for (Atom a: this._generateCreateEventRules(this._instal.getCreationEvents(), this._domain.getInitiallyFluents())) {
            this._addItem(a);
        }
        
        this._addDivider();
        this._addComment("Timesteps..");
        this._addComment();
        for (Atom a: this._generateTimeSteps(this._instal.getTimeSteps())) {
            this._addItem(a);
        }
        
        return this;
	}
	
	public String toString() {
	    StringBuilder builder = new StringBuilder();
	    Iterator<Atom> iter = this._program.iterator();
	    while (iter.hasNext()) {
	        Atom a = iter.next();
	        builder.append(a.toString());
	        builder.append("\n");
	    }
	    
	    return builder.toString();
	}
}
