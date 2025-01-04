CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
SET N = N-1;
  RETURN (
      # Write your MySQL query statement below.
      # Employee 테이블에서 n번째로 큰 급여를 반환하는 query를 작성
      # 만약에 n번째로 큰 급여 정보가 없다면, NULL을 반환
      SELECT DISTINCT salary
      FROM Employee
      ORDER BY salary DESC
      LIMIT 1 OFFSET N
  );
END