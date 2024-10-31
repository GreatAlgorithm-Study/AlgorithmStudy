import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] students = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                students[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(students[i]); // 각 반의 능력치 정렬
        }

        int[] indexs = new int[N]; // 학생들 인덱스를 저장할 배열
        for(int i=0; i<N; i++){
            indexs[i] = 0;
        }
        int min = Integer.MAX_VALUE;

        while(true){
            int curMin = students[0][indexs[0]];
            int curMax= students[0][indexs[0]];
            int minIdex = 0;
            for(int i=1; i<N; i++){
                if (curMin > students[i][indexs[i]]) { // 최솟값
                    curMin = students[i][indexs[i]];
                    minIdex = i;
                }
                if(curMax < students[i][indexs[i]]){ // 최댓값
                    curMax = students[i][indexs[i]];
                }
            }
            if((curMax - curMin) < min){
                min = curMax - curMin;
            }
            if(++indexs[minIdex] >= M)
                break;
        }
        System.out.println(min);
    }
}