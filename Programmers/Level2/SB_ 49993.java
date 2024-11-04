import java.io.*;
import java.util.*;

class SB_49993 {
    public int solution(String skill, String[] skill_trees) {
        Set<Character> sk = new HashSet<>();
        for (char c : skill.toCharArray()) {
            sk.add(c);
        }

        int cnt = 0;
        for (String st : skill_trees) {
            StringBuilder sb = new StringBuilder();
            for (char c : st.toCharArray()) {
                if (sk.contains(c)) sb.append(c);
            }
            if (skill.startsWith(sb.toString())) cnt++;
        }
        
        return cnt;
    }
}