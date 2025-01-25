-- 부서 상위 3개 급여 구하는 문제
-- 저번에는 DENSE_RANK로 풀었어서 이번에는 서브쿼리로 풀기
-- 서브쿼리로 부서별 상위 3명을 추출
SELECT b.name AS Department, a.name AS Employee, a.salary AS Salary
FROM EMPLOYEE a
         JOIN DEPARTMENT b
              ON a.departmentId = b.id
WHERE 3 >
      (
          SELECT COUNT(DISTINCT c.salary)
          FROM EMPLOYEE c
          WHERE c.salary > a.salary AND a.departmentId = c.departmentId
      )
