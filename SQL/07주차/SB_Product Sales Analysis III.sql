# Write your MySQL query statement below
# 판매된 모든 제품의 첫해에 대한 제품 ID, 연도, 수량 및 가격 출력

SELECT product_id, year AS first_year, quantity, price
FROM Sales
WHERE (product_id, year) IN (
    SELECT product_id, MIN(year) AS year
    FROM Sales
    GROUP BY product_id
)