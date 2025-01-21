import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 집과 사무실의 위치 모두 철로 선분에 포함되는 사람들의 수가 최대가 되도록, 철로 선분 정하기
시간 복잡도 : N <=100,000, O(N^2) 불가, O(N) or O(N logN) 풀기
뭔가 요격 시스템이랑 비슷할거같은 문제 : 요격 시스템은 e 구간 기준으로 정렬했었음
이 문제도 사무실 기준으로 정렬해보기
 */
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(h, o); // 시작점
            arr[i][1] = Math.max(h, o); // 끝점
        }
        int d = Integer.parseInt(br.readLine()); // 철로의 길이

        Arrays.sort(arr, (a, b) -> Integer.compare(a[1], b[1])); // e(끝점) 기준 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int maxCnt = 0;

        for(int i=0; i<arr.length; i++){
            int s = arr[i][0];
            int e = arr[i][1];

            while(!pq.isEmpty() && pq.peek() < e-d){ // 기준에 벗어나는 것 큐에서 제거
                pq.poll();
            }
            if(e-s<=d){ // d 길이보다 작으면 추가
                pq.add(s);
            }
            maxCnt = Math.max(maxCnt, pq.size());
        }
        System.out.println(maxCnt);
    }
}