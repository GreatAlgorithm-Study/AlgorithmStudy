import java.util.*;
import java.io.*;
public class JY_전자사전_만들기 {

    static int N, T;
    static Map<String, Integer> sMap;
    static String[] srr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        sMap = new HashMap<>();
        srr = new String[N];
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            srr[i] = str;
            sMap.put(str, i+1);
        }
        Arrays.sort(srr);
        // System.out.println(Arrays.toString(srr));

        for(int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String word = st.nextToken();

            // idx: word보다 작거나(뒤에있는) 같은 것 중 최소 문자열 인덱스
            int idx = bSearch(word);        

            // 구한 인덱스가 -1이 아니고,
            // 탐색할 인덱스 범위를 벗어나지 않으며,
            // word의 길이보다 크거나 같고 (word로 시작하는 단어니깐),
            // word로 시작하는 단어라면 출력
            if(idx != -1 && idx+k < N 
                && srr[idx+k-1].length() >= word.length()
                && srr[idx+k-1].substring(0, word.length()).equals(word)) {
                System.out.println(sMap.get(srr[idx+k-1]));
            } else {
                System.out.println(-1);
            }
        }

    }
    public static int bSearch(String word) {
        int s = 0;
        int e = srr.length-1;

        int ans = -1;
        while(s <= e) {
            int mid = (s+e) / 2;
            // 문자열 비교
            // s1.compareTo(s2) s2가 s1보다 사전순으로 앞서면 양수 반환
            if(srr[mid].compareTo(word) >= 0) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }
}