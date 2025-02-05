SELECT visited_on, amount, ROUND(amount / 7, 2) `average_amount`
FROM (
         SELECT DISTINCT visited_on
                       , SUM(amount) OVER(ORDER BY visited_on RANGE BETWEEN INTERVAL 6 DAY PRECEDING AND CURRENT ROW) `amount`
         , MIN(visited_on) OVER() min_date
         FROM CUSTOMER
     ) a
WHERE visited_on >= min_date + 6
