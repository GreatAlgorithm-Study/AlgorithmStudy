SELECT MAX(Salary) as SecondHighestSalary
FROM (SELECT salary, dense_rank() over(order by salary desc) as rk
      FROM Employee
     ) Employee
where rk = 2;