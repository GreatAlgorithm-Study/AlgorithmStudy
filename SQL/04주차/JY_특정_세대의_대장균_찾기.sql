-- 특정 세대의 대장균 찾기
-- https://school.programmers.co.kr/learn/courses/30/lessons/301650

-- K: 3세대, C: 2세대, P: 1세대
SELECT K.ID
FROM ECOLI_DATA P
JOIN ECOLI_DATA C
ON P.ID = C.PARENT_ID AND P.PARENT_ID IS NULL
JOIN ECOLI_DATA K
ON C.ID = K.PARENT_ID
ORDER BY K.ID