package uk.ac.bath.cs.agents.instal;

public class AlwaysWhen extends Rule {
    
    protected NoninertialFluent _nf;
    protected String[] args;
    
    public AlwaysWhen(NoninertialFluent nf, String[] args) {
        super(null, args, 0, 0);
    }
    
    /**
     * We hi-jack this to use our noninertial fluent instead of a variable.
     */
    public String _getSourceEventWithVariables() {
        return this._nf.asVariablesToString(this.args);
    }
}
