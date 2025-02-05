SELECT
    B.name `Department`
    , A.name `Employee`
    , A.salary `Salary`
FROM
    Employee A
    INNER JOIN Department B 
        ON A.departmentId = B.id
WHERE
    (
        SELECT COUNT(DISTINCT salary)
        FROM Employee C
        WHERE departmentId = A.departmentId AND salary >= A.salary
    ) <= 3
ORDER BY
    Department
    , Salary DESC