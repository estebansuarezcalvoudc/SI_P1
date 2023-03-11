package es.udc.intelligentsystems.e2;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.pow;

public class MagicSquareProblem extends SearchProblem {

    public MagicSquareProblem(MagicSquareState initialState) {
        super(initialState);
    }

    @Override
    public boolean isGoal(State st) {
        MagicSquareState esC = (MagicSquareState) st;
        int sumd1 = 0,sumd2=0;

        for (int i = 0; i < esC.N; i++) {
            sumd1 += esC.magicSquare[i][i];
            sumd2 += esC.magicSquare[i][esC.N-1-i];
        }

        //compara que las diagonales sumen lo mismo
        if (sumd1 != sumd2)
            return false;

        for (int i = 0; i < esC.N; i++) {
            int rowSum = 0, colSum = 0;

            for (int j = 0; j < esC.N; j++) {
                rowSum += esC.magicSquare[i][j];
                colSum += esC.magicSquare[j][i];
            }
            //compara que las filas y columnas sumen lo mismo
            if (rowSum != colSum || colSum != sumd1)
                return false;
        }
        return true;
    }

    @Override
    public Action[] actions(State st) {
        MagicSquareState s = (MagicSquareState) st;
        List<Action> actions = new ArrayList<>();
        List<Integer> nums = new LinkedList<>();
        List<Integer> auxList = new ArrayList<>();
        Action action;
        int i, j;

        for (i = 1; i <= s.N * s.N; ++i) nums.add(i);

        for (i = 0; i < s.N; ++i) {
            for (j = 0; j < s.N; ++j) {
                if (s.magicSquare[i][j] == 0) continue;

                auxList.add(s.magicSquare[i][j]);
            }
        }

        for (Integer num : auxList) {
            nums.remove(num);
        }

        for (i = 0; i < s.N; ++i) {
            for (j = 0; j < s.N; ++j) {
                if (s.magicSquare[i][j] != 0) continue;

                for (Integer num : nums) {
                    action = new MagicSquareAction(i, j, num);
                    if (action.isApplicable(s))
                        actions.add(action);
                }
            }
        }

        return actions.toArray(new Action[0]);
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
            return that.N == N && Arrays.deepEquals(magicSquare, that.magicSquare);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(magicSquare);
        }
    }

    public static class MagicSquareAction extends Action {
        private final int x;
        private final int y;
        private final int num;

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
        public boolean isApplicable(State st) {
            MagicSquareState state = (MagicSquareState) applyTo(st);

            if (((MagicSquareState) st).magicSquare[x][y] != 0) return false;

            int num = state.N;
            int maxN = (num*((num*num)+1))/2;

            int sumd1 = 0,sumd2=0;
            int rowSum = 0, colSum = 0;
            for (int i = 0; i < state.N; i++) {
                if (maxN < (sumd1 += state.magicSquare[i][i])) return false;
                if (maxN < (sumd2 += state.magicSquare[i][state.N-1-i])) return false;
                if (maxN < (rowSum += state.magicSquare[x][i])) return false;
                if (maxN < (colSum += state.magicSquare[i][y])) return false;
            }
            return true;
        }

        @Override
        public State applyTo(State st) {
            MagicSquareState state = ((MagicSquareState) st);
            int[][] matrix = new int[state.N][state.N];

            for(int i = 0 ; i < state.N ; i++)
                System.arraycopy(state.magicSquare[i], 0, matrix[i], 0, state.N);

            matrix[x][y] = num;

            return new MagicSquareState(matrix);
        }
    }
}
