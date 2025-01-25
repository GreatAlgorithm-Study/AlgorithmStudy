SELECT a.product_id, b.product_name
FROM SALES a LEFT JOIN product b
                       ON a.product_id = b.product_id
GROUP BY a.product_id
having MIN(QUARTER(sale_date)) = 1
   and MAX(QUARTER(sale_date)) = 1
   and MIN(YEAR(sale_date)) = 2019
   and MAX(YEAR(sale_date)) = 2019