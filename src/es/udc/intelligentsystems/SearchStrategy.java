package es.udc.intelligentsystems;

import java.util.ArrayList;
import java.util.List;

public interface SearchStrategy {
    /**
     * Solves a search problem, obtaining a goal state or throwing an exception if none is found
     * @param p Problem to solve
     * @return Goal state found
     */
    public abstract Node[] solve(SearchProblem p) throws Exception;

    static Node[] buildSol(Node n) {
        List<Node> solution = new ArrayList<>();

        while (n != null) {
            solution.add(n);
            n = n.getParent();
        }

        return solution.toArray(new Node[0]);
    }
}
