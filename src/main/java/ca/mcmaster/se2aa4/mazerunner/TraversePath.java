package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Collections;

public class TraversePath {
    private Integer[][][] previous;
    private ArrayList<Integer[]> path;
    public TraversePath(Integer[][][] previous) {
        this.previous = previous;
        path = new ArrayList();
    }
    public ArrayList<Integer[]> getPath() {
        return this.path;
    }
    public void traversePath() {
        int start_x = 0;
        int start_y = 0;
        int end_x = 0;
        int end_y = 0;

        for (int j = 0; j < previous[0].length; j++) {
            if (previous[0][j][0] != null && previous[0][j][1] != null) {
                start_x = previous[0][j][0];
                start_y = previous[0][j][1];
            }
            if (previous[previous.length - 1][j][0] != null && previous[previous.length - 1][j][1] != null) {
                end_x = previous[previous.length - 1][j][0];
                end_y = previous[previous.length - 1][j][1];
            }
        }
        int curr_x = end_x;
        int curr_y = end_y;
        while (curr_x != start_x || curr_y != start_y) {
            path.add(new Integer[]{curr_x, curr_y});
            Integer[] prev = previous[curr_x][curr_y];
            curr_x = prev[0];
            curr_y = prev[1];
        }
        path.add(new Integer[]{start_x, start_y});
        Collections.reverse(path);
        path.add(new Integer[]{end_x + 1, end_y});
    }
}
