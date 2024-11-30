import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class HW_17686 {
    public String[] solution(String[] files) {
        String regex = "^([^0-9]+)([0-9]{1,5}).*$";
        Pattern pattern = Pattern.compile(regex);

        Arrays.sort(files, (files1, files2) -> {
            Matcher m1 = pattern.matcher(files1);
            Matcher m2 = pattern.matcher(files2);

            if(m1.find() && m2.find()){
                String h1 = m1.group(1).toLowerCase(); // 대소문자 구분X
                String h2 = m2.group(1).toLowerCase();
                int head = h1.compareTo(h2);

                if(head!=0){
                    return head;
                }

                int n1 = Integer.parseInt(m1.group(2));
                int n2 = Integer.parseInt(m2.group(2));
                return Integer.compare(n1, n2); // number 기준 정렬

            }
            return 0; // 같은 파일명은 그대로 유지하기
        });
        return files;
    }
}