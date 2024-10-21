import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static String[] words; // 문자열을 저장할 배열
    static HashMap<String, Integer> dictionary = new HashMap<>(); // 각 문자열이 몇 번째로 입력이 들어왔는지 저장할 맵

    public static void main(String[] args) throws Exception {
        // 1. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        words = new String[n];
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            words[i] = word;
            dictionary.put(word, i + 1); // 문자열을 key로 해서 인덱스 저장
        }
        Arrays.sort(words); // 이분 탐색을 위한 정렬
        StringBuilder sb = new StringBuilder();
        // 2. 입력순서 추적
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            String prefix = st.nextToken();
            int result = findByIdx(prefix, idx); // 입력순서 찾기
            sb.append(result).append("\n");
        }
        // 3. 출력
        System.out.println(sb);
    }

    // 해당 접두사를 가지는 집합에서 몇 번째로 입력됐는지
    private static int findByIdx(String prefix, int idx) {
        int lowerBound = getLowerBound(prefix);
        // 해당 Lower Bound가 유효한지 확인
        if (!isPossible(prefix, idx, lowerBound)) {
            return -1;
        }
        return dictionary.get(words[lowerBound + idx - 1]); // 입력 순서 반환
    }

    // 이분 탐색으로 해당 접두사를 가지는 문자열의 Lower Bound를 반환하는 함수
    private static int getLowerBound(String prefix) {
        int l = 0, r = words.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            // 타겟이 사전적으로 앞에 위치하는지 확인
            if (words[m].compareTo(prefix) < 0)
                l = m + 1;
            else
                r = m - 1;
        }
        return l;
    }

    // 가능한 인덱스인지 확인하는 함수
    private static boolean isPossible(String prefix, int idx, int lowerBound) {
        // 경계 조건
        if (idx + lowerBound >= words.length)
            return false;
        // 해당 인덱스의 접두사가 일치하는지 확인
        return words[idx + lowerBound - 1].startsWith(prefix);
    }
}