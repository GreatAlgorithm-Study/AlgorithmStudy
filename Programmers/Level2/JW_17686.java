import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JW_17686 {

    public String[] solution(String[] files) {
        // 정규표현식
        String regex = "^([^0-9]+)([0-9]{1,5}).*$";
        Pattern pattern = Pattern.compile(regex);
        String[][] strArr = new String[files.length][3];
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i];
            Matcher matcher = pattern.matcher(fileName);
            // 정규표현식을 이용하여 그룹화
            if (matcher.find()) {
                String header = matcher.group(1).toLowerCase();
                String number = matcher.group(2);
                strArr[i] = new String[] { fileName, header, number };
            }
        }
        // 조건에 맞게 정렬
        Arrays.sort(strArr, (s1, s2) -> !s1[1].equals(s2[1]) ? s1[1].compareTo(s2[1])
                : Integer.parseInt(s1[2]) - Integer.parseInt(s2[2]));
        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            answer[i] = strArr[i][0];
        }
        return answer;
    }
}