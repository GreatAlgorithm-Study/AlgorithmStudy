# https://school.programmers.co.kr/learn/courses/30/lessons/273710
# 두 테이블을 ITEM_ID를 기준으로 OUTER JOIN을 한 후
# PARENT_ITEM_ID가 NULL 값인 데이터 출력

SELECT A.ITEM_ID, B.ITEM_NAME
FROM ITEM_TREE A LEFT JOIN ITEM_INFO B
    ON A.ITEM_ID = B.ITEM_ID
WHERE A.PARENT_ITEM_ID IS NULL