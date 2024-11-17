import java.io.*;
import java.util.*;

public class YJ_1613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //graph 모델링
        boolean[][] graph = new boolean[n+1][n+1];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            graph[to][from] = true;
        }
        //플로이드 와샬 추가 중간경로 찾기
        for(int d=1; d<=n; d++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(graph[i][d] && graph[d][j]){   //경유가 가능할 경우 i > d > j 사건으로 연결됨
                        graph[i][j] = true;
                    }
                }
            }
        }
        //전후관계 찾기
        int s = Integer.parseInt(br.readLine());
        while(s-- > 0){
            int result = 0;
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            if(before > n || after > n){
                System.out.println(result);
                continue;
            }

            if(graph[before][after]){
                result = -1;    //앞에 있는 번호의 사건이 먼저 일어났으면 -1
            }else if(graph[after][before]){
                result = 1; //뒤에 있는 번호의 사건이 먼저 일어났으면 1
            }
            System.out.println(result);
        }

    }

}
