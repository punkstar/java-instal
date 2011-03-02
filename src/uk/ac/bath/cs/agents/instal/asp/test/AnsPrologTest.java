package uk.ac.bath.cs.agents.instal.asp.test;

import uk.ac.bath.cs.agents.instal.Domain;
import uk.ac.bath.cs.agents.instal.Initiates;
import uk.ac.bath.cs.agents.instal.Institution;
import uk.ac.bath.cs.agents.instal.Type;
import uk.ac.bath.cs.agents.instal.asp.AnsProlog;

public class AnsPrologTest extends AnsProlog {
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

    public String generateInitiatesRule(Initiates i) {
        return this._generateInitiateRules(new Initiates[] {i})[1].toString();
    }
}
