package uk.ac.bath.cs.agents.instal.asp.test;

import java.util.Hashtable;
import java.util.Iterator;

import junit.framework.TestCase;
import uk.ac.bath.cs.agents.instal.Domain;
import uk.ac.bath.cs.agents.instal.Event;
import uk.ac.bath.cs.agents.instal.Initiates;
import uk.ac.bath.cs.agents.instal.Institution;
import uk.ac.bath.cs.agents.instal.NormativeEvent;
import uk.ac.bath.cs.agents.instal.Type;

public class BasicTests extends TestCase {
    protected AnsPrologTest _asp;
    
    public void testDeclareInstitution() {
        String name = "test_inst";
        assertEquals(String.format("inst(%s).", name), this._asp.generateInstitutionName(name));
    }
    
    public void testConcreteTypes() {
        Hashtable<Type, String[]> type_map = new Hashtable<Type, String[]>();
        
        type_map.put(new Type("Handset"), new String[] {
            "handset_1", "handset_2", "handset_3"
        });

        Iterator<Type> iter = type_map.keySet().iterator();
        while (iter.hasNext()) {
            Type t = iter.next();
            
            for (int i = 0; i < type_map.get(t).length; i++) {
                String s = type_map.get(t)[i];
                assertEquals(String.format("%s(%s).", t.toString().toLowerCase(), s), this._asp.generateConcreteType(t, s));
            }
        }
    }
    
    public void testInitiationRules() {
        Event e1 = new NormativeEvent("test");
        Initiates i1 = new Initiates(e1);
        
        assertEquals(String.format(""), this._asp.generateInitiatesRule(i1));
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        this._asp = new AnsPrologTest(this.__getInstitution(), this.__getDomain());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }    
    
    private Institution __getInstitution() {
        Institution i = new Institution("test", 1);
        return i;
    }
    
    private Domain __getDomain() {
        Domain d = new Domain();
        
        return d;
    }
}
