-- 주문량이 많은 아이스크림들 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/133027

-- 상반기 총 주문량과 7월 총 주문량의 합을 기준으로 정렬
SELECT J.FLAVOR
FROM JULY J
LEFT JOIN FIRST_HALF F
ON J.SHIPMENT_ID = F.SHIPMENT_ID
GROUP BY J.FLAVOR
ORDER BY SUM(J.TOTAL_ORDER)+F.TOTAL_ORDER DESC
LIMIT 3