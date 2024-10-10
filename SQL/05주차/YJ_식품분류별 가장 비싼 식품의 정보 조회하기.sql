--  식품분류가 '과자', '국', '김치', '식용유'인 경우만 출력
--  식품 가격을 기준으로 내림차순 정렬
SELECT
    fp.CATEGORY, fp.PRICE, fp.PRODUCT_NAME
FROM FOOD_PRODUCT fp
         JOIN
     (SELECT CATEGORY, MAX(PRICE) MAX_PRICE
      FROM FOOD_PRODUCT
      WHERE CATEGORY IN ('과자','국','김치','식용유')
      GROUP BY CATEGORY) AS sub
     ON fp.CATEGORY = sub.CATEGORY AND fp.PRICE = sub.MAX_PRICE
ORDER BY fp.PRICE DESC;
