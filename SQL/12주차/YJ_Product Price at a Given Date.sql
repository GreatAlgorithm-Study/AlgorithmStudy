SELECT
    DISTINCT p.product_id,
    CASE
        WHEN distinct_p.product_id IS NULL THEN 10
        ELSE p.new_price
    END AS price
FROM Products p
LEFT JOIN (
    SELECT product_id, MAX(change_date) change_date
    FROM Products
    WHERE change_date < '2019-08-17'
    GROUP BY product_id
) sub
ON p.product_id = sub.product_id AND p.change_date = sub.change_date
LEFT JOIN (
    SELECT DISTINCT product_id
    FROM Products
    WHERE change_date < '2019-08-17'
) distinct_p
ON p.product_id = distinct_p.product_id
WHERE sub.change_date IS NOT NULL OR distinct_p.product_id IS NULL