SELECT
    person_name
FROM (
    SELECT
     person_name, SUM(Weight) OVER(ORDER BY Turn) Total_Weight
    FROM Queue
) sub
WHERE Total_Weight <= 1000
ORDER BY Total_Weight DESC
LIMIT 1;