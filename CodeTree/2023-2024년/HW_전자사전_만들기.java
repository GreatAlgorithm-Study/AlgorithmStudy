// 시간 복잡도 : N^2 초과 예상, O(n logn)으로 줄이기 -> 이진탐색 사용
// N(100,000) * T(1,000) * 길이 (1,000) -> 완전탐색 불가
import java.io.*;
import java.util.*;
public class HW_전자사전_만들기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        List<String> words = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();  // Key, Value값 함께 저장

        for(int i=0; i<N; i++){
            String word = br.readLine();
            words.add(word);
            map.put(word, i+1); // 단어 인덱스 저장(1부터)
        }
        Collections.sort(words); // 사전 순으로 정렬

        StringBuilder sb= new StringBuilder();
        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String prefix = st.nextToken();

            int start = lower(words, prefix);
            int end = lower(words, nextPrefix(prefix));

            // prefix로 시작하는 단어들 중 K번째가 있는지 확인
            if(start +k-1 < end){ // 존재하는 경우
                String kWord = words.get(start+k-1);
                sb.append(map.get(kWord)).append('\n');
            } else{
                sb.append("-1\n"); // 존재하지 않는 경우 -1 출력
            }
        }
        System.out.print(sb.toString());
    }
    public static int lower(List<String> words, String prefix){ // 이진탐색으로 첫 단어 위치를 찾음
        int left = 0, right = words.size();
        while(left<right){
            int mid = (left+right)/2;
            if(words.get(mid).compareTo(prefix) >=0){
                right = mid;
            } else left = mid +1;
        }
        return left;
    }

    public static String nextPrefix(String prefix){ // prefix 다음으로 올 수 있는 단어 위치 찾음
        int len = prefix.length();
        StringBuilder sb = new StringBuilder(prefix);
        sb.setCharAt(len-1, (char)(sb.charAt(len-1)+1));
        return sb.toString();
    }
}