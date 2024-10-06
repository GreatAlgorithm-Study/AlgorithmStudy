-- https://school.programmers.co.kr/learn/courses/30/lessons/131537
-- 2022년 3월의 오프라인/온라인 상품 판매 데이터의 판매 날짜, 상품ID, 유저ID, 판매량을 출력하는 SQL문을 작성
SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE,
       PRODUCT_ID, USER_ID, SALES_AMOUNT
FROM (
         SELECT SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
         FROM ONLINE_SALE
         WHERE DATE_FORMAT(SALES_DATE, '%Y-%m') = '2022-03'

         UNION ALL

         SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE,
                PRODUCT_ID, NULL AS USER_ID, SALES_AMOUNT
         FROM OFFLINE_SALE
         WHERE DATE_FORMAT(SALES_DATE, '%Y-%m') = '2022-03'
     ) AS SALES_DATA
ORDER BY SALES_DATE ASC, PRODUCT_ID ASC, USER_ID ASC; -- 판매일 (오름차순),상품기준 오름차순, 유저 id오름차순