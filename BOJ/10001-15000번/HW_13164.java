import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class HW_13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 원생
        int K = Integer.parseInt(st.nextToken()); // 조원 수
        int[] arr = new int[N];
        int[] diff = new int[N-1];
        int result = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N-1; i++){
            diff[i] = arr[i+1] - arr[i];
        }

        Arrays.sort(diff);

        for(int i=0; i<(N-1)-(K-1); i++){
            result += diff[i];
        }
        System.out.println(result);
    }
}