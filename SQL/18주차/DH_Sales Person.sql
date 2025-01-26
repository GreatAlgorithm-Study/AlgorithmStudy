SELECT DISTINCT(a.name)
FROM SALESPERSON a
         LEFT JOIN ORDERS b ON a.sales_id = b.sales_id
WHERE a.sales_id not in (
    SELECT a.sales_id
    FROM ORDERS a
             JOIN COMPANY b ON a.com_id = b.com_id
    WHERE b.name = "RED"
)