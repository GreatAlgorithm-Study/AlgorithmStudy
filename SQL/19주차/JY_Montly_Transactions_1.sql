-- 1193. Monthly Transactions I
-- https://leetcode.com/problems/monthly-transactions-i/
SELECT DATE_FORMAT(trans_date, "%Y-%m") AS month, country
        , COUNT(*) AS trans_count
        , COUNT(IF(state = 'approved', state, NULL)) AS approved_count
        , SUM(amount) AS trans_total_amount
        , SUM(IF(state = 'approved', amount, 0)) AS approved_total_amount
FROM TRANSACTIONS
GROUP BY YEAR(trans_date), MONTH(trans_date), country