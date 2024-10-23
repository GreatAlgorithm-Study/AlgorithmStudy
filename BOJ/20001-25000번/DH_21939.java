import java.io.*;
import java.util.*;

/*
 * 문제 추천 시스템
 */

public class DH_21939 {
    static class Node implements Comparable<Node> {
        int num, level;

        public Node(int num, int level) {
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(Node o) {
            if(this.level == o.level) return Integer.compare(this.num, o.num); // 오름차순
            return Integer.compare(this.level, o.level); // 오름차순
        }
    }

    static TreeMap<Node, Integer> mapToIdx;
    static TreeMap<Integer, Node> idxToMap;
    static StringBuilder sb = new StringBuilder();

    static void recommend(StringTokenizer st) {
        int x = Integer.parseInt(st.nextToken());
        if(x == 1) sb.append(mapToIdx.lastKey().num + "\n");
        else sb.append(mapToIdx.firstKey().num + " \n");
    }

    static void add(StringTokenizer st) {
        // 난이도가 L인 문제 번호 P 추가
        int P = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Node n = new Node(P, L);
        idxToMap.put(P, n);
        mapToIdx.put(n, P);
    }

    static void solved(StringTokenizer st) {
        int P = Integer.parseInt(st.nextToken());

        Node current = idxToMap.get(P);
        mapToIdx.remove(current);
        idxToMap.remove(P);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        mapToIdx = new TreeMap<>();
        idxToMap = new TreeMap<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken()); // 문제 번호
            int L = Integer.parseInt(st.nextToken()); // 난이도

            Node n = new Node(P, L);
            mapToIdx.put(n, P);
            idxToMap.put(P, n);
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            String o = st.nextToken();
            if(o.equals("recommend")) recommend(st);
            if(o.equals("add")) add(st);
            if(o.equals("solved")) solved(st);
        }

        System.out.println(sb);
    }
}