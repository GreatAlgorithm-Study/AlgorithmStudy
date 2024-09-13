package Programmers.Level2;

import java.util.*;

public class SB_49994_방문길이 {
    static HashMap<Character, int[]> cmd = new HashMap<>();
    static Set<List<Integer>> visited = new HashSet<>();
    public static int solution(String dirs) {
        cmd.put('U', new int[]{-1, 0});
        cmd.put('D', new int[]{1, 0});
        cmd.put('R', new int[]{0, 1});
        cmd.put('L', new int[]{0, -1});

        int curX = 5, curY = 5;
        for (char c : dirs.toCharArray()) {
            int[] d = cmd.get(c);
            int nx = curX+d[0];
            int ny = curY+d[1];
            if (!(0<=nx && nx <=10 && 0<=ny && ny<=10)) continue;
            visited.add(Arrays.asList(curX, curY, nx, ny));
            visited.add(Arrays.asList(nx, ny, curX, curY));
            curX = nx;
            curY = ny;
        }
        return visited.size()/2;
    }

    public static void main(String[] args) {
        String dirs = "LULLLLLLU";
        System.out.println(solution(dirs));
    }
}


/*
* set에 원시타입 배열이 아닌, Integer로 사용하기
* */
