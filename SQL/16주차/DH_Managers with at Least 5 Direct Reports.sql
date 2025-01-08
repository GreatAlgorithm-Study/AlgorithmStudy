SELECT a.name
FROM EMPLOYEE a
         RIGHT JOIN EMPLOYEE b
                    ON a.id = b.managerId
GROUP BY  a.id
HAVING COUNT(a.id) >= 5