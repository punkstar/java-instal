package uk.ac.bath.cs.agents.instal.asp;

import java.util.ArrayList;

import uk.ac.bath.cs.agents.instal.Event;
import uk.ac.bath.cs.agents.instal.Fluent;
import uk.ac.bath.cs.agents.instal.Generates;
import uk.ac.bath.cs.agents.instal.InitiallyFluent;
import uk.ac.bath.cs.agents.instal.Initiates;
import uk.ac.bath.cs.agents.instal.Institution;
import uk.ac.bath.cs.agents.instal.Rule;
import uk.ac.bath.cs.agents.instal.Terminates;

public class AnsProlog extends InstalASPTranslator {

	public AnsProlog(Institution instalSpec) {
		super(instalSpec);
	}
	
	public void generate() {
	    this._addDivider();
        this._addComment("Engine: AnsProlog, Nick Jones <nj210@bath.ac.uk>");
        this._addDivider();
        super.generate();
	}
	
	protected Atom _generateInstitutionName(String name) {
	    return new Blank(String.format("inst(%s).", name));
	}
	
	protected Atom[] _generateInitiallyFluents(Fluent[] fluents) {
	    Atom[] atoms = new Atom[fluents.length];
	    
	    for (int i = 0; i < fluents.length; i++) {
	        atoms[i] = new Blank(String.format("ifluent(%s).", fluents[i].toString()));
	    }
	    
	    return atoms;
	}
	
	/**
	 * @FIXME We shouldn't have an extra event description for events that are already violations.
	 * @TODO Check that creation events should actually be listed here.
	 */
	protected Atom[] _generateEvents(Event[] events) {
	    int atoms_per_event = 7;
	    Atom[] atoms = new Atom[events.length*atoms_per_event];
        
        for (int i = 0; i < events.length; i++) {
            Event e = events[i];
            
            atoms[i*atoms_per_event] = new Comment(String.format("Event: %s (Type: %s)", e.getName().toString(), this.__eventTypeAbbr(e.getType())));
            
            atoms[i*atoms_per_event+1] = new Blank(String.format("event(%s).", e.getName().toString()));
            atoms[i*atoms_per_event+2] = new Blank(String.format("evtype(%s, %s).", e.getName().toString(), this.__eventTypeAbbr(e.getType())));
            // @FIXME This is evil and breaks encapsulation
            atoms[i*atoms_per_event+3] = new Blank(String.format("evinst(%s, %s).", e.getName().toString(), this._instal.getName()));
            
            atoms[i*atoms_per_event+4] = new Blank(String.format("event(viol(%s)).", e.getName().toString()));
            atoms[i*atoms_per_event+5] = new Blank(String.format("evtype(viol(%s), %s).", e.getName().toString(), this.__eventTypeAbbr(Event.TYPE_VIOLATION)));
            // @FIXME This is evil and breaks encapsulation
            atoms[i*atoms_per_event+6] = new Blank(String.format("evinst(viol(%s), %s).", e.getName().toString(), this._instal.getName()));
        }
    
        return atoms;
	}
	
	private String __eventTypeAbbr(int type) {
	    switch (type) {
	        case Event.TYPE_CREATION:
	            return "create";
	        case Event.TYPE_EXOGENEOUS:
	            return "ex";
	        case Event.TYPE_NORMATIVE:
	            return "inst";
	        case Event.TYPE_VIOLATION:
	            return "viol";
	        default:
	            return "";
	    }
	}

    @Override
    protected Atom[] _generateInitiateRules(Initiates[] rules) {
        ArrayList<Atom> atoms = new ArrayList<Atom>();
        
        for (Rule r: rules) {
            atoms.add(new Comment(""));
            atoms.add(new Comment(String.format("Translation of: %s", r.toString())));
            
            StringBuilder conditions = new StringBuilder();
            
            for (int i = 0; i < r.getConditionsWithVariables().length; i++) {
                conditions.append("holdsat(").append(r.getConditionsWithVariables()[i]).append(", I), ");
            }
            
            for (String s: r.getResultAtomsWithVariables()) {
                atoms.add(
                    new Blank(String.format(
                        "initiated(%s, I) :- occured(%s, I), %sholdsat(live(%s), I), instant(I).",
                        s,
                        r._getSourceEventWithVariables(),
                        (conditions.toString().length() > 0) ? conditions : "",
                        this._instal.getName()
                    ))
                );
            }
        }
        
        return atoms.toArray(new Atom[] {});
    }

    @Override
    protected Atom[] _generateTerminateRules(Terminates[] rules) {
        ArrayList<Atom> atoms = new ArrayList<Atom>();
        
        for (Rule r: rules) {
            atoms.add(new Comment(""));
            atoms.add(new Comment(String.format("Translation of: %s", r.toString())));
            
            StringBuilder conditions = new StringBuilder();
            
            for (int i = 0; i < r.getConditionsWithVariables().length; i++) {
                conditions.append("holdsat(").append(r.getConditionsWithVariables()[i]).append(", I), ");
            }
            
            for (String s: r.getResultAtomsWithVariables()) {
                atoms.add(
                    new Blank(String.format(
                        "terminated(%s, I) :- occured(%s, I), %sholdsat(live(%s), I), instant(I).",
                        s,
                        r._getSourceEventWithVariables(),
                        (conditions.toString().length() > 0) ? conditions : "",
                        this._instal.getName()
                    ))
                );
            }
        }
        
        return atoms.toArray(new Atom[] {});
    }
    
    protected Atom[] _generateGenerateRules(Generates[] rules) {
        ArrayList<Atom> atoms = new ArrayList<Atom>();
        
        for (Rule r: rules) {
            atoms.add(new Comment(""));
            atoms.add(new Comment(String.format("Translation of: %s", r.toString())));
            
            StringBuilder conditions = new StringBuilder();
            
            for (int i = 0; i < r.getConditionsWithVariables().length; i++) {
                conditions.append("holdsat(").append(r.getConditionsWithVariables()[i]).append(", I), ");
            }
            
            for (String s: r.getResultAtomsWithVariables()) {
                atoms.add(
                    new Blank(String.format(
                        "occured(%s, I) :- occured(%s, I), %sholdsat(pow(%s, %s), I), instant(I).",
                        s,
                        r._getSourceEventWithVariables(),
                        (conditions.toString().length() > 0) ? conditions : "",
                        this._instal.getName(),
                        s
                    ))
                );
            }
        }
        
        return atoms.toArray(new Atom[] {});
    }
    
    protected Atom[] _generateInitiallyFluents(InitiallyFluent[] inits) {
        ArrayList<Atom> atoms = new ArrayList<Atom>(inits.length);
        
        for (InitiallyFluent i: inits) {
            atoms.add(new Blank(String.format("ifluent(%s%s).", i.getName().toString(), i.getVariablesWithParenthesisToString(i.getParameterVariables()))));
        }
        
        return atoms.toArray(new Atom[] {});
    }
}
