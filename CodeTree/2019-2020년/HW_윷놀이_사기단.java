import java.util.*;
import java.io.*;

// 미리 계산을 하여 주어진 이동 횟수에 나갈 말의 종류를 잘 조합하여 얻을 수 있는 점수의 최댓값
// 주어진 이동 칸 수를 이용해서 얻을 수 있는 점수의 최댓값을 출력
// 파란 칸에 도착 시 최대 이동 횟수 이동하기 
public class HW_윷놀이_사기단 {
    static int[] board = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40,
            13, 16, 19, 22, 24, 28, 27, 26, 25, 30, 35, 0}; // 32칸
    static int[] horse = {0, 0, 0, 0}; // 말 4개
    static int[][] move = new int[33][6]; // 말의 이동 경로 저장
    static int max = 0;
    static int[] input;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        input = new int[10];

        for(int i=0; i<10; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        simulation();
        dfs(0, 0, horse);
        System.out.println(max);
    }
    private static void simulation(){
        // 윷놀이판 이동 경로 설정
        for(int i=0; i<17; i++){
            for(int j=1; j<=5; j++){ // 윷 놀이 1~5칸 이동 가능
                move[i][j] = i+j;
            }
        }

        // 파란칸 도착 시 이동 경로 설정
        move[5] = new int[]{0, 21, 22, 23, 29, 30}; // 0~5칸 이동
        move[10] = new int[]{0, 24, 25, 29, 30, 31};
        move[15] = new int[]{0, 26, 27, 28, 29, 30};

        // 직선 경로 제외 이동 경로 설정
        move[21] = new int[]{0, 22, 23, 29, 30, 31}; // 13~
        move[22] = new int[]{0, 23, 29, 30, 31, 20};
        move[23] = new int[]{0, 29, 30, 31, 20, 32}; // ~19

        move[24] = new int[]{0, 25, 29, 30, 31, 20};
        move[25] = new int[]{0, 29, 30, 31, 20, 32};
        move[26] = new int[]{0, 27, 28, 29, 30, 31};
        move[27] = new int[]{0, 28, 29, 30, 31, 20};
        move[28] = new int[]{0, 29, 30, 31, 20, 32};
        move[29] = new int[]{0, 30, 31, 20, 32, 32};
        move[30] = new int[]{0, 31, 20, 32, 32, 32};
        move[31] = new int[]{0, 20, 32, 32, 32, 32};

        // 도착 지점 경로 설정
        move[16][5] = 32;
        move[17] = new int[]{0, 18, 19, 20, 32, 32};
        move[18] = new int[]{0, 19, 20, 32, 32, 32};
        move[19] = new int[]{0, 20, 32, 32, 32, 32};
        move[20] = new int[]{0, 32, 32, 32, 32, 32};
    }
    static void dfs(int moveCnt, int score, int[] horse){
        if(moveCnt==10){ // 10번 이동 완료 시
            max = Math.max(max, score);
            return;
        }
        for(int i=0; i<4; i++){
            int cur = horse[i];
            if(cur==32){ // 도착하면 움직이지않음
                continue;
            }
            int next = move[cur][input[moveCnt]]; // 다음 위치 계산
            boolean flag = true;
            for(int j=0; j<4; j++){ // 겹치지 않아야함
                if(i!=j && horse[j]==next && next !=32){ // 도착시 움직이지 않음
                    flag = false;
                    break;
                }
            }
            if(!flag){
                continue;
            }
            horse[i] = next; // 말 이동
            dfs(moveCnt+1, score+board[next], horse);
            horse[i] = cur; // 상태 복원?
        }

    }
}