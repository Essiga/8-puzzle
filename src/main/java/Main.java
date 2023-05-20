import java.util.LinkedList;

public class Main {
    public static void main(String[] args){
        int solvedState[][] = {{1,2,3},{4,5,6},{7,8,0}};
        int puzzleState[][] = { {1,2,3},
                                {4,0,5},
                                {7,8,6}
        };


        System.out.println(puzzleState);
    }

    public LinkedList<int[][]> findAllValidStates(int[][] state){
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
        int[] coordinates = new int[2];
        if(holeCoordinates[0]-1 >= 0){

            coordinates[0] = holeCoordinates[0]-1;
            coordinates[1] = holeCoordinates[1];
            neighbors.add(coordinates);
        }
    }
}
