package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.GraphSearch;
import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.SearchProblem;

public class Main {

    public static void main(String[] args) throws Exception {
        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

        SearchStrategy buscador = new GraphSearch();
        Node[] sol = buscador.solve(aspiradora);
        System.out.println(buscador.buildSol(sol));
    }
}
