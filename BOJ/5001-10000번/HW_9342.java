import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HW_9342 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String str = "^[ABCDEF]?A+F+C+[ABCDEF]?$";

        StringBuilder sb = new StringBuilder();
        while(T-->0){
            sb.append(br.readLine().matches(str) ? "Infected!" : "Good").append('\n');
        }
        System.out.println(sb);
    }
}