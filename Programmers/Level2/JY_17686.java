import java.util.*;

class Solution {
    
    static class File implements Comparable<File> {
        String name, head, tail;
        int number, idx;
        public File(String name, String head, int number, String tail, int idx) {
            this.name = name;
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(File other) {
            if(this.head.equals(other.head)) {
                if(this.number == other.number) {
                    // 3) 입력 순(인덱스)
                    return this.idx - other.idx;
                }
                // 2) 숫자 순
                return this.number - other.number;
            }
            // 1) head 사전순
            return this.head.compareTo(other.head);
        }
    }
    
    public String[] solution(String[] files) {        
        List<File> fList = new ArrayList<>();
        for(int n=0; n <files.length; n++) {
            String file = files[n];
            StringBuilder head = new StringBuilder();
            StringBuilder number = new StringBuilder();
            StringBuilder tail = new StringBuilder();
            boolean isHead = false;
            boolean isNumber = false;
            for(int i=0; i<file.length(); i++) {
                char c = file.charAt(i);
                if(isHead && isNumber) {
                    tail.append(c);
                    continue;
                }
                if(Character.isDigit(c)) {
                    isHead = true;
                    if(number.length() == 5) {
                        isNumber = true;
                        tail.append(c);
                        continue;
                    }
                    number.append(c);
                }  else if(isHead) {
                    isNumber = true;
                    tail.append(c);
                } else{
                    head.append(c);
                }
            }
            
            // File 객체 생성
            fList.add(new File(file, head.toString().toUpperCase(), Integer.parseInt(number.toString()), tail.toString(), n));
        }
        
        Collections.sort(fList);
        String[] answer = new String[fList.size()];
        for(int i=0; i<fList.size(); i++) {
            File f = fList.get(i);
            answer[i] = f.name;
        }
        
        return answer;
    }
}