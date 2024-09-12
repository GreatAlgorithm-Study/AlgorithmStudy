-- 문제: https://school.programmers.co.kr/learn/courses/30/lessons/133027

-- FIRST_HALF 테이블 PK: FLAVOR, FK: SHIPMENT_ID
-- JULY 테이블 PK: SHIPMENT_ID FK: FLAVOR
-- 상위 3개 FLAVOR
-- 맛별로 (7월 아이스크림 총 주문량+상반기의 아이스크림 총 주문량)
SELECT
    JULY.FLAVOR
FROM JULY
         INNER JOIN
     (SELECT
          FIRST_HALF.FLAVOR
           ,FIRST_HALF.TOTAL_ORDER AS T_O
      FROM FIRST_HALF) AS FH
     ON JULY.FLAVOR = FH.FLAVOR
GROUP BY FLAVOR
ORDER BY SUM(JULY.TOTAL_ORDER+FH.T_O) DESC
LIMIT 3;