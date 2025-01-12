# AMOUNT OVER SUM TABLE
WITH AOST AS (
    SELECT
        visited_on,
        SUM(amount) OVER(ORDER BY visited_on RANGE BETWEEN INTERVAL 6 DAY PRECEDING AND CURRENT ROW) `amount`,
        MIN(visited_on) OVER() min_date
    FROM CUSTOMER
)

SELECT
    DISTINCT(visited_on)
           , amount
           , round(amount / 7, 2) `average_amount`
FROM AOST
WHERE min_date + 6 <= visited_on