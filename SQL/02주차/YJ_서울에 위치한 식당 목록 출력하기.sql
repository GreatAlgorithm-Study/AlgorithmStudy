-- 서울에 위치한 식당들의 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수를 조회
-- 1.리뷰 평균점수는 소수점 세 번째 자리에서 반올림
-- 2.평균점수를 기준으로 내림차순 정렬
-- 3.평균점수가 같다면 즐겨찾기수를 기준으로 내림차순 정렬
SELECT
    ri.REST_ID, ri.REST_NAME, ri.FOOD_TYPE, ri.FAVORITES, ri.ADDRESS
     ,ROUND(AVG(rr.REVIEW_SCORE),2) AS SCORE
FROM REST_INFO ri
INNER JOIN REST_REVIEW rr
ON ri.REST_ID = rr.REST_ID
WHERE ri.ADDRESS LIKE '서울%'
GROUP BY ri.REST_ID, ri.REST_NAME, ri.FOOD_TYPE, ri.FAVORITES, ri.ADDRESS
ORDER BY SCORE DESC, ri.FAVORITES DESC;