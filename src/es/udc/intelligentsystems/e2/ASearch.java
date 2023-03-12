package es.udc.intelligentsystems.e2;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ASearch implements InformedSearchStrategy {

    @Override
    public State solve(SearchProblem p, Heuristic h) throws Exception {
        PriorityQueue<HeuristicNode> frontier = new PriorityQueue<>();
        List<HeuristicNode> explored = new ArrayList<>();
        Action[] actions;
        State initialState = p.getInitialState();
        HeuristicNode currentNode = new HeuristicNode(initialState, null, null, h.evaluate(initialState));
        int i = 1;
        int createdNodes = 1;

        System.out.println((i++) + " - Starting search at " + currentNode.getState());

        while (!p.isGoal(currentNode.getState())) {
            explored.add(currentNode);

            System.out.println((i++) + " - RESULT(" + currentNode.getState() + "," +
                    currentNode.getAction() + ")=" + currentNode.getState());

            actions = p.actions(currentNode.getState());

            for (Action action : actions) {
                State nextState = p.result(currentNode.getState(), action);

                HeuristicNode auxNode = new HeuristicNode(nextState, currentNode, action, h.evaluate(nextState));
                createdNodes++;

                if (!frontier.contains(auxNode) && !explored.contains(auxNode))
                    frontier.add(auxNode);
            }

            if (frontier.isEmpty()) throw new Exception("No solution could be found\n");

            currentNode = frontier.poll();
        }

        System.out.println((i++) + " - END - " + currentNode.getState());
        System.out.println("Created nodes: " + createdNodes);
        System.out.println("Explored nodes: " + explored.size());

        return currentNode.getState();
    }
}
