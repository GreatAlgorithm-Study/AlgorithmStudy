import java.util.*;

//user_id 배열의 크기는 1 이상 8 이하 > 8!
public class YJ_64064 {
    HashSet<String> set = new HashSet<>();
    String[] userIds;
    String[] bannedIds;
    boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        for(int i=0; i<banned_id.length; i++){
            banned_id[i] = banned_id[i].replace('*','.');
        }
        userIds = user_id;
        bannedIds = banned_id;
        visited = new boolean[user_id.length];

        permutation(0, "");
        return set.size();
    }

    //유저 수(n) 중에서 제재 수(r) 만큼 뽑기
    private void permutation(int index, String ids){
        //r만큼 다 뽑았을 경우
        if(index == bannedIds.length){
            String[] combination = ids.split("\\s");
            Arrays.sort(combination);

            StringBuilder bannedCombination = new StringBuilder();
            for (String id : combination) {
                bannedCombination.append(id);
            }
            set.add(bannedCombination.toString());
            return;
        }

        for(int i=0; i<userIds.length; i++){
            //이미 방문했거나 제재 목록과 형식 일치하지 않을 경우 패스
            if(visited[i] || !userIds[i].matches(bannedIds[index])){
                continue;
            }
            visited[i] = true;
            permutation(index+1, ids + " " + userIds[i]);
            visited[i] = false;
        }
    }

}
