WITH CTE AS (
    SELECT
        product_id
        , new_price
    FROM
        products
    WHERE
        (product_id, change_date) IN 
        (
            SELECT
                product_id
                , MAX(change_date)
            FROM
                Products
            WHERE
                change_date <= '2019-08-16'
            GROUP BY
                product_id
        )
)

SELECT
    DISTINCT(A.product_id)
    , IFNULL(B.new_price, 10) `price`
FROM
    products A
    LEFT JOIN CTE B
        ON A.product_id = B.product_id