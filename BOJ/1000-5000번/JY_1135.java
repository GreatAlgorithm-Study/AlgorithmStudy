package dfs;
import java.io.*;
import java.util.*;

public class JY_1135 { 

    static int N;
    static List<Integer>[] g;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        g = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p != -1) {
                g[p].add(i);
            }
        }

        // 최소 시간 계산
        System.out.println(dfs(0));
    }

    public static int dfs(int node) {
        // 리프노드이면 전달시간 0 반환
    	if (g[node].isEmpty()) {
            return 0; 
        }
        
        
        List<Integer> tList = new ArrayList<>();

        // 내 자식에게 전파할 수 있는 총 시간 구하기
        for (int child : g[node]) {
            tList.add(dfs(child));
        }

        // 전파 시간이 긴 순으로 정렬
        Collections.sort(tList, (o1, o2)-> o2-o1);
        
        int maxTime = 0;
        for (int i = 0; i < tList.size(); i++) {
            // 순차적으로 전화를 걸면서, 현재 전화받은 자식 기준으로 시간 계산
            maxTime = Math.max(maxTime, tList.get(i) + i + 1);
        }

        return maxTime;
    }
}
