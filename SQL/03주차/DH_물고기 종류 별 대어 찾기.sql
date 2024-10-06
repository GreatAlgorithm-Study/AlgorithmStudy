# https://school.programmers.co.kr/learn/courses/30/lessons/293261

# FISH_INFO 테이블과 FISH_NAME_INFO 테이블이 주어졌을 때,
# 종류별로 가장 큰 물고기의 정보 출력하기
# GROUP BY키로 지정된 컬럼 외 다른 컬럼을 출력하기 위해서는
# over 함수나 subquery 사용하기

SELECT A.ID, C.FISH_NAME, C.LENGTH
FROM FISH_INFO A JOIN (
    SELECT B.FISH_TYPE, B.FISH_NAME, MAX(A.LENGTH) `LENGTH`
    FROM FISH_INFO A JOIN FISH_NAME_INFO B ON A.FISH_TYPE = B.FISH_TYPE
    GROUP BY B.FISH_TYPE, B.FISH_NAME
) C ON A.FISH_TYPE = C.FISH_TYPE
WHERE A.LENGTH = C.LENGTH
ORDER BY A.ID