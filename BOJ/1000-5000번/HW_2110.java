import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 복잡도 : N, C <= 200,000 xi <= 10^9 -> 이분 탐색 시간 복잡도를 줄여줘야함
// 가장 인접한 두 공유기 사이의 최대 거리를 출력
// 공유기 사이의 최소 거리를 mid값으로 두어 UpperBound

public class HW_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기의 개수
        int[] houses = new int[N];

        for(int i=0; i<N; i++){
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int s = 1; // 최소 거리
        int e = houses[N-1] - houses[0];
        int ans = 0;

        while(s <= e){
            int first = houses[0];
            int count = 1;
            int m = (s+e)/2; // mid = 공유기 사이의 최소 거리
            for(int i=1; i<N; i++){
                if(houses[i] - first >= m){
                    count++;
                    first = houses[i];
                }
            }
            if(count>=C){
                ans = m;
                s = m+1;
            } else e = m-1;

        }
        System.out.println(ans);
    }
}