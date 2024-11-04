
// 시간복잡도 : n<=100, -32767 < 좌표 < 327867 6만정도 -> O(N) 가능?
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 페스티벌까지 맥주 20병 마시며 도착할 수 있는지
// 최대 이동 거리 : 1000미터 (맥주 20병 * 50)
// 그래프 탐색 : 편의점, 페스티벌 -> 노드 -> 연결 여부 탐색
// BFS 이유 :

// 노드 개수 : n+2 (편의점 (n) + 집, 페스티벌)
// BFS 시간 복잡도 O(V + E) -> O(n^2) 가능
public class HW_9205 {
    static int n; // 편의점 개수
    static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t>0){
            n = Integer.parseInt(br.readLine()); // 편의점 개수
            Point[] points = new Point[n+2];
            for(int i=0; i<n+2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);// x, y 좌표
            }
            t--;
            System.out.println(bfs(points, n) ? "happy" :"sad");
        }
    }
    public static boolean bfs(Point[] points, int n){
        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+2];
        queue.add(points[0]); // 출발 위치(집) 방문 처리
        visited[0] = true;

        while(!queue.isEmpty()){
            Point current = queue.poll();
            if(distance(current, points[n+1]) <= 1000){
                return true;
            }

            for(int i=1; i<n+2; i++){
                if(!visited[i] && distance(current, points[i]) <= 1000){
                    visited[i] = true;
                    queue.add(points[i]);
                }
            }
        }
        return false;
    }
    public static int distance(Point a, Point b){
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}