package uk.ac.bath.cs.agents.instal.asp;

import java.util.ArrayList;
import java.util.Iterator;

import uk.ac.bath.cs.agents.instal.Event;
import uk.ac.bath.cs.agents.instal.Generates;
import uk.ac.bath.cs.agents.instal.InitiallyFluent;
import uk.ac.bath.cs.agents.instal.Initiates;
import uk.ac.bath.cs.agents.instal.Institution;
import uk.ac.bath.cs.agents.instal.Terminates;

public abstract class InstalASPTranslator {
    protected ArrayList<Atom> _program = new ArrayList<Atom>();
    protected Institution _instal;
    
    abstract protected Atom _generateInstitutionName(String name);
    abstract protected Atom[] _generateInitiallyFluents(InitiallyFluent[] fluents);
    abstract protected Atom[] _generateEvents(Event[] events);
    abstract protected Atom[] _generateInitiateRules(Initiates[] rules);
    abstract protected Atom[] _generateTerminateRules(Terminates[] rules);
    abstract protected Atom[] _generateGenerateRules(Generates[] rules);
    abstract protected Atom[] _generateTimeSteps(int timesteps);
    
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
        this._addItem(new Newline());
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
        for(Atom a: this._generateInitiallyFluents(this._instal.getInitiallyFluents())) {
            this._addItem(a);
        }
        
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
        
        this._addDivider();
        this._addComment("Timesteps..");
        for (Atom a: this._generateTimeSteps(this._instal.getTimeSteps())) {
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
