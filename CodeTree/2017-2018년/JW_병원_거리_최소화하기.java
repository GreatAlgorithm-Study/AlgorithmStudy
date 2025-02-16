import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JW_병원_거리_최소화하기 {

    static int n, m, minDistance = Integer.MAX_VALUE;
    static ArrayList<int[]> pArr = new ArrayList<>(); // 사람들의 좌표
    static ArrayList<int[]> hArr = new ArrayList<>(); // 병원들의 좌표
    static int[][] distances; // 사람 → 병원의 거리
    static int[] choices; // 조합 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int p = Integer.parseInt(st.nextToken());
                if (p == 1)
                    pArr.add(new int[] { i, j });
                else if (p == 2)
                    hArr.add(new int[] { i, j });
            }
        }
        // 좌표로 거리 계산
        distances = new int[pArr.size()][hArr.size()];
        for (int i = 0; i < pArr.size(); i++)
            for (int j = 0; j < hArr.size(); j++) {
                int py = pArr.get(i)[0], px = pArr.get(i)[1];
                int hy = hArr.get(j)[0], hx = hArr.get(j)[1];
                distances[i][j] = Math.abs(py - hy) + Math.abs(px - hx);
            }
        choices = new int[m];
        recursive(0, 0);
        System.out.println(minDistance);
    }

    // 재귀로 조합 계산
    private static void recursive(int depth, int p) {
        // 종료 조건
        if (depth == m) {
            int totalDistance = 0;
            for (int i = 0; i < pArr.size(); i++) {
                int distance = Integer.MAX_VALUE;
                // 뽑은 병원들까지의 거리들 중 최소 거리
                for (int j = 0; j < m; j++)
                    distance = Math.min(distance, distances[i][choices[j]]);
                totalDistance += distance;
            }
            minDistance = Math.min(minDistance, totalDistance);
            return;
        }
        // 다음 재귀 호출
        for (int i = p; i < hArr.size(); i++) {
            choices[depth] = i;
            recursive(depth + 1, i + 1);
        }
    }
}