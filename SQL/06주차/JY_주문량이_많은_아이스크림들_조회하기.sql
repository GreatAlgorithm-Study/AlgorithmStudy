-- 주문량이 많은 아이스크림들 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/133027

SELECT J.FLAVOR
FROM JULY J
LEFT JOIN FIRST_HALF F
ON J.FLAVOR = F.FLAVOR
GROUP BY J.FLAVOR
ORDER BY SUM(J.TOTAL_ORDER)+SUM(F.TOTAL_ORDER) DESC
LIMIT 3