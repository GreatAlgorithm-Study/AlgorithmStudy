import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 모든 물웅덩이들을 덮기 위해 필요한 널빤지들의 최소 개수를 출력
// 시작 위치로부터 순서대로(->정렬) 물웅덩이를 덮어 나가기 -> 그리디
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] puddles = new int [N][2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            puddles[i][0] = s; // 물웅덩이 시작
            puddles[i][1] = e; // 물웅덩이 끝
        }
        Arrays.sort(puddles, (a, b) -> Integer.compare(a[0], b[0])); // 시작 위치부터 순서대로 덮기 위해 오름차순 정렬

        int current = 0; // 현재 덮은 위치의 끝
        int cnt = 0; // 필요한 널빤지 개수

        for(int i=0; i<puddles.length; i++){ // 물 웅덩이 덮기
            int s = puddles[i][0];
            int e = puddles[i][1];

            if(current >= e){ // 이미 덮였다면
                continue;
            }
            if(current < s){
                current = s;
            }

            int result = (int) Math.ceil((e - current) / (double) L); // (덮어야할 길이 / 널빤지 길이)
            cnt += result; // 널빤지 개수++
            current += result * L; // 덮은 위치 갱신
        }
        System.out.println(cnt);
    }
}