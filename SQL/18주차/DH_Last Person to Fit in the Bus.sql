WITH TOTAL_WEIGHT_TABLE AS (
    SELECT a.person_name
         , SUM(a.weight) OVER(ORDER BY a.turn) `tw`
    FROM QUEUE a
)
SELECT a.person_name
FROM TOTAL_WEIGHT_TABLE a
WHERE a.tw <= 1000
ORDER BY a.tw DESC
    LIMIT 1
