# Write your MySQL query statement below
-- 버스 무게 제한이 1000일때, 무게 제한을 초과하지 않고 버스에 들어갈 마지막 사람의 이름 구하기

WITH CTE AS (
    SELECT person_name,
    SUM(weight) OVER(ORDER BY turn) AS total_weight
    FROM queue
)
SELECT person_name
FROM CTE
WHERE total_weight <= 1000
ORDER BY total_weight DESC
LIMIT 1;