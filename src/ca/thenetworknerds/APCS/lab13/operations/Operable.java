package ca.thenetworknerds.APCS.lab13.operations;

import ca.thenetworknerds.APCS.lab13.vectorables.Vectorable;

public interface Operable extends Vectorable {
    String getNature(); // Returns nature of the operable, example, "Number", "Concatenatable", "Multi-Number Container"
}
