-- https://school.programmers.co.kr/learn/courses/30/lessons/301649
-- 대장균 개체의 크기에 대한 순위
-- 상위 0% ~ 25%:'CRITICAL', 26% ~ 50%:'HIGH', 51% ~ 75%:'MEDIUM', 76% ~ 100%:'LOW' 분류
WITH ECOLI_PERCENT AS (
    SELECT
        ID,
        SIZE_OF_COLONY,
        PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC)*100 AS C_PERCENT
    FROM ECOLI_DATA
)
SELECT
    ID,
    CASE
        WHEN C_PERCENT <= 25 THEN 'CRITICAL'
        WHEN C_PERCENT <= 50 THEN 'HIGH'
        WHEN C_PERCENT <= 75 THEN 'MEDIUM'
        ELSE 'LOW'
        END AS COLONY_NAME
FROM ECOLI_PERCENT
ORDER BY ID;