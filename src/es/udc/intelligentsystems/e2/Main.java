package es.udc.intelligentsystems.e2;

import es.udc.intelligentsystems.InformedSearchStrategy;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;

import java.util.Arrays;


public class Main {

    private static void partA(int[][] matrix) throws Exception {
        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(matrix);
        SearchProblem problemaCuadradoMagico = new MagicSquareProblem(initialState);
        SearchStrategy buscadorNoInformado = new DepthFirstSearch();
        System.out.println(Arrays.toString(buscadorNoInformado.solve(problemaCuadradoMagico)));
    }

    private static void partB(int[][] matrix) throws Exception {
        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(matrix);
        SearchProblem problemaCuadradoMagico = new MagicSquareProblem(initialState);
        InformedSearchStrategy buscadorInformado = new ASearch();
        MagicSquareProblem.MagicSquareHeuristic heuristica = new MagicSquareProblem.MagicSquareHeuristic();
        System.out.println(buscadorInformado.solve(problemaCuadradoMagico, heuristica));
    }

    private static void partC(int[][] matrix) throws Exception {
        MagicSquareProblem.MagicSquareState initialStateC = new MagicSquareProblem.MagicSquareState(matrix);
        SearchProblem problemaCuadradoMagico = new MagicSquareProblem(initialStateC);
        SearchStrategy buscadorNoInformado = new Backtracking();
        System.out.println(Arrays.toString(buscadorNoInformado.solve(problemaCuadradoMagico)));
    }

    public static void main(String[] args) throws Exception {
        int[][] matrixA = {
                {4, 9, 2},
                {3, 5, 0},
                {0, 1, 0}
        };

        int [][] matrixB = {
                {2, 0, 0},
                {0, 0, 0},
                {0, 0 ,0}
        };

        int [][] matrixC = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 0, 0}
        };

        switch (args[0]) {
            case "A" -> partA(matrixA);
            case "B" -> partB(matrixB);
            case "C" -> partC(matrixC);
        }
    }
}
