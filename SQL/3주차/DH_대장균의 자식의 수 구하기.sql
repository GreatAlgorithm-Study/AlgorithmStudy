# https://school.programmers.co.kr/learn/courses/30/lessons/299305
# 대장균의 자식의 수 구하기
# 한 테이블의 균의 ID, 부모의 ID가 있을 떄, 각 ID에 대해서 자식이 몇 마리 있는지 출력
SELECT A.ID, IFNULL(B.COUNT, 0) `CHILD_COUNT`
FROM ECOLI_DATA A LEFT JOIN (
    # SELF JOIN을 해서
    SELECT A.ID, COUNT(*) `COUNT`
    FROM ECOLI_DATA A JOIN ECOLI_DATA B ON A.ID = B.PARENT_ID
    GROUP BY A.ID
) B ON A.ID = B.ID

# COUNT(*): NULL을 포함하는지 여부에 관계 없이 개수를 셈
# COUNT(컬럼): NULL 값 제외해서 개수 셈
SELECT A.ID, COUNT(B.ID) `CHILD_COUNT`
FROM ECOLI_DATA A LEFT JOIN ECOLI_DATA B ON A.ID = B.PARENT_ID
GROUP BY A.ID
