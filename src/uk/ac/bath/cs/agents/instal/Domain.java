package uk.ac.bath.cs.agents.instal;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Store the domain specific information relating to the instantiation of an institution.
 * 
 * @author Nick Jones <nj210@bath.ac.uk>
 *
 */
public class Domain {
    protected ArrayList<InitiallyFluent> _initially = new ArrayList<InitiallyFluent>();
    protected Hashtable<Type, ArrayList<String>> _concreteTypes = new Hashtable<Type, ArrayList<String>>();
    
    public Domain() {}
    
    /**
     * Add a fluent that will be initiated.
     * 
     * @param f
     * @return
     */
    public Domain initially(InitiallyFluent f) {
        this._initially.add(f); return this;
    }
    
    /**
     * Add a concrete implementation of a type to be used when grounding rules.
     * 
     * @param t
     * @param s
     * @return
     */
    public Domain concreteType(Type t, String s) {
        if (!this._concreteTypes.containsKey(t)) {
            this._concreteTypes.put(t, new ArrayList<String>());
        }
        
        this._concreteTypes.get(t).add(s);
        
        return this;
    }
    
    /**
     * Get an array of the fluents to be initiated at the start.
     * 
     * @return
     */
    public InitiallyFluent[] getInitiallyFluents() {
        return this._initially.toArray(new InitiallyFluent[] {});
    }
    
    /**
     * Get an array of the types we've got concrete implementations of.
     * 
     * @return
     */
    public Type[] getDefinedTypes() {
        return this._concreteTypes.keySet().toArray(new Type[] {});
    }
    
    /**
     * Get the concrete values for a type.
     * 
     * @param t
     * @return
     */
    public String[] getConcretesOf(Type t) {
        if (this._concreteTypes.containsKey(t)) {
            return this._concreteTypes.get(t).toArray(new String[] {});
        }
        
        return null;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        for (InitiallyFluent i: this.getInitiallyFluents()) {
            builder.append("initially ").append(i.toString()).append(";\n");
        }
        
        Iterator<Type> iter = this._concreteTypes.keySet().iterator();
        while(iter.hasNext()) {
            Type t = iter.next();
            
            for (String s: this._concreteTypes.get(t)) {
                builder.append(t.getName())
                       .append("(")
                       .append(s)
                       .append(");")
                       .append("\n");
            }
        }
        
        return builder.toString();
    }
}
