import java.util.*;

class Solution {
    static List<File> fileList = new ArrayList<>();
    private static File setFile(String f, int order) {
        char[] cf = f.toCharArray();
        String head, num = null, tail = "";         // tail 초기값 빈 문자열로 설정
        int stN = 0;                                // num 시작 인덱스이자 head 찾았는지 판별
        int idx = 0;

        // head 찾기
        while (!Character.isDigit(cf[idx])){
            idx++;
        }
        head = f.substring(0, idx);
        stN = idx;

        // num 찾기 (최대 5자리)
        for (idx = stN; idx < Math.min(stN + 5, cf.length); idx++) {
            if (!Character.isDigit(cf[idx])) {
                num = f.substring(stN, idx);
                break;
            }
        }

        // 숫자가 끝까지 이어진 경우 예외 처리
        if (num == null) num = f.substring(stN, idx);

        // 남은 부분이 있으면 tail 설정
        if (idx < cf.length) tail = f.substring(idx);

        return new File(head, num, tail, order);
    }
    public static String[] solution(String[] files) {
        int N = files.length;
        // 파일 객체 처리
        for (int i = 0; i < N; i++) {
            File file = setFile(files[i], i);
            fileList.add(file);
        }

        // 파일 정렬
        Collections.sort(fileList);

        // 결과 배열 담기
        String[] ans = new String[N];
        for (int i = 0; i < N; i++) {
            ans[i] = fileList.get(i).toString();
        }
        return ans;
    }

    static class File implements Comparable<File>{
        String head;
        String num;
        String tail;
        int order;

        public File(String head, String num, String tail, int order) {
            this.head = head;
            this.num = num;
            this.tail = tail;
            this.order = order;
        }

        @Override
        public int compareTo(File o) {
            // head 사전순 비교
            int headCmp = this.head.compareToIgnoreCase(o.head);
            if (headCmp != 0) return headCmp;

            // num 숫자 변환 후 비교
            int numCmp = Integer.compare(Integer.parseInt(num), Integer.parseInt(o.num));
            if (numCmp != 0) return numCmp;

            // 마지막 입력 순으로 정렬
            return this.order - o.order;
        }

        @Override
        public String toString() {
            return head + num + tail;
        }
    }
}