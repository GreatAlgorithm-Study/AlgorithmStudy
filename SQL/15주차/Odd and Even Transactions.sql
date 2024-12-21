WITH EVEN AS (
    SELECT TRANSACTION_DATE, SUM(AMOUNT) `amount`
    FROM TRANSACTIONS
    WHERE MOD(AMOUNT, 2) = 0
    GROUP BY TRANSACTION_DATE
), ODD AS (
    SELECT TRANSACTION_DATE, SUM(AMOUNT) `amount`
    FROM TRANSACTIONS
    WHERE MOD(AMOUNT, 2) = 1
    GROUP BY TRANSACTION_DATE
)

SELECT DISTINCT(a.TRANSACTION_DATE) `transaction_date`
              , IFNULL(c.AMOUNT, 0) `odd_sum`
              , IFNULL(b.AMOUNT, 0) `even_sum`
FROM TRANSACTIONS a
         LEFT JOIN EVEN b ON a.transaction_date = b.transaction_date
         LEFT JOIN ODD c ON a.transaction_date = c.transaction_date
ORDER BY transaction_date

------------------------------------------

SELECT transaction_date
     , SUM(CASE WHEN MOD(AMOUNT, 2) = 1 THEN AMOUNT ELSE 0 END) `odd_sum`
     , SUM(CASE WHEN MOD(AMOUNT, 2) = 0 THEN AMOUNT ELSE 0 END) `even_sum`
FROM TRANSACTIONS
GROUP BY TRANSACTION_DATE
ORDER BY TRANSACTION_DATE