import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int[][] solvedState = {{1,2,3},{4,5,6},{7,8,0}};
    static List<int[][]> visitedStates = new LinkedList<>();
    public static void main(String[] args){

        int[][] puzzleState = { {1,2,0},
                                {4,5,3},
                                {7,8,6}
        };


        System.out.println(puzzleState);

//        for (int[][] state:findAllValidStates(puzzleState)) {
//            for (int i = 0; i < state.length; i++) {
//                for (int j = 0; j < state[i].length; j++) {
//                    System.out.print(state[i][j]+",");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }


        recurse(puzzleState);



       //findAllValidStates(puzzleState).stream().forEach(System.out::println);
    }

    public static boolean recurse(int[][] currentState){
        if(Arrays.deepEquals(currentState, solvedState)){
            System.out.println("solved");
            return true;
        }
        List<int[][]> nextStates = findAllValidStates(currentState);
        for (int[][]nextState:nextStates) {

            if(!checkIfContained(visitedStates, nextState)){
                visitedStates.add(nextState);
                if(recurse(nextState)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkIfContained(List<int[][]> list, int[][] element){
        boolean containsState = false;
        for(int[][] state:list){
            if(Arrays.deepEquals(state, element)){
                containsState = true;
                break;
            }
        }
        return containsState;
    }

    public static List<int[][]> findAllValidStates(int[][] state){
        //find hole
        int holeCoordinates[] = new int[2];
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if(state[i][j] == 0){
                    holeCoordinates[0] = i;
                    holeCoordinates[1] = j;
                }
            }
        }
        //get neighbors
        //neighbor1 == holeCoordinates[0] -1 if >= 0
        //neighbor2 == holeCoordinates[1] -1 if >= 0
        //neighbor3 == holeCoordinates[0] +1 if <= 2
        //neighbor4 == holeCoordinates[1] +1 if <= 2

        LinkedList<int[]> neighbors = new LinkedList<>();

        //get all neighbors vertical
        for (int i = -1; i <= 1; i++) {
            int[] coordinates = new int[2];
            int offsetCol = holeCoordinates[1] + i;
            if (offsetCol < 0 || offsetCol > 2 || offsetCol == holeCoordinates[1]) {
                continue;
            }

            coordinates[0] = holeCoordinates[0];
            coordinates[1] = holeCoordinates[1] + i;
            neighbors.add(coordinates);
        }
        //get all neighbors horizontal
        for (int i = -1; i <= 1; i++) {
            int[] coordinates = new int[2];
            int offsetCol = holeCoordinates[0] + i;
            if (offsetCol < 0 || offsetCol > 2 || offsetCol == holeCoordinates[1]) {
                continue;
            }

            coordinates[0] = holeCoordinates[0] + i;
            coordinates[1] = holeCoordinates[1];
            neighbors.add(coordinates);
        }

        //get next states from neighbors
        List<int[][]> nextStates = new LinkedList<>();
        for (int[] neighbor:neighbors) {

            int[][] newState = Arrays.stream(state).map(int[]::clone).toArray(int[][]::new);
            int temp = newState[neighbor[0]][neighbor[1]];
            newState[neighbor[0]][neighbor[1]] = 0;
            newState[holeCoordinates[0]][holeCoordinates[1]] = temp;
            nextStates.add(newState);
        }

        return nextStates;
//        if(holeCoordinates[0]-1 >= 0){
//
//            coordinates[0] = holeCoordinates[0]-1;
//            coordinates[1] = holeCoordinates[1];
//            neighbors.add(coordinates);
//        }
    }
}
