package divy.kmap.runner;
import divy.kmap.solver.TestSolver;
import divy.kmap.solver.TestSolver.*;

import java.util.ArrayList;

/**
 * Created by divy on 11/9/16.
 */
public class Main {
    public static void main(String[] args) {
        TestSolver solver = new TestSolver();
        solver.setVariable_count(3);

        ArrayList<Integer> terms = new ArrayList<>();
        terms.add(3);
        terms.add(5);
        ArrayList<Integer> dcare = new ArrayList<>();
        solver.minimize(terms,dcare);

    }

}
