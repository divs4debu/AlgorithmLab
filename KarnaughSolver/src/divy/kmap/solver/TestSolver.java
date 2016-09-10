package divy.kmap.solver;

import java.util.ArrayList;

/**
 * Created by divy on 10/9/16.
 */
public class TestSolver {
    private ArrayList<ArrayList<Boolean> > kmap;
    private ArrayList<ArrayList<Integer> > terms;
    private int variable_count;
    int resultTerms;

    public TestSolver(ArrayList<ArrayList<Boolean> > map){
        this.kmap = map;
    }

    /**
     * This function set the coordinates of  1's and dont care's
     * for example in a 3 variable karnaugh map we need to set coordinate of 1,3,5,7th min term
     * then: coordinate set will be - [ [0,0,1], [0,1,1], [1,0,1], [1,1,1]]
     * @param position  : arraylist of integers where either dontcare or 1's are present
     */
    public void setTerms(ArrayList<Integer> position){
        for (int i = 0; i< position.size(); i++){
            this.terms.add(new ArrayList<Integer>());
            for (int j = 0; j< this.variable_count; j++){
                this.terms.get(terms.size()).add(0, position.get(i) % 2);
                position.set(i, position.get(i)/2);
            }
        }
    }
}
