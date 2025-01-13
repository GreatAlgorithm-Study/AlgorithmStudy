SELECT DISTINCT a.num AS COnsecutiveNums
FROM LOGS a
         JOIN LOGS b ON a.id = b.id -1
         JOIN LOGS c ON b.id = c.id-1
WHERE a.num = b.num AND b.num = c.num;