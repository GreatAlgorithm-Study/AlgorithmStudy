import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_15831 {
    static int N, B, W;
    static int[] walks;
    static int[] pebb = new int[2];     // 0:흰돌, 1:검정
    static int mx = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());       // 검정 최대 개수
        W = Integer.parseInt(st.nextToken());       // 흰색 최소 개수

        walks = new int[N];
        String line = br.readLine();
        for (int i = 0; i < N; i++) {
            char c = line.charAt(i);
            walks[i] = (c == 'W') ? 0 : 1;
        }

        int left = 0;
        int right = 0;
        while (right < N) {
            pebb[walks[right]]++;                         // 오른쪽에 해당하는 색의 돌++

            while (pebb[1]>B){                            // 더이상 못갈경우 조건만족할때까지 left빼기
                pebb[walks[left]]--;
                left++;
            }

            if (pebb[0] >= W){                            // 흰돌 만족하면 거리 갱신
                mx = Math.max(mx, right - left + 1);
            }
            right++;
        }
        System.out.println(mx);
    }
}
