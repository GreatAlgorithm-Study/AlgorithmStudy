import java.io.*;
import java.util.*;

public class HW_20291 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        TreeMap<String, Integer> words = new TreeMap<>();

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            String extension = input.substring(input.lastIndexOf('.')+1);

            words.put(extension, words.getOrDefault(extension, 0)+1);
        }

        for(Map.Entry<String, Integer> entry : words.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}