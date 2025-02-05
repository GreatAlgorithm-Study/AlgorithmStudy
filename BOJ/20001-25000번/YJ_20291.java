import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class YJ_20291 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TreeMap<String,Integer> fileMap = new TreeMap<>();
        for(int i=0; i<n; i++){
            String extension = br.readLine().split("\\.")[1];
            fileMap.put(extension,fileMap.getOrDefault(extension, 0)+1);
        }

        for(Entry<String,Integer> entry: fileMap.entrySet()){
            System.out.printf("%s %d%n",entry.getKey(),entry.getValue());
        }
    }
}
