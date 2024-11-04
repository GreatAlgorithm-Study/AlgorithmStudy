import java.io.*;
import java.util.*;

/*
 * 트리의 지름
 */

public class DH_1967 {
    static class Node {
        int e, w;
        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
    static ArrayList<Node> adj[]; // 노드 정보를 인접 리스트로 저장

    public static void main(String[] args) throws Exception {
        initInput();
        solution();
    }

    static void solution() {
        // maxInfo: 노드의 id와 출발점 기준으로 얼마나 떨어져있는지 저장
        int[] maxInfo = getMaxDisInfo(1); // 루트 노듣를 기준으로 maxInfo 구하기
        System.out.println(getMaxDisInfo(maxInfo[0])[1]); // maxInfo기준으로 maxInfo 구하기
    }

    static int[] getMaxDisInfo(int start) {
        // maxInfo[0]: 가장 멀리 떨어져 있는 Node의 idx 저장
        // maxinfo[1]: 가장 멀리 떨어져 있는 Node가 start 지점으로부터 얼마나 떨어져있는지 저장
        int[] maxInfo = new int[2];
        maxInfo[1] = Integer.MIN_VALUE;

        Deque<Node> q = new ArrayDeque<Node>();
        boolean[] v = new boolean[adj.length];

        q.add(new Node(start, 0));
        v[start] = true;

        while(!q.isEmpty()) {
            Node current = q.poll();

            if(maxInfo[1] < current.w) {
                maxInfo[1] = current.w;
                maxInfo[0] = current.e;
            }

            for(Node next: adj[current.e]) {
                if(v[next.e]) continue;
                q.add(new Node(next.e, current.w + next.w));
                v[next.e] = true;
            }
        }


        return maxInfo;
    }


    static void initInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];

        for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Node>();
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, w));
            adj[b].add(new Node(a, w));
        }
    }
}
