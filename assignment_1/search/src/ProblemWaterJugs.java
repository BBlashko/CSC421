import java.util.HashSet;
import java.util.Map;
import java.util.Collections;
import java.util.Set;

public class ProblemWaterJugs extends Problem {

    static final int cap12 = 0;
    static final int cap8 = 1;
    static final int cap3 = 2;

    // mapping between capacity enum and integer capacity
    static final int[] capMap = {12, 8, 3};

    boolean goal_test(Object state) {
        StateWaterJugs jugState = (StateWaterJugs) state;

        if (jugState.jugArray[cap12] == 1
                || jugState.jugArray[cap8] == 1
                || jugState.jugArray[cap3] == 1) {
            return true;
        }
        return false;
    }

    Set<Object> getSuccessors(Object state) {

        Set<Object> set = new HashSet<Object>();
        StateWaterJugs jugState = (StateWaterJugs) state;

        StateWaterJugs successorState;

        // Note: filling and emptying the jugs will always be valid regardless of their previous volume
        // fill capacity 12 jug
        successorState = new StateWaterJugs(jugState);
        successorState.jugArray[cap12] = capMap[cap12];
        if (!successorState.equals(jugState)) set.add(successorState);

        // fill capacity 8 jug
        successorState = new StateWaterJugs(jugState);
        successorState.jugArray[cap8] = capMap[cap8];
        if (!successorState.equals(jugState)) set.add(successorState);

        // fill capacity 3 jug
        successorState = new StateWaterJugs(jugState);
        successorState.jugArray[cap3] = capMap[cap3];
        if (!successorState.equals(jugState)) set.add(successorState);

        // empty capacity 12 jug
        successorState = new StateWaterJugs(jugState);
        successorState.jugArray[cap12] = 0;
        if (!successorState.equals(jugState)) set.add(successorState);

        // empty capacity 8 jug
        successorState = new StateWaterJugs(jugState);
        successorState.jugArray[cap8] = 0;
        if (!successorState.equals(jugState)) set.add(successorState);

        // empty capacity 3 jug
        successorState = new StateWaterJugs(jugState);
        successorState.jugArray[cap3] = 0;
        if (!successorState.equals(jugState)) set.add(successorState);

        // empty 12 jug into 8 jug
        successorState = transferWater(new StateWaterJugs(jugState), cap12, cap8);
        if (!successorState.equals(jugState)) set.add(successorState);

        // empty 12 jug into 3 jug
        successorState = transferWater(new StateWaterJugs(jugState), cap12, cap3);
        if (!successorState.equals(jugState)) set.add(successorState);

        // empty 8 jug into 12 jug
        successorState = transferWater(new StateWaterJugs(jugState), cap8, cap12);
        if (!successorState.equals(jugState)) set.add(successorState);

        // empty 8 jug into 3 jug
        successorState = transferWater(new StateWaterJugs(jugState), cap8, cap3);
        if (!successorState.equals(jugState)) set.add(successorState);

        // empty 3 jug into 12 jug
        successorState = transferWater(new StateWaterJugs(jugState), cap3, cap12);
        if (!successorState.equals(jugState)) set.add(successorState);

        // empty 3 jug into 8 jug
        successorState = transferWater(new StateWaterJugs(jugState), cap3, cap8);
        if (!successorState.equals(jugState)) set.add(successorState);

        return set;
    }

    private StateWaterJugs transferWater(StateWaterJugs initialState, int srcJug, int dstJug) {
        StateWaterJugs finalState = new StateWaterJugs(initialState);

        int srcVolume = initialState.jugArray[srcJug];
        int dstVolume = initialState.jugArray[dstJug];

        int dstVolumeFinal = Math.min(capMap[dstJug], dstVolume + srcVolume);
        int srcVolumeFinal = Math.max(0, srcVolume - (dstVolumeFinal - dstVolume));

        finalState.jugArray[srcJug] = srcVolumeFinal;
        finalState.jugArray[dstJug] = dstVolumeFinal;

        return finalState;
    }

    double step_cost(Object fromState, Object toState) {
        StateWaterJugs from = (StateWaterJugs) fromState;
        StateWaterJugs to = (StateWaterJugs) toState;
        int cost = 0;
        for(int i=0; i<3; i++) {
            cost = Math.max(cost, Math.abs(to.jugArray[i] - from.jugArray[i]));
        }
        return cost;
    }

    public double h(Object state) { return 0; }

    public static void main(String[] args) throws Exception {
        ProblemWaterJugs problem = new ProblemWaterJugs();
        int[] jugArray = {0,0,0};
        problem.initialState = new StateWaterJugs(jugArray);

        Search search = new Search(problem);

        // Tree Search
        System.out.println("TreeSearch----------------------------------");
        System.out.println("BreadthFirstTreeSearch:\t\t\t" + search.BreadthFirstTreeSearch());
        System.out.println("UniformCostTreeSearch:\t\t\t" + search.UniformCostTreeSearch() + "\n");

        // Graph Search
        System.out.println("GraphSearch----------------------------------");
        System.out.println("BreadthFirstGraphSearch:\t\t" + search.BreadthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t\t\t" + search.UniformCostGraphSearch());
        System.out.println("DepthFirstGraphSearch:\t\t\t" + search.DepthFirstGraphSearch() + "\n");

        System.out.println("IterativeDeepening----------------------------------");
        System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
    }
}
