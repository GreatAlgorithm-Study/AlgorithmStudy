import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String regex = "^[ABCDEF]?A+F+C+[ABCDEF]?$"; // 조건에 맞는 정규 표현식
        while (n-- > 0) {
            String str = br.readLine();
            boolean infected = Pattern.matches(regex, str); // 해당 문자가 정규표현식과 일치하는지
            if (infected)
                sb.append("Infected!");
            else
                sb.append("Good");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}