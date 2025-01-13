# Write your MySQL query statement below
# 최소 5명의 직속 부하 직원이 있는 관리자를 찾기

SELECT a.name AS name
FROM Employee a
JOIN Employee b ON a.id = b.managerId
GROUP BY a.id, a.name
HAVING COUNT(b.id) >= 5;