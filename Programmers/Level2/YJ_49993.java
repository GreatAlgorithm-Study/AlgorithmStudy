import java.util.*;

public class YJ_49993 {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        int answer = 0;
        Map<Character,Integer> skillMap = new HashMap<>();
        for(int i=0; i<skill.length(); i++){
            skillMap.put(skill.charAt(i),i+1);
        }

        for(String tree :skill_trees){
            boolean temp = true;
            int order = 1;

            for(int i=0; i<tree.length(); i++){
                Integer now = skillMap.get(tree.charAt(i));
                if(now == null){
                    continue;
                }

                if(now == order){
                    order++;
                }else{
                    temp = false;
                }
            }

            if(temp){
                answer++;
            }
        }

        System.out.println(answer);
    }
}
