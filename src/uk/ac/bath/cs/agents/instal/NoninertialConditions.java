package uk.ac.bath.cs.agents.instal;

class NoninertialConditions extends Conditional {
    protected NoninertialConditions(NoninertialFluent f) {
        super(f.getName(), Atom.ATOM_NONINERTIAL, NoninertialFluent.NONINERT_CONDITIONS);
    }
}
