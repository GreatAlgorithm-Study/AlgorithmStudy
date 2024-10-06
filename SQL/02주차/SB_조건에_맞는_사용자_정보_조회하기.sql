-- [조건에 맞는 사용자 정보 조회하기](https://school.programmers.co.kr/learn/courses/30/lessons/164670)
-- 게시글 3건 이상 등록, id기준 내림차순

SELECT 
    u.USER_ID, u.NICKNAME,
    CONCAT(u.CITY, ' ', u.STREET_ADDRESS1, ' ', u.STREET_ADDRESS2) AS 전체주소,
    CONCAT_WS('-', SUBSTR(u.TLNO, 1,3), SUBSTR(u.TLNO, 4,4), SUBSTR(u.TLNO,8,4)) AS 전화번호
FROM
    USED_GOODS_BOARD b
JOIN USED_GOODS_USER u ON b.WRITER_ID = u.USER_ID
GROUP BY b.WRITER_ID
HAVING COUNT(b.WRITER_ID) >= 3
ORDER BY u.USER_ID DESC;