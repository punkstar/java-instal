package uk.ac.bath.cs.agents.instal.asp.test;

import uk.ac.bath.cs.agents.instal.Domain;
import uk.ac.bath.cs.agents.instal.Initiates;
import uk.ac.bath.cs.agents.instal.Institution;
import uk.ac.bath.cs.agents.instal.Type;
import uk.ac.bath.cs.agents.instal.asp.AnsProlog;
import uk.ac.bath.cs.agents.instal.asp.Atom;

public class AnsPrologTest extends AnsProlog {
    protected AnsPrologTest _asp;
    
    public AnsPrologTest(Institution instalSpec, Domain domain) {
        super(instalSpec, domain);
    }
    
    public String generateInstitutionName(String name) {
        return this._generateInstitutionName(name).toString();
    }
    
    public String generateConcreteType(Type t, String s) {
        Domain d = new Domain();
        d.concreteType(t, s);
        
        return this._generateConcreteTypes(d)[0].toString();
    }

    public String[] generateInitiatesRules(Initiates i) {
        Atom[] atoms = this._generateInitiateRules(new Initiates[] {i});
        String[] result = new String[atoms.length];
        
        int counter = 0;
        for (Atom a : atoms) {
            result[counter++] = a.toString();
        }
        
        return result;
    }
}
