SELECT
    transaction_date
     , SUM(CASE WHEN MOD(amount, 2) = 0 THEN 0 ELSE amount END) AS odd_sum
     , SUM(CASE WHEN MOD(amount, 2) = 1 THEN 0 ELSE amount END) as even_sum
FROM TRANSACTIONS
GROUP BY transaction_date
ORDER BY transaction_date ASC