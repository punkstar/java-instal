package uk.ac.bath.cs.agents.instal.asp;

import java.util.ArrayList;
import java.util.Iterator;

import uk.ac.bath.cs.agents.instal.Event;
import uk.ac.bath.cs.agents.instal.Fluent;
import uk.ac.bath.cs.agents.instal.Generates;
import uk.ac.bath.cs.agents.instal.Initiates;
import uk.ac.bath.cs.agents.instal.Institution;
import uk.ac.bath.cs.agents.instal.Terminates;

public abstract class InstalASPTranslator {
    protected ArrayList<Atom> _program = new ArrayList<Atom>();
    protected Institution _instal;
    
    abstract protected Atom _generateInstitutionName(String name);
    abstract protected Atom[] _generateFluents(Fluent[] fluents);
    abstract protected Atom[] _generateEvents(Event[] events);
    abstract protected Atom[] _generateInitiateRules(Initiates[] rules);
    abstract protected Atom[] _generateTerminateRules(Terminates[] rules);
    abstract protected Atom[] _generateGenerateRules(Generates[] rules);
    
	public InstalASPTranslator(Institution instal_spec) {
	    this._instal = instal_spec;
	}
	
	protected InstalASPTranslator _addComment(String message) {
        this._addItem(new Comment(message)); return this;
    }
	
	protected InstalASPTranslator _addComment() {
	    return this._addComment("");
	}
	
	protected InstalASPTranslator _addDivider() {
	    return this._addComment("% % % % % % % % % % % % % % % % % % % % % % % % % % % % % %");
	}
	
	protected InstalASPTranslator _addItem(Atom a) {
	    this._program.add(a); return this;
	}
	
	public void generate() {
        this._addComment(String.format("Institution: %s", this._instal.getName()));
        this._addItem(this._generateInstitutionName(this._instal.getName()));
        
        this._addDivider();
        this._addComment("Initial fluents..");
        this._addComment(" @TODO");
        
        this._addDivider();
        this._addComment("Events..");
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
