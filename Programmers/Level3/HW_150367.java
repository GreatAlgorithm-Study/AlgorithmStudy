import java.util.*;

class HW_150367 {
    public int[] solution(long[] numbers) {
        int[] result = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]); // 숫자를 이진수로 변환
            String fullBinary = extend(binary); // 포화 이진트리로 확장
            result[i] = isValidTree(fullBinary) ? 1 : 0; // 트리 검증
        }
        return result;
    }

    // 이진수를 포화 이진트리 형태로 확장
    private String extend(String binary) {
        int length = binary.length();
        int height = 0;

        // 포화 이진트리의 노드 개수 계산
        while ((1 << height) - 1 < length) {
            height++;
        }

        int fullLength = (1 << height) - 1; // 포화 이진트리 노드 개수
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fullLength - length; i++) {
            sb.append("0"); // 앞쪽에 더미 노드 추가
        }
        sb.append(binary);
        return sb.toString();
    }

    // 트리 검증
    private boolean isValidTree(String binary) {
        return isValidSubtree(binary, 0, binary.length() - 1);
    }

    // 서브트리 검증
    private boolean isValidSubtree(String binary, int start, int end) {
        if (start > end) { // 범위를 벗어나면 유효함
            return true;
        }

        int mid = (start + end) / 2; // 현재 루트 노드
        char root = binary.charAt(mid);

        // 왼쪽과 오른쪽 서브트리
        boolean leftValid = isValidSubtree(binary, start, mid - 1);
        boolean rightValid = isValidSubtree(binary, mid + 1, end);

        // 루트가 0인데 자식이 1인 경우 규칙 위반
        if (root == '0') {
            for (int i = start; i <= end; i++) {
                if (binary.charAt(i) == '1') {
                    return false;
                }
            }
        }
        return leftValid && rightValid; // 왼쪽, 오른쪽 서브트리 모두 유효 -> 전체 트리 유효
    }
}