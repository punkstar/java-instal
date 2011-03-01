package uk.ac.bath.cs.agents.instal;

import java.util.ArrayList;

import org.ahmadsoft.ropes.Rope;

public class Type extends Atom {
	//protected ArrayList<Rope> _instances = new ArrayList<Rope>();
	protected ArrayList<String> _instances = new ArrayList<String>();
    
	public Type(String name) {
		super(name, Atom.ATOM_TYPE, 1);
	}
	
	public Type addInstance(String name) {
		//this._instances.add(this._rope.build(name.toCharArray())); return this;
	    this._instances.add(name); return this;
	}
	
	public String definitionToString() {
		return String.format("Type %s;\n", this.toString());
	}
	
	public String toString() {
		return this.getName().toString();
	}
}
