-- 그룹별 조건에 맞는 식당 목록 출력하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/131124

WITH BEST_REVIEWER AS (
    SELECT MEMBER_ID
    FROM REST_REVIEW
    GROUP BY MEMBER_ID
    ORDER BY COUNT(MEMBER_ID) DESC
    LIMIT 1
)

SELECT M.MEMBER_NAME, R.REVIEW_TEXT
    , DATE_FORMAT(REVIEW_DATE, "%Y-%m-%d") AS REVIEW_DATE
FROM MEMBER_PROFILE M
JOIN REST_REVIEW R
ON M.MEMBER_ID = R.MEMBER_ID
WHERE M.MEMBER_ID = (SELECT * FROM BEST_REVIEWER)
ORDER BY R.REVIEW_DATE, R.REVIEW_TEXT