package uk.ac.bath.cs.agents.instal.asp;

public class Comment extends Atom {
    protected String _message;
    
    public Comment(String message) {
        super();
        this._message = message;
    }
    
    public String toString() {
        return String.format("%% %% %s", this._message.replaceAll("\n", ""));
    }
}
