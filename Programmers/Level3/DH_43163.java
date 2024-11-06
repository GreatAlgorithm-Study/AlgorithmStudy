import java.util.*;

/*
단어 변환
 */
public class DH_43163 {

    class Solution {
        static class Node {
            int idx, depth;
            public Node(int idx, int depth) {
                this.idx = idx;
                this.depth = depth;
            }
        }
        static int N;
        static boolean v[];
        public int solution(String begin, String target, String[] words) {
            int answer = 0;

            N = begin.length();
            Queue<Node> q = new LinkedList<>();
            v = new boolean[words.length];

            for(int i = 0; i < words.length; i++) {
                int diffCnt = getDiffCnt(begin, words[i]);
                if(diffCnt == 1) {
                    q.add(new Node(i, 1));
                    v[i] = true;
                }
            }

            while(!q.isEmpty()) {
                Node current = q.poll();

                if(words[current.idx].equals(target)) {
                    answer = current.depth;
                    break;
                }
                for(int i = 0; i < words.length; i++) {

                    if(v[i]) continue;
                    int diffCnt = getDiffCnt(words[current.idx], words[i]);
                    if(diffCnt == 1) {
                        q.add(new Node(i, current.depth + 1));
                        v[i] = true;
                    }
                }
            }
            return answer;
        }

        static boolean check(int idx) {
            return idx >= 0 && idx < v.length;
        }

        static int getDiffCnt(String s1, String s2) {
            if(s1.length() != s2.length()) return 0;
            int cnt = 0;

            for(int i = 0; i < s1.length(); i++) {
                if(s1.charAt(i) != s2.charAt(i)) cnt++;
            }

            return cnt;
        }
    }
}
