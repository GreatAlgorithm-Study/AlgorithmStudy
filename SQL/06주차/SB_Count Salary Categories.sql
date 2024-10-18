# 각 급여 범주에 대한 은행 계좌 수를 계산
# 결과표에는 세 가지 범주가 모두 포함되어야 합니다. 카테고리에 계정이 없으면 0 반환

SELECT 'High Salary' AS category,
    SUM(income > 50000) AS accounts_count
FROM Accounts
UNION
SELECT 'Average Salary' AS category,
    SUM(income BETWEEN 20000 AND 50000) AS accounts_count
FROM Accounts
UNION
SELECT 'Low Salary' AS category,
    SUM(income < 20000) AS accounts_count
FROM Accounts