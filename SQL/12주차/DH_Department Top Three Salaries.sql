WITH CTE AS (
    SELECT ID,
           DENSE_RANK() OVER (PARTITION BY DEPARTMENTID ORDER BY SALARY DESC) GBR
    FROM EMPLOYEE
)

SELECT c.name `Department`, a.name `EmPloyee`, a.SALARY `Salary`
FROM EMPLOYEE a
         LEFT JOIN CTE b ON a.id = b.id
         LEFT JOIN DEPARTMENT c ON a.departmentId = c.id
WHERE b.GBR <= 3