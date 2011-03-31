package uk.ac.bath.cs.agents.instal;

import java.io.Serializable;

public abstract class Atom implements Serializable {
    public static final int ATOM_EVENT = 2;
	public static final int ATOM_FLUENT = 3;
	public static final int ATOM_GENERATES = 4;
	public static final int ATOM_CONSEQUENCE = 5;
	public static final int ATOM_TYPE = 6;
	public static final int ATOM_OBLIGATION = 7;
	public static final int ATOM_NONINERTIAL = 8;
	
	protected int _atom;
	protected int _type;
	protected String _name;
	
	protected Atom(String name, int atom, int type) {
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
	
	public boolean isEvent() {
	    return this._atom == Atom.ATOM_EVENT;
	}
}
