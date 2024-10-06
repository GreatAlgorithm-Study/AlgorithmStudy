-- 우유와 요거트를 동시에 구입한 장바구니 아이디 조회
-- having절엔 집계함수만 가능
-- ALL 연산자는 대소비교 가능한 것만 가능(서브쿼리, 값)

SELECT CART_ID
FROM CART_PRODUCTS
WHERE NAME IN ('Milk', 'Yogurt')
GROUP BY CART_ID
HAVING COUNT(DISTINCT NAME) >=2
ORDER BY CART_ID;

-- 조인을 이용한 다른 방법
SELECT DISTINCT C1.CART_ID
FROM CART_PRODUCTS C1
JOIN CART_PRODUCTS C2 ON C1.CART_ID = C2.CART_ID
WHERE C1.NAME = 'Milk' AND C2.NAME = 'Yogurt'