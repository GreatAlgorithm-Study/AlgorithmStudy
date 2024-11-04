package BOJ;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 알고리즘: BFS
 * 시간복잡도:
 * 테스트 케이스 t ≤ 50 , 편의점 갯수 0 ≤ n ≤ 100
 * 모든 지점 간의 거리 계산 시 N * (N-1) = O(N^2) > 50 * (100^2) = 50 * 10000 = 500,000 > 10^5 으로 BFS 풀이가능
 * */
public class YJ_9205 {
    static class Pos {
        int x;
        int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Deque<Pos> DEQUE;
    static int[][] CONVINI;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            int conviniCount = Integer.parseInt(br.readLine());

            //집
            String[] home = br.readLine().split("\\s");
            DEQUE = new ArrayDeque<>();
            DEQUE.offer(new Pos(Integer.parseInt(home[0]), Integer.parseInt(home[1])));
            //편의점
            CONVINI = new int[conviniCount][2];
            for(int j=0; j<conviniCount; j++){
                String[] c = br.readLine().split("\\s");
                CONVINI[j][0] = Integer.parseInt(c[0]);
                CONVINI[j][1] = Integer.parseInt(c[1]);
            }
            //페스티벌
            String[] f = br.readLine().split("\\s");
            Pos festival = new Pos(Integer.parseInt(f[0]), Integer.parseInt(f[1]));

            bfs(conviniCount, festival);
        }
        br.close();
    }

    static void bfs(int conviniCount, Pos festival){
        final int STAMINA = 1000;
        boolean[] visited = new boolean[conviniCount];

        while(!DEQUE.isEmpty()){
            Pos current = DEQUE.poll();
            if(calculateDistance(current,festival) <= STAMINA){
                System.out.println("happy");
                return;
            }

            for(int i=0; i<conviniCount; i++){
                if(visited[i]){
                    continue;
                }
                Pos nextConvini = new Pos(CONVINI[i][0],CONVINI[i][1]);
                if(calculateDistance(current,nextConvini) <= STAMINA){
                    DEQUE.offer(nextConvini);
                    visited[i] = true;
                }
            }
        }
        System.out.println("sad");
    }

    private static int calculateDistance(Pos current, Pos next){
        return Math.abs(current.x-next.x) + Math.abs(current.y-next.y);
    }

}
