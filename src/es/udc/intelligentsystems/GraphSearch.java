package es.udc.intelligentsystems;

import java.util.*;

public class GraphSearch implements SearchStrategy {
    private Node[] buildSol(Node n) {
        List<Node> solution = new ArrayList<>();

        while (n != null) {
            solution.add(n);
            n = n.getParent();
        }

        return solution.toArray(new Node[0]);
    }

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        List<Node> explored = new LinkedList<>();
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
            explored.add(currentNode);

            System.out.println((i++) + " - RESULT(" + currentNode.getState() + "," +
                    currentNode.getAction() + ")=" + currentNode.getState());

            Action[] avaliableActions = p.actions(currentNode.getState());

            /* Añadir nodos disponibles a la frontera */
            for (Action action : avaliableActions) {
                State nextState = p.result(currentNode.getState(), action);

                Node auxNode = new Node(nextState, currentNode, action);

                if (!frontier.contains(auxNode)) {
                    frontier.add(auxNode);
                }
            }
        }

        System.out.println((i++) + " - END - " + currentNode.getState());

        return buildSol(currentNode);
    }
}
