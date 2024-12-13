import java.util.*;

/*
* points: i번위치에 대한 '좌표' 정보
* routes: 가야할 points의 '인덱스' 정보
* */
public class lv2_충돌위험찾기 {
    static Map<Integer, Node> map = new HashMap<>();
    static List<List<Node>> paths = new ArrayList<>();

    private static void move(int sx, int sy, int ex, int ey, List<Node> tmp) {
        int cx = sx;
        int cy = sy;

        while (!(cx == ex && cy == ey)) {       // 목표 좌표를 향해 행우선으로 이동
            if (cx > ex) {
                cx--;
                tmp.add(new Node(cx, cy));
                continue;
            }
            if (cx < ex) {
                cx++;
                tmp.add(new Node(cx, cy));
                continue;
            }
            if (cy > ey) {
                cy--;
                tmp.add(new Node(cx, cy));
                continue;
            }
            cy++;
            tmp.add(new Node(cx, cy));
        }
    }
    public static int solution(int[][] points, int[][] routes) {
        int ans = 0;
        for (int i = 0; i < points.length; i++) {                       // 포인트 지점을 맵으로 표현
            map.put(i + 1, new Node(points[i][0], points[i][1]));
        }

        for (int[] r : routes) {                                        // 로봇별로 경로에따라 이동하기
            int start = r[0];
            int sx = map.get(start).x;
            int sy = map.get(start).y;

            List<Node> tmp = new ArrayList<>();
            tmp.add(new Node(sx, sy));

            for (int i = 1; i < r.length; i++) {
                int end = r[i];
                int ex = map.get(end).x;
                int ey = map.get(end).y;

                move(sx, sy, ex, ey, tmp);                              // 목표지점을 향해 이동
                sx = ex;
                sy = ey;
            }
            paths.add(tmp);
        }

        int mx = 0;
        for (List<Node> p : paths) {            // 가장 긴 경로 찾기
            int len = p.size();
            mx = Math.max(mx, len);
        }

        for (int i = 0; i < mx; i++) {
            Set<Integer> set = new HashSet<>();
            Set<Integer> check = new HashSet<>();
            for (List<Node> p : paths) {
                if (p.size() <= i) continue;        // 이미 끝났으면 패쓰

                int key = p.get(i).x * 100 + p.get(i).y;
                if (check.contains(key)) continue;
                if (set.contains(key)) {
                    check.add(key);
                    continue;
                }
                set.add(key);
            }
            ans += check.size();                    // 현재 시간에 충돌 개수 더하기
        }
        return ans;
    }
    public static void main(String[] args) {
        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {2, 4}};
        System.out.println(solution(points, routes));
    }

    static class Node{
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
