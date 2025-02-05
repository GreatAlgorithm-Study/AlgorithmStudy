-- 상품 번호, 가격, 가격이 변경된 날짜가 저장된 PRODUCTS 테이블이 있을 때, 2019년 08월 16일까지 상품 가격이 변경된 상품이 있다면 해당 상품 정보(상품 id, 가격) 출력하기
-- 만약 2019년 08월 16일 이전까지 상품 가격에 대한 정보가 없다면 10 출력

WITH PRE AS (
    SELECT product_id, new_price, change_date
    FROM Products
    WHERE (product_id ,change_date) IN (
        SELECT product_id, MAX(change_date)
        FROM Products
        WHERE change_date <= "2019-08-16"
        GROUP BY product_id
    )
)
SELECT DISTINCT(a.product_id), IFNULL(b.new_price, 10) AS price
FROM Products a
LEFT JOIN PRE b ON a.product_id = b.product_id
-- GROUP BY product_id