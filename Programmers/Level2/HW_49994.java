// [PG] 49994_방문 길이
// 게임 캐릭터가 지나간 길 중 캐릭터가 처음 걸어본 길의 길이 구하기
// 좌표평면(-5, 5) 벗어나는 명령어는 무시
import java.util.*;
class Solution {
    private static boolean isValidMove(int nx, int ny){
        return 0 <= nx && nx < 11 && 0 <= ny && ny < 11;
    }
    private static final HashMap<Character, int[]> location = new HashMap<>();

    private static void initLocation(){
        location.put('U', new int[]{0, 1});
        location.put('D', new int[]{0, -1});
        location.put('L', new int[]{-1, 0});
        location.put('R', new int[]{1, 0});
    }
    public int solution(String dirs) { // U(위), D(아래), L, R
        initLocation();
        int x = 5, y=5;
        HashSet<String> answer = new HashSet<>();

        for(int i=0; i<dirs.length(); i++){
            int[] offset = location.get(dirs.charAt(i));
            int nx = x + offset[0];
            int ny = y + offset[1];

            if(!isValidMove(nx, ny))
                continue;

            answer.add(x + " " + y + " " + nx + " " + ny);
            answer.add(nx + " " + ny + " " + x + " " + y);

            x = nx;
            y = ny;
        }

        return answer.size()/2;
    }
}