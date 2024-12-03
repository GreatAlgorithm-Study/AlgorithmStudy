WITH NOT_BANNED AS(
    SELECT users_id
    FROM Users
    WHERE banned = 'No'
)
SELECT
    request_at Day,
    ROUND(
        SUM(CASE
                WHEN status LIKE 'cancelled_by%' THEN 1
                ELSE 0
            END) / COUNT(id)
    ,2) 'Cancellation Rate'
FROM Trips t
WHERE t.client_id IN (SELECT users_id FROM NOT_BANNED)
    AND t.driver_id IN (SELECT users_id FROM NOT_BANNED)
    AND request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY request_at