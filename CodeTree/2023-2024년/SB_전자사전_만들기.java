import java.io.*;
import java.util.*;

public class SB_전자사전_만들기 {
    static int N, T;
    static String[] arr;
    static HashMap<String, Integer> map = new HashMap<>();

    private static int findIdx(String target) {
        int left = 0;
        int right = N-1;

        int idx = -1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid].compareTo(target) >= 0) {      // mid값이 target값 뒤에 위치하거나 같음
                idx = mid;
                right = mid;
            } else{
                left = mid+1;
            }
        }
        return idx;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            map.put(arr[i], i + 1);
        }
        Arrays.sort(arr);

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String s = st.nextToken();

            int idx = findIdx(s);

            if (idx != -1 && idx + k < N && arr[idx + k - 1].startsWith(s)) {
                sb.append(map.get(arr[idx + k - 1])).append('\n');
            } else sb.append(-1).append('\n');
        }

        System.out.println(sb);
    }
}
