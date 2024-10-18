# 최소 5명의 직속 부하 직원이 있는 관리자를 찾기

SELECT e.name
FROM Employee e
JOIN Employee m ON e.id = m.managerId
GROUP BY m.managerId
HAVING COUNT(m.managerId) >=5;