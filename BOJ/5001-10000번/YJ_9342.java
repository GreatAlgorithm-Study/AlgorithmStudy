import java.io.*;
import java.util.*;

public class YJ_9342 {
    static final String INFECTED = "Infected!";
    static final String GOOD = "Good";
    static int order;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        List<Character> chromosomeList = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');

        for(int i=0; i<T; i++){
            String chromosome = br.readLine();
            if("AFC".equals(chromosome)){
                System.out.println(INFECTED);
                continue;
            }

            order= 0;
            //문자열은 {A, B, C, D, E, F} 중 0개 또는 1개로 시작해야 한다.
            char c = chromosome.charAt(order);
            if(!chromosomeList.contains(c)){
                System.out.println(GOOD);
                continue;
            }
            order++;
            //그 다음에는 A가 하나 또는 그 이상 있어야 한다.
            if(!checkAFC(chromosome,'A')){
                System.out.println(GOOD);
                continue;
            }
            //그 다음에는 F가 하나 또는 그 이상 있어야 한다.
            if(!checkAFC(chromosome,'F')){
                System.out.println(GOOD);
                continue;
            }
            //그 다음에는 C가 하나 또는 그 이상 있어야 한다.
            if(!checkAFC(chromosome,'C')){
                System.out.println(GOOD);
                continue;
            }
            //그 다음에는 {A, B, C, D, E, F} 중 0개 또는 1개가 있으며,
            if(chromosome.length() > order){
                c = chromosome.charAt(order);
                if(!chromosomeList.contains(c)){
                    System.out.println(GOOD);
                    continue;
                }
            }
            //더 이상의 문자는 없어야 한다.
            if(chromosome.length() > order++){
                System.out.println(GOOD);
                continue;
            }
            System.out.println(INFECTED);
        }
    }

    public static boolean checkAFC(String chromosome, char target){
        if(chromosome.length() <= order){
            return false;
        }

        char c = chromosome.charAt(order);
        if(c != target){
            return false;
        }
        for(; order<chromosome.length(); order++){
            c = chromosome.charAt(order);
            if(c != target){
                break;
            }
        }
        return true;
    }
}
