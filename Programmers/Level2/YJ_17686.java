import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//정규식 문자열
public class YJ_17686 {
    static class File implements Comparable<File>{
        String head;
        String num;
        String tail;

        public File(String head, String num, String tail) {
            this.head = head;
            this.num = num;
            this.tail = tail;
        }

        @Override
        public int compareTo(File o) {
            //1. HEAD 사전순으로 정렬, 대소문자 구분X 2. NUMBER의 숫자 순으로 정렬
            int order = this.head.toLowerCase().compareTo(o.head.toLowerCase());
            return order == 0 ? Integer.parseInt(this.num) - Integer.parseInt(o.num) : order;
        }
    }

    public String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();
        //HEAD는 숫자가 아닌 문자로 이루어져 있으며, 최소한 한 글자 이상이다.
        //NUMBER는 한 글자에서 최대 다섯 글자 사이의 연속된 숫자 (0~9)
        final String regex = "([a-zA-Z\\s\\-]+)([0-9]{1,5})(.*)";

        for(String file : files){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(file);
            while(matcher.find()){
                //TAIL은 자가 다시 나타날 수도 있으며, 아무 글자도 없을 수 있다.
                if(matcher.group(3).isEmpty()){
                    fileList.add(new File(matcher.group(1),matcher.group(2),""));
                }else{
                    fileList.add(new File(matcher.group(1),matcher.group(2),matcher.group(3)));
                }
            }
        }
        Collections.sort(fileList);
        //파일명 조합
        String[] answer = new String[fileList.size()];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < fileList.size(); i++){
            answer[i] = sb.append(fileList.get(i).head)
                    .append(fileList.get(i).num)
                    .append(fileList.get(i).tail).toString();
            sb.setLength(0);
        }
        return answer;
    }
}
