package es.udc.intelligentsystems.e2;

import es.udc.intelligentsystems.InformedSearchStrategy;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.State;

import java.sql.SQLOutput;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws Exception {
        int[][] A = {
            {4, 9, 2},
            {3, 5, 0},
            {0, 1, 0}
        };

        int [][] B = {
            {2, 0, 0},
            {0, 0, 0},
            {0, 0 ,0}
        };

        /* Apartado A */
        MagicSquareProblem.MagicSquareState initialStateA = new MagicSquareProblem.MagicSquareState(A);
        SearchProblem problemaCuadradoMagicoA = new MagicSquareProblem(initialStateA);
        SearchStrategy buscadorNoInformado = new DepthFirstSearch();
        //System.out.println(Arrays.toString(buscadorNoInformado.solve(problemaCuadradoMagicoA)));

        /* Apartado B */
        MagicSquareProblem.MagicSquareState initialStateB = new MagicSquareProblem.MagicSquareState(B);
        SearchProblem problemaCuadradoMagicoB = new MagicSquareProblem(initialStateB);
        InformedSearchStrategy buscadorInformado = new ASearch();
        MagicSquareProblem.MagicSquareHeuristic heuristica = new MagicSquareProblem.MagicSquareHeuristic();
        System.out.println(buscadorInformado.solve(problemaCuadradoMagicoB, heuristica));
    }
}
