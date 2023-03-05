package es.udc.intelligentsystems;

import java.util.Arrays;

import static java.lang.Math.pow;

public class MagicSquareProblem extends SearchProblem {

    private final int N;
    private int[][] magicSquare;

    public MagicSquareProblem(MagicSquareState initialState) {
        super(initialState);
        this.magicSquare = initialState.magicSquare;
        this.N = this.magicSquare.length;
    }

    private boolean checkSum(int[] sum) {
        for (int i = 1; i < N; ++i) {
            if (sum[i-1] != sum[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isGoal(State st) {
        MagicSquareState state = (MagicSquareState) st;
        int[] sum = new int[N * 2 + 2];
        int i, j, k;

        for (i = 0; i < N; ++i)
            for (j = 0; j < N; ++j)
                sum[i] += state.magicSquare[i][j];

        if (!checkSum(sum)) return false;

        for (i = 0; i < N; ++i)
            for (j = 0; j < N; ++j)
                sum[j] += state.magicSquare[i][j];

        if (!checkSum(sum)) return false;

        i = 0;
        j = 0;
        while (i < N) {
            sum[j] += state.magicSquare[i][i];
            i++;
        }

        if (!checkSum(sum)) return false;

        i = 0;
        j = N - 1;
        k = 0;
        while (i < N && j >= 0) {
            sum[k] += state.magicSquare[i][j];
            i++;
            j--;
        }

        return checkSum(sum);
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
                    if (ints[j] < 1 || ints[j] > pow(matrix.length, 2))
                        return false;

            return true;
        }

        public MagicSquareState(int[][] matrix) {
            if (!checkMatrix(matrix)) throw new IllegalArgumentException("Matrix not valid\n");

            N = matrix.length;
            magicSquare = matrix;
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

    public static class MagicSquareAction extends Action {

        @Override
        public String toString() {
            return null;
        }

        @Override
        public boolean isApplicable(State st) {
            return false;
        }

        @Override
        public State applyTo(State st) {
            return null;
        }
    }
}
