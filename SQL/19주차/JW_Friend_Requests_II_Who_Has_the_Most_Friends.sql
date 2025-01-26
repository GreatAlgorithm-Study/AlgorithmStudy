WITH TOTAL AS (
    SELECT
        requester_id `id`
    FROM
        RequestAccepted
    UNION ALL
    SELECT
        accepter_id `id`
    FROM
        RequestAccepted
)

SELECT
    id
    , COUNT(id) as num    
FROM 
    TOTAL
GROUP BY
    id
ORDER BY
    COUNT(id) DESC
LIMIT 1
