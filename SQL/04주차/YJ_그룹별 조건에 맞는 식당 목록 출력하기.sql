-- https://school.programmers.co.kr/learn/courses/30/lessons/131124
-- MEMBER_PROFILE, REST_REVIEW 테이블에서 리뷰를 가장 많이 작성한 회원의 리뷰
-- 회원 이름, 리뷰 텍스트, 리뷰 작성일
-- 리뷰 작성일, 리뷰 텍스트 오름차순 정렬
SELECT
    mp.MEMBER_NAME, rr.REVIEW_TEXT, DATE_FORMAT(rr.REVIEW_DATE,'%Y-%m-%d') REVIEW_DATE
FROM REST_REVIEW rr
INNER JOIN MEMBER_PROFILE mp
ON rr.MEMBER_ID = mp.MEMBER_ID
WHERE mp.MEMBER_ID IN (
    SELECT MEMBER_ID
    FROM REST_REVIEW
    GROUP BY MEMBER_ID
    HAVING COUNT(*)=(SELECT MAX(rc.REVIEW_COUNT) MAX_REVIEW
                     FROM (
                          SELECT
                              COUNT(MEMBER_ID) REVIEW_COUNT
                          FROM REST_REVIEW
                          GROUP BY MEMBER_ID
                          ) rc)
    )
ORDER BY rr.REVIEW_DATE, rr.REVIEW_TEXT;

-- 리뷰가 가장 많은 회원명단
SELECT MEMBER_ID
FROM REST_REVIEW
GROUP BY MEMBER_ID
HAVING COUNT(*)=(SELECT MAX(rc.REVIEW_COUNT) MAX_REVIEW
                 FROM (
                      SELECT
                          COUNT(MEMBER_ID) REVIEW_COUNT
                      FROM REST_REVIEW
                      GROUP BY MEMBER_ID
                      ) rc);
