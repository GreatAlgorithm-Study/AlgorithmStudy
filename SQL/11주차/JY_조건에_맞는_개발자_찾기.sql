-- 조건에 맞는 개발자 찾기
-- https://school.programmers.co.kr/learn/courses/30/lessons/276034
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS
WHERE SKILL_CODE & (SELECT SUM(CODE) FROM SKILLCODES WHERE NAME="Python" OR NAME="C#")
ORDER BY ID