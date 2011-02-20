package uk.ac.bath.cs.agents.instal.asp;

public class Blank extends Atom {
    String _line;
    
    public Blank(String line) {
        this._line = line;
    }
    
    public String toString() {
        return this._line;
    }
}
