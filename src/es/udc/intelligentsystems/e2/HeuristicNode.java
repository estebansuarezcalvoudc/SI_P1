package es.udc.intelligentsystems.e2;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.State;

import java.util.Objects;

public class HeuristicNode implements Comparable {
    private final State state;
    private final HeuristicNode parent;
    private final Action action;
    private final float pathCost;
    private final float f;

    public HeuristicNode(State state, HeuristicNode parent, Action action, float heuristic) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.pathCost = parent == null ? 0 : parent.pathCost + action.getCost();
        this.f = pathCost + heuristic;
    }

    public State getState() {
        return this.state;
    }

    public HeuristicNode getParent() {
        return this.parent;
    }

    public Action getAction() {
        return this.action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeuristicNode node = (HeuristicNode) o;
        return state.equals(node.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return state.toString();
    }

    @Override
    public int compareTo(Object o) {
        HeuristicNode n = (HeuristicNode) o;

        return Float.compare(this.f, n.f);
    }
}
