package es.udc.intelligentsystems.e2;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch implements SearchStrategy {
    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        Stack<Node> frontier = new Stack<>();
        List<Node> explored = new ArrayList<>();
        Action[] actions;
        Node currentNode = new Node(p.getInitialState(), null, null);
        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentNode.getState());

        while (!p.isGoal(currentNode.getState())) {
            explored.add(currentNode);

            System.out.println((i++) + " - RESULT(" + currentNode.getState() + "," +
                    currentNode.getAction() + ")=" + currentNode.getState());

            actions = p.actions(currentNode.getState());

            for (Action action : actions) {
                State nextState = p.result(currentNode.getState(), action);

                Node auxNode = new Node(nextState, currentNode, action);

                if (!frontier.contains(auxNode) && !explored.contains(auxNode))
                    frontier.push(auxNode);
            }

            if (frontier.isEmpty()) throw new Exception("No solution could be found\n");

            currentNode = frontier.pop();
        }

        System.out.println((i++) + " - END - " + currentNode.getState());

        return SearchStrategy.buildSol(currentNode);
    }
}
