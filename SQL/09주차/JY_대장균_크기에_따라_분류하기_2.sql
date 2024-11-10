-- 대장균의 크기에 따라 분류하기 2
-- https://school.programmers.co.kr/learn/courses/30/lessons/301649
SELECT ID,
       (CASE 
            WHEN (ROWNUM / CNT) <= 0.25 THEN "CRITICAL"
            WHEN (ROWNUM / CNT) <= 0.5 THEN "HIGH"
            WHEN (ROWNUM / CNT) <= 0.75 THEN "MEDIUM"
            ELSE "LOW"
        END) AS COLONY_NAME
FROM (SELECT ID, SIZE_OF_COLONY,
       ROW_NUMBER() OVER(ORDER BY SIZE_OF_COLONY DESC) AS ROWNUM
       FROM ECOLI_DATA) R,
     (SELECT COUNT(*) AS CNT FROM ECOLI_DATA) C
ORDER BY ID