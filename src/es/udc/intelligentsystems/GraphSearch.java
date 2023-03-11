package es.udc.intelligentsystems;

import java.util.*;

public class GraphSearch implements SearchStrategy {
    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        Queue<Node> frontier = new LinkedList<>();
        Node currentNode = new Node(p.getInitialState(), null, null);
        int i = 1;

        frontier.add(currentNode);

        System.out.println((i++) + " - Starting search at " + currentNode.getState());

        while (!p.isGoal(currentNode.getState())) {
            if (frontier.isEmpty()) {
                throw new Exception("No solution could be found");
            }

            currentNode = frontier.poll();

            System.out.println((i++) + " - RESULT(" + currentNode.getState() + "," +
                    currentNode.getAction() + ")=" + currentNode.getState());

            Action[] availableActions = p.actions(currentNode.getState());

            /* Añadir nodos disponibles a la frontera */
            for (Action action : availableActions) {
                State nextState = p.result(currentNode.getState(), action);

                Node auxNode = new Node(nextState, currentNode, action);

                if (!frontier.contains(auxNode)) {
                    frontier.add(auxNode);
                }
            }
        }

        System.out.println((i++) + " - END - " + currentNode.getState());

        return SearchStrategy.buildSol(currentNode);
    }
}
