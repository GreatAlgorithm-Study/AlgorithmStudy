-- https://school.programmers.co.kr/learn/courses/30/lessons/59413
-- 0시부터 23시까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회
WITH RECURSIVE time_h AS (
    SELECT 0 AS HOUR_DATA
    UNION ALL
    SELECT HOUR_DATA+1
    FROM time_h
    WHERE HOUR_DATA < 23
)
SELECT
    th.HOUR_DATA AS HOUR
     ,COUNT(DISTINCT ao.ANIMAL_ID) AS COUNT
FROM time_h th
         LEFT JOIN ANIMAL_OUTS ao
                   ON th.HOUR_DATA = HOUR(ao.DATETIME)
GROUP BY th.HOUR_DATA
ORDER BY th.HOUR_DATA;