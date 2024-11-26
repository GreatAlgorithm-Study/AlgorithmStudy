WITH CTE AS (
    SELECT id, visit_date, people, rk, (id - rk) AS GROUP_ID
    FROM (
             SELECT id, visit_date, people, RANK() OVER (ORDER BY id) AS rk -- RANK()함수
             FROM Stadium
             WHERE people >= 100  -- 조건: people이 100 이상인 행만 선택
         ) AS t0
)
SELECT id, visit_date, people
FROM CTE
WHERE GROUP_ID IN (
    SELECT GROUP_ID
    FROM CTE
    GROUP BY GROUP_ID
    HAVING COUNT(*) >= 3
)
ORDER BY visit_date;