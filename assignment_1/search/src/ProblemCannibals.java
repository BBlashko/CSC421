import java.util.HashSet;
import java.util.Set;

public class ProblemCannibals extends Problem {

    static final int cannL = 0;
    static final int missL = 1;
    static final int boatL = 2;
    static final int cannR = 3;
    static final int missR = 4;
    static final int boatR = 5;
    
    boolean goal_test(Object state) {
        StateCannibals can_state = (StateCannibals) state;
        
        if (can_state.canArray[cannR]==3 && can_state.canArray[missR]==3 && can_state.canArray[boatR]==1)
            return true;
        else return false;
    }
  
    Set<Object> getSuccessors(Object state) {

        Set<Object> set = new HashSet<Object>();
        StateCannibals can_state = (StateCannibals) state;
        
        //Let's create without any constraint, then remove the illegal ones
        StateCannibals successor_state;
        
        //one cannibal only from left to right
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] -= 1;
        successor_state.canArray[cannR] += 1;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //one cannibal only from right to left
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] += 1;
        successor_state.canArray[cannR] -= 1;
        successor_state.canArray[boatL] += 1;
        successor_state.canArray[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        //two cannibals from left to right
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] -= 2;
        successor_state.canArray[cannR] += 2;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //two cannibals from right to left 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] += 2;
        successor_state.canArray[cannR] -= 2;
        successor_state.canArray[boatL] += 1;
        successor_state.canArray[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);

        //one missionary only from left to right 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[missL] -= 1;
        successor_state.canArray[missR] += 1;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        //one missionary only from right to left 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[missL] += 1;
        successor_state.canArray[missR] -= 1;
        successor_state.canArray[boatL] += 1;
        successor_state.canArray[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);

        //two missionaries from left to right 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[missL] -= 2;
        successor_state.canArray[missR] += 2;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //two missionaries from right to left 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[missL] += 2;
        successor_state.canArray[missR] -= 2;
        successor_state.canArray[boatL] += 1;
        successor_state.canArray[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        //one cannibal and one missionary from left to right 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] -= 1;
        successor_state.canArray[cannR] += 1;
        successor_state.canArray[missL] -= 1;
        successor_state.canArray[missR] += 1;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);
        
        //one cannibal and one missionary from right to left 
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] += 1;
        successor_state.canArray[cannR] -= 1;
        successor_state.canArray[missL] += 1;
        successor_state.canArray[missR] -= 1;
        successor_state.canArray[boatL] += 1;
        successor_state.canArray[boatR] -= 1;
        if (isValid(successor_state)) set.add(successor_state);

        return set;
    }
    
    private boolean isValid(StateCannibals state)
    {   
        //Checking to see if any element of the array is negative 
        for (int i=0; i<6; i++)
            if (state.canArray[i] < 0) return false;
        
        //Checking to see if the numbers of cannibals, missionaries, and boat 
        //are more then 3,3,1 respectively

        // check for exactly 3 cannibals
        if (state.canArray[cannL] + state.canArray[cannR] != 3) return false;

        // check for exactly 3 missionaries
        if (state.canArray[missL] + state.canArray[missR] != 3) return false;

        // check for exactly 1 boat position
        if (state.canArray[boatL] + state.canArray[boatR] != 1) return false;

        //Now, checking if cannibals out number missionaries
        if (state.canArray[cannL] > state.canArray[missL]
                && state.canArray[missL] != 0) return false;
        if (state.canArray[cannR] > state.canArray[missR]
                && state.canArray[missR] != 0) return false;

        return true;
    }

    double step_cost(Object fromState, Object toState) { return 1; }

    public double h(Object state) {
        StateCannibals canState = (StateCannibals) state;
        return canState.canArray[cannL] + canState.canArray[missL] - 1;
    }

    public static void main(String[] args) throws Exception {
        ProblemCannibals problem = new ProblemCannibals();
        int[] canArray = {3,3,1,0,0,0};
        problem.initialState = new StateCannibals(canArray);

        Search search  = new Search(problem);

        //Depth First Limit
        int limit = 11;

        //Tree Search
        System.out.println("TreeSearch----------------------------------");
        System.out.println("BreadthFirstTreeSearch:\t\t\t" + search.BreadthFirstTreeSearch());
        System.out.println("UniformCostTreeSearch:\t\t\t" + search.UniformCostTreeSearch());
        System.out.println("DepthFirstTreeSearch:\t\t\t" + search.DepthFirstTreeSearch());
        System.out.println("GreedyBestFirstTreeSearch:\t\t" + search.GreedyBestFirstTreeSearch());
        System.out.println("DepthLimitedTreeSearch:\t\t\t" + search.DepthLimitedGraphSearch(limit));
        System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch() + "\n");

        //Graph Search
        System.out.println("GraphSearch----------------------------------");
        System.out.println("BreadthFirstGraphSearch:\t\t" + search.BreadthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t\t\t" + search.UniformCostGraphSearch());
        System.out.println("DepthFirstGraphSearch:\t\t\t" + search.DepthFirstGraphSearch());
        System.out.println("GreedyBestFirstGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
        System.out.println("DepthLimitedGraphSearch:\t\t" + search.DepthLimitedGraphSearch(limit));
        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
    }
}
