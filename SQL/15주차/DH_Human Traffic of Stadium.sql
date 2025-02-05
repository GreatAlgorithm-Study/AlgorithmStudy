
WITH CTE AS (
    SELECT id
         , visit_date
         , (id - ROW_NUMBER() OVER()) `group_id`
         , people
    FROM STADIUM
    WHERE PEOPLE >= 100
)

SELECT id, visit_date, people
FROM cte
WHERE group_id in (
    SELECT group_id
    FROM cte
    GROUP BY(group_id)
    HAVING COUNT(group_id) >= 3
)