package es.udc.intelligentsystems.e2;

import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws Exception {
        int[][] matrix = {
            {4, 9, 2},
            {3, 5, 0},
            {0, 1, 0}
        };

        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(matrix);

        SearchProblem problemaCuadradoMagico = new MagicSquareProblem(initialState);

        SearchStrategy buscador = new DepthFirstSearch();
        System.out.println(Arrays.toString(buscador.solve(problemaCuadradoMagico)));
    }
}
