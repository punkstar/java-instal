package uk.ac.bath.cs.agents.instal;

import org.ahmadsoft.ropes.Rope;
import org.ahmadsoft.ropes.RopeBuilder;

public abstract class Atom {
	public static int ATOM_EVENT = 2;
	public static int ATOM_FLUENT = 3;
	public static int ATOM_GENERATES = 4;
	public static int ATOM_CONSEQUENCE = 5;
	public static int ATOM_TYPE = 6;
	public static int ATOM_OBLIGATION = 7;
	
	//protected RopeBuilder _rope = new RopeBuilder();
	protected int _atom;
	protected int _type;
	//protected Rope _name;
	protected String _name;
	
	protected Atom(String name, int atom, int type) {
		//this._name = this._rope.build(name.toCharArray());
		this._name = name;
	    this._atom = atom;
		this._type = type;
	}
	
	public String getName() {
		return this._name;
	}
	
	public int getType() {
	    return this._type;
	}
}
