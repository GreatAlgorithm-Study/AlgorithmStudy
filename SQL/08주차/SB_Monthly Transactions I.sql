# Write your MySQL query statement below
# 각 월 및 국가, 거래 수 및 총 금액, 승인된 거래 수 및 총 금액 찾기

SELECT 
    DATE_FORMAT(trans_date, '%Y-%m') AS month, 
    country,
    COUNT(*) AS trans_count,
    SUM(IF(state ='approved', 1, 0)) AS approved_count,
    SUM(amount) AS trans_total_amount,
    SUM(IF(state ='approved', amount, 0)) AS approved_total_amount
FROM Transactions
GROUP BY month, country