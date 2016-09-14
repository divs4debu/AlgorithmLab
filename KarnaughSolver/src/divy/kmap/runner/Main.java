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
        terms.add(0);
        terms.add(2);
        terms.add(3);
        terms.add(5);
        terms.add(6);
        terms.add(7);
        //terms.add(1);

        ArrayList<Integer> dcare = new ArrayList<>();
        /*dcare.add(0);
        dcare.add(1);
        dcare.add(2);
        dcare.add(4);
        dcare.add(6);
        dcare.add(7);

        /*
        kmap -  ---------------------------------
                |   0   |   1   |   x   |   0   |
                _________________________________
                |   1   |   1   |   0   |   x   |
                _________________________________

        Boolean b;
        ArrayList<Boolean> kmap = new ArrayList<>();
        kmap.add(new Boolean(false));
        kmap.add(new Boolean(true));
        kmap.add(new Boolean(false));
        kmap.add(null);
        kmap.add(new Boolean(true));
        kmap.add(new Boolean(true));
        kmap.add(null);
        kmap.add(new Boolean(false));
        solver.setKmap(kmap);
        solver.resolve();*/

        solver.minimize(terms,dcare);

    }

}
