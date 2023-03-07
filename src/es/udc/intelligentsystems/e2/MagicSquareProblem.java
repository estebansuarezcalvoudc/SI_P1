package es.udc.intelligentsystems.e2;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Math.pow;

public class MagicSquareProblem extends SearchProblem {
    private final int N;
    private int[][] magicSquare;
    final List<Position> positions;

    public MagicSquareProblem(MagicSquareState initialState) {
        super(initialState);
        magicSquare = initialState.magicSquare;
        N = magicSquare.length;
        positions = new ArrayList<>();
        Position pos = new Position();

        for (pos.i = 0; pos.i < N; ++pos.i)
            for (pos.j = 0; pos.j < N; ++pos.j)
                if (magicSquare[pos.i][pos.j] != 0)
                    positions.add(pos);
    }

    private boolean checkSum(int sum) {
        return (sum != (pow(N, 3) + N) / 2);
    }

    @Override
    public boolean isGoal(State st) {
        MagicSquareState state = (MagicSquareState) st;
        int[] sum = new int[N * 2 + 2];
        int i, j;

        for (i = 0; i < N; ++i) {
            for (j = 0; j < N; ++j)
                sum[i] += state.magicSquare[i][j];

            if (checkSum(sum[i])) return false;
        }

        for (i = 0; i < N; ++i){
            for (j = 0; j < N; ++j)
                sum[j] += state.magicSquare[i][j];
            if (checkSum(sum[i])) return false;
        }

        i = 0;
        while (i < N) {
            sum[0] += state.magicSquare[i][i];
            i++;
        }

        if (!checkSum(sum[0])) return false;

        i = 0;
        j = N - 1;
        while (i < N && j >= 0) {
            sum[0] += state.magicSquare[i][j];
            i++;
            j--;
        }

        return checkSum(sum[0]);
    }

    @Override
    public Action[] actions(State st) {


        return new Action[0];
    }

    public static class MagicSquareState extends State {
        private final int[][] magicSquare;
        private final int N;

        private boolean checkMatrix(int[][] matrix) {
            for (int i = 0; i < matrix.length-1; ++i)
                if (matrix[i].length != matrix[i + 1].length)
                    return false;

            for (int[] ints : matrix)
                for (int j = 0; j < matrix.length; ++j)
                    if (ints[j] < 0 || ints[j] > pow(matrix.length, 2))
                        return false;

            return true;
        }

        public MagicSquareState(int[][] matrix) {
            if (!checkMatrix(matrix)) throw new IllegalArgumentException("Matrix not valid\n");

            N = matrix.length;
            magicSquare = matrix;
        }

        public int[][] getMagicSquare() {
            return magicSquare;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            int i, j;

            sb.append("\n");

            for (i = 0; i < N; ++i) {
                for (j = 0; j < N; ++j) {
                    sb.append(magicSquare[i][j]).append("\t");
                }
                sb.append("\n");
            }

            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MagicSquareState that = (MagicSquareState) o;
            return Arrays.deepEquals(magicSquare, that.magicSquare);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(magicSquare);
        }
    }

    public class MagicSquareAction extends Action {
        private int x;
        private int y;
        private int num;

        public MagicSquareAction(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")" + " " + num;
        }

        @Override
        public boolean isApplicable(State es) {
            MagicSquareState esC = (MagicSquareState) applyTo(es);

            if (((MagicSquareState)es).magicSquare[x][y] != 0) return false;

            int num = esC.N;
            int maxN = (num*((num*num)+1))/2;

            int sumd1 = 0,sumd2=0;
            int rowSum = 0, colSum = 0;
            for (int i = 0; i < esC.N; i++) {
                if (maxN < (sumd1 += esC.magicSquare[i][i])) return false;
                if (maxN < (sumd2 += esC.magicSquare[i][esC.N-1-i])) return false;
                if (maxN < (rowSum += esC.magicSquare[x][i])) return false;
                if (maxN < (colSum += esC.magicSquare[i][y])) return false;
            }
            return true;
        }

        @Override
        public State applyTo(State es) {
            MagicSquareState esC = ((MagicSquareState) es);
            int[][] matriz = new int[esC.N][esC.N];

            for(int i = 0 ; i < esC.N ; i++)
                System.arraycopy(esC.magicSquare[i], 0, matriz[i], 0, esC.N);

            matriz[x][y] = num;

            return new MagicSquareState(matriz);
        }
    }
}
