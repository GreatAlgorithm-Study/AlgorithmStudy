import java.util.*;
import java.io.*;

/**
 * 알고리즘: 이분탐색
 * 시간복잡도: 1 ≤ N ≤ 100,000 → 10^5 으로 `O(NlogN)` 이내로 풀이하기
 */
public class YJ_전자사전_만들기 {
    static List<String> original = new ArrayList<>();
    static String[] dictionary = null;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split("\\s");
        int N = Integer.parseInt(line1[0]);
        int T = Integer.parseInt(line1[1]);

        dictionary = new String[N];
        for(int i=0; i<N; i++){
            String word = br.readLine();
            dictionary[i] = word;
            original.add(word);
        }
        Arrays.sort(dictionary);

        int[] orders = new int[T];
        String[] findWords = new String[T];
        for(int i=0; i<T; i++){
            String[] line2 = br.readLine().split("\\s");
            orders[i] = Integer.parseInt(line2[0]);
            findWords[i] = line2[1];
        }

        for(int i=0; i<T; i++){
            int k = orders[i];
            String value = findWords[i];

            //검색된 단어들의 시작과 끝
            int start = findStartSearch(value);
            int end = findEndSearch(value);
            //k 번째 단어 찾기
            String word  = findWord(start,end,k);
            //k번째 단어의 입력순서 찾기
            int orderNo = word.isEmpty()? -1 : original.indexOf(word) +1;
            System.out.println(orderNo);
        }

        br.close();
    }

    static int findStartSearch(String target){
        int left = 0;
        int right = dictionary.length-1;
        int start = dictionary.length-1;

        while(left <= right){
            int mid = (left+right)/2;
            if(dictionary[mid].startsWith(target)){
                start = mid;
                right = mid-1;
            }
            //mid 값이 더 클 경우
            else if (dictionary[mid].compareTo(target) > 0) {
                right = mid-1;
            }
            //mid 값이 더 작을 경우
            else{
                left = mid+1;
            }
        }
        return start;
    }

    static int findEndSearch(String target){
        int left = 0;
        int right = dictionary.length-1;
        int end = 0;

        while(left <= right){
            int mid = (left+right)/2;
            if(dictionary[mid].startsWith(target)){
                end = mid;
                left = mid+1;
            }
            //mid 값이 더 클 경우
            else if (dictionary[mid].compareTo(target) > 0) {
                right = mid-1;
            }
            //mid 값이 더 작을 경우
            else{
                left = mid+1;
            }
        }
        return end;
    }

    static String findWord(int start, int end, int k){
        if(end-start < k-1){
            return "";
        }
        int j=0;
        String target = "";
        for(int i=start; i<=end; i++){
            target = dictionary[i];
            if(k-1 == j){
                break;
            }
            j++;
        }
        return target;
    }
}
