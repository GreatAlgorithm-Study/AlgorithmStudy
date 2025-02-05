-- 두번째로 큰 salary 값 출력하기, 없으면 null 출력
SELECT IFNULL(
               (SELECT DISTINCT salary
                FROM Employee
                ORDER BY salary DESC
                   LIMIT 1 OFFSET 1), null)
as SecondHighestSalary;