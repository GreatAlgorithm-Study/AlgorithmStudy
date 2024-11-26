SELECT t.department, t.employee, t.salary
FROM (
         SELECT d.name AS department,
                e.name AS employee,
                e.salary AS Salary,
                DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS dr
         FROM employee e
                  JOIN Department d ON e.departmentId=d.id
     ) t
WHERE t.dr<=3;