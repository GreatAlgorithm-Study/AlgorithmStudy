import java.io.*;
import java.util.*;

/*
 * 맥주 마시면서 걸어가기
 */

public class DH_9205 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class Node implements Comparable<Node> {
        int e, w;
        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Node> adj[];
    static Point[] p;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine()); // 맥주를 파는 편의점의 개수

            // 시작할 떄 맥주 한 박스 들고 시작 - 200 * 50 미터 갈 수 있음
            p = new Point[n + 2];
            adj = new ArrayList[n + 2];

            // 위치들 입력 받기
            // 0: 집이 있는 곳, n + 1: 락 페스티벌 좌표
            for(int i = 0; i < p.length; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                p[i] = new Point(r,  c);
            }

            // 양방향 인접리스트로 표현하기
            for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Node>();
            for(int i = 0; i < p.length - 1; i++) {
                for(int j = i + 1; j < p.length; j++) {
                    int dis = getDis(i, j);

                    // 해당 지점을 가기 전 50미터에서 맥주 한 병을 다 마셔야 됨
                    // 두 지점간 거리가 1000을 넘으면 못감
                    if(dis > 1000) continue;
                    adj[i].add(new Node(j, dis));
                    adj[j].add(new Node(i, dis));
                }
            }

            // 다익스트라를 통해 '집의 시작점: 0' 에서부터 페스티벌 지점까지 갈 수 있는지 확인
            sb.append(dijkstra(0)? "happy": "sad").append("\n");
        }

        System.out.println(sb);
    }

    // 우선순위 큐를 사용한 다익스트라
    static boolean dijkstra(int s) {
        PriorityQueue<Node> q = new PriorityQueue<Node>();

        q.add(new Node(s, 0));
        boolean[] v = new boolean[adj.length];
        int[] dis = new int[adj.length];

        Arrays.fill(dis, Integer.MAX_VALUE);
        v[0] = true;
        dis[0] = 0;

        while(!q.isEmpty()) {
            Node current = q.poll();
            v[current.e] = true;

            if(current.e == adj.length - 1) return true;
            for(Node next: adj[current.e]) {
                if(v[next.e]) continue;

                if(dis[next.e] > dis[current.e] + next.w) {
                    dis[next.e] = dis[current.e] + next.w;

                    q.add(new Node(next.e, dis[next.e]));
                }
            }
        }

        return false;
    }

    static int getDis(int i, int j) {
        return Math.abs(p[i].r - p[j].r) + Math.abs(p[i].c - p[j].c);
    }
}
