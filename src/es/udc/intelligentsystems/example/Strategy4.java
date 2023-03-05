package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;

public class Strategy4 implements SearchStrategy {

    public Strategy4() {
    }

    public String buildSol(Node[] nodes) {
        StringBuilder sb = new StringBuilder();

        for (Node n : nodes) {
            sb.append(n).append("\n");
            System.out.println(n + "\n");
        }

        return sb.toString();
    }

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        ArrayList<Node> explored = new ArrayList<>();
        State currentState = p.getInitialState();
        Node currentNode = new Node(currentState, null, null);
        explored.add(currentNode);

        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentState);

        while (!p.isGoal(currentState)) {
            System.out.println((i++) + " - " + currentState + " is not a goal");
            Action[] availableActions = p.actions(currentState);
            boolean modified = false;

            for (Action acc : availableActions) {
                State sc = p.result(currentState, acc);
                System.out.println((i++) + " - RESULT(" + currentState + "," + acc + ")=" + sc);

                if (!explored.contains(new Node(sc, currentNode, acc))) {
                    currentState = sc;
                    Node nextNode = new Node(currentState, currentNode, acc);
                    System.out.println((i++) + " - " + sc + " NOT explored");
                    explored.add(nextNode);
                    modified = true;
                    System.out.println((i++) + " - Current state changed to " + currentState);
                    currentNode = nextNode;
                    break;
                } else
                    System.out.println((i++) + " - " + sc + " already explored");
            }
            if (!modified) throw new Exception("No solution could be found");
        }
        System.out.println((i++) + " - END - " + currentState);

        return explored.toArray(new Node[0]);
    }
}

