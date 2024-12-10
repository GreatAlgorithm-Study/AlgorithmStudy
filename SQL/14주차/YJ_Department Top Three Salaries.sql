SELECT
    d.name Department, er.name Employee, salary
FROM (
SELECT
    departmentId, name, salary,
    DENSE_RANK() OVER(PARTITION BY departmentId ORDER BY salary DESC) 'rank'
    FROM Employee
    GROUP BY departmentId, name, salary
    ORDER BY departmentId
) er
INNER JOIN Department d
ON er.departmentId = d.id
WHERE er.rank <= 3;