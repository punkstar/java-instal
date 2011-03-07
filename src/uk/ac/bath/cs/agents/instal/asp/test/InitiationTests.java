package uk.ac.bath.cs.agents.instal.asp.test;

import junit.framework.TestCase;
import uk.ac.bath.cs.agents.instal.Domain;
import uk.ac.bath.cs.agents.instal.ExogenousEvent;
import uk.ac.bath.cs.agents.instal.Fluent;
import uk.ac.bath.cs.agents.instal.Initiates;
import uk.ac.bath.cs.agents.instal.Institution;

public class InitiationTests extends TestCase {
    protected ExogenousEvent source = new ExogenousEvent("source_event");
    protected Fluent result = new Fluent("result_fluent");
    
    protected Institution _i;
    protected Domain _d;
    protected AnsPrologTest _asp;
    
    public InitiationTests(String name) {
        super(name);
    }

//    public void testBasic() {
//        String[] rules = this._asp.generateInitiatesRules(this._getBasicRule())
//        assertEquals(
//            String.format("initiated(%s, I) :- occurred(%s, I), holdsat(live(%s), I), instant(I).",
//                this.result.getName(),
//                this.source.getName(),
//                this._i.getName()
//            ),
//        );
//    }
    
//    public void testMultipleResults() {
//        Initiates i = this._getBasicRule();
//        Fluent f = new Fluent("second_result_fluent");
//        i.result(f);
//        
//        assertEquals(
//            String.format("initiated(%s, I) :- occurred(%s, I), holdsat(live(%s), I), instant(I).",
//                this.result.getName(),
//                this.source.getName(),
//                this._i.getName()
//            ),
//            this._asp.generateInitiatesRule(i)
//        );
//    }
    
    protected Initiates _getBasicRule() {
        Initiates i = new Initiates(source);
        i.result(result);
        return i;
    }
    
    public void setUp() {
        this._i = new Institution("test", 1);
        this._d = new Domain();
        this._asp = new AnsPrologTest(_i, _d);
    }
}
