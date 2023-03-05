package es.udc.intelligentsystems;

import java.util.Objects;

public class Node {
    private final State state;
    private final Node parent;
    private final Action action;

    public Node(State state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    public State getState() {
        return this.state;
    }

    public Node getParent() {
        return this.parent;
    }

    public Action getAction() {
        return this.action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return state.equals(node.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
