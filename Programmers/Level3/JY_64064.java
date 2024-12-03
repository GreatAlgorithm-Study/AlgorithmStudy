import java.util.*;
class JY_64064 {
    
    static Set<Set<String>> uSet;
    static Set<String> bSet;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        uSet = new HashSet<>();
        bSet = new HashSet<>();
        dfs(0, user_id, banned_id);
        
        // System.out.println(uSet);
        answer = uSet.size();
        
        return answer;
    }
    public static boolean findId(String u, String b) {
        if(u.length() != b.length()) return false;
        for(int i=0; i<b.length(); i++) {
            if(b.charAt(i) == '*') continue;
            if(u.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }
    public static void dfs(int depth, String[] urr, String[] brr) {
        if(depth == brr.length) {
            boolean isOk = true;
            for(Set<String> sub : uSet) {
                if(sub.containsAll(bSet)) {
                    isOk = false;
                    break;
                }
            }
            if(isOk) {
                // copy
                Set<String> tmp = new HashSet<>();
                for(String s : bSet){
                    tmp.add(s);
                }
                uSet.add(tmp);
            }
            return;
        }
        
        for(String s : urr) {
            // 현재 제재 아이디 목록에 있지 않고 && 불량 사용자 아이디와 매핑 된다면
            if(!bSet.contains(s) && findId(s, brr[depth])) {
                bSet.add(s);
                dfs(depth+1, urr, brr);
                bSet.remove(s);
            }
        }
    }
}