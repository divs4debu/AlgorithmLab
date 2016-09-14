package divy.kmap.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by divy on 10/9/16.
 */
public class TestSolver {
    private ArrayList<Boolean> kmap;
    private ArrayList<ArrayList<Integer> > terms = new ArrayList<>();
    private int variable_count;
    private int resultTerms;
    private ArrayList<Character> minimized;

    public TestSolver(ArrayList<Boolean>  map){
        this.kmap = map;
    }
    public TestSolver(){}

    public void setVariable_count(int count){
        variable_count = count;
    }
    public void setTerms(ArrayList<ArrayList<Integer>> list){
        this.terms = list;
    }

    /**
     * This function set the coordinates of  1's and dont care's
     * for example in a 3 variable karnaugh map we need to set coordinate of 1,3,5,7th min term
     * then: coordinate set will be - [ [0,0,1], [0,1,1], [1,0,1], [1,1,1]]
     * @param position  : arraylist of integers where either dontcare or 1's are present
     */
    private void setCoordinate(ArrayList<Integer> position){
        //this.terms.add(new ArrayList<Integer>());
        //System.out.print(terms.size());
        for (int i = 0; i< position.size(); i++){
            this.terms.add(new ArrayList<Integer>());
            for (int j = 0; j< this.variable_count; j++){
                this.terms.get(terms.size()-1).add(0, position.get(i) % 2);
                position.set(i, position.get(i)/2);
            }
        }
    }

    public void minimize(ArrayList<Integer> one, ArrayList<Integer> dcare){
        setCoordinate(one);
        //setCoordinate(dcare);
        compare();
        unRepeat();
        printArray(terms);

    }

    private void compare(){
        ArrayList<Integer> tempSave = new ArrayList<>();
        ArrayList<ArrayList<Integer>> saver = new ArrayList<>();
        //List<Boolean> compared = new ArrayList<>(Collections.nCopies(42, false));
        int k,j;

        for(int i = 0; i<variable_count && terms.size() >1; i++){
            List<Boolean> compared = new ArrayList<>(Collections.nCopies(42, false));

            for(j = 0; j< terms.size()-1; j++){
                for(k = j+1; k<terms.size(); k++){
                    tempSave.clear();
                    for(int l =0; l< variable_count; l++){
                        if(terms.get(j).get(l).equals(terms.get(k).get(l)))
                            tempSave.add(l);
                    }
                    if(tempSave.size() == variable_count-1)
                        saveValue(tempSave, saver, compared,j, k);
                    printArray(saver);
                }
            }
            addOther(saver, compared);
            if(saver.size()>0) {
                terms.clear();
                terms = new ArrayList<>(saver);
            }
            saver.clear();
        }

    }


    private void saveValue(ArrayList<Integer> tempSave, ArrayList<ArrayList <Integer>> saver, List<Boolean> compared, int index, int index2){

        if(tempSave.size() == variable_count-1){
            saver.add(new ArrayList<Integer>());
            for(int i = 0; i< terms.get(index).size();i++){
                if(i==tempSave.size())
                    tempSave.add(-1);
                else if(i != tempSave.get(i))
                    tempSave.add(i, -1);
            }

            for(int i = 0; i < tempSave.size(); i++){
                if(tempSave.get(i) == -1)
                    saver.get(saver.size()-1).add(-1);
                else
                    saver.get(saver.size()-1).add(terms.get(index).get(i));
            }
            compared.set(index,new Boolean(true));
            compared.set(index2, new Boolean(true));

        }
    }

    private void addOther(ArrayList<ArrayList<Integer> >saver, List<Boolean> compared){
        for(int i=0 ; i<terms.size();i++){
            if(compared.size()>0 && !compared.get(i)){
                saver.add(new ArrayList<Integer>());
                for(int j =0; j<terms.get(i).size(); j++)
                    saver.get(saver.size()-1).add(terms.get(i).get(j));
            }
        }
    }

    private void unRepeat(){
        for(int i = 0; i< terms.size(); i++){
            for (int j = i+1; j<terms.size(); j++){
                if(terms.get(i).equals(terms.get(j))) {
                    terms.remove(j);
                    j--;

                }
            }
        }


    }

    private void printArray(ArrayList<ArrayList<Integer>> x){
        for(ArrayList<Integer> y : x){
            for(Integer z: y){
                System.out.print(z + " ");
            }
            System.out.print("|");
        }
        System.out.println();
    }

    public void setKmap(ArrayList<Boolean> map){
        this.kmap = map;
    }

    public void resolve(){
        ArrayList<Integer> ones = new ArrayList<>();
        ArrayList<Integer> dcares = new ArrayList<>();
        for (int i = 0; i< kmap.size();i++){

            if(kmap.get(i) == (null))
                dcares.add(i);
            else if(kmap.get(i).equals(Boolean.TRUE))
                ones.add(i);
        }

        minimize(ones, dcares);
    }

}
