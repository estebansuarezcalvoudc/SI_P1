package es.udc.intelligentsystems.e2;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Backtracking implements SearchStrategy {
    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        Stack<Node> frontier = new Stack<>();
        List<Node> explored = new ArrayList<>();
        List<Action> actions;
        Node currentNode = new Node(p.getInitialState(), null, null);
        int i = 1;
        int createdNodes = 1;

        System.out.println((i++) + " - Starting search at " + currentNode.getState());

        while (!p.isGoal(currentNode.getState())) {
            explored.add(currentNode);

            System.out.println((i++) + " - RESULT(" + currentNode.getState() + "," +
                    currentNode.getAction() + ")=" + currentNode.getState());

            actions = Arrays.stream(p.actions(currentNode.getState())).toList();

            if (actions.isEmpty()) {
                frontier.remove(currentNode);
                currentNode = currentNode.getParent();
                continue;
            }

            State nextState = p.result(currentNode.getState(), actions.get(0));

            Node auxNode = new Node(nextState, currentNode, actions.get(0));
            createdNodes++;

            if (!frontier.contains(auxNode) && !explored.contains(auxNode))
                frontier.push(auxNode);

            if (frontier.isEmpty()) throw new Exception("No solution could be found\n");

            currentNode = frontier.peek();
        }

        System.out.println((i++) + " - END - " + currentNode.getState());
        System.out.println("Created nodes: " + createdNodes);
        System.out.println("Explored nodes: " + explored.size());

        return SearchStrategy.buildSol(currentNode);
    }
}
