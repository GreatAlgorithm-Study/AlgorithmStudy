-- https://school.programmers.co.kr/learn/courses/30/lessons/59412
-- 09:00부터 19:59까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회하는 SQL문을 작성
SELECT HOUR(DATETIME) AS HOUR, COUNT(ANIMAL_ID) AS COUNT
FROM ANIMAL_OUTS
GROUP BY HOUR(DATETIME)
HAVING HOUR >= 9 AND HOUR <= 19
ORDER BY HOUR