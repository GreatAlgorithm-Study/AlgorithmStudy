-- 문제: https://school.programmers.co.kr/learn/courses/30/lessons/276035
-- 개발자의 ID, 이메일, 이름, 성을 조회 + ID를 기준으로 오름차순 정렬
SELECT
    D.ID, D.EMAIL, D.FIRST_NAME,  D.LAST_NAME
FROM DEVELOPERS AS D
    INNER JOIN SKILLCODES AS S
    ON D.SKILL_CODE & S.CODE = S.CODE
WHERE S.CATEGORY = 'Front End'
GROUP BY D.ID, D.EMAIL, D.FIRST_NAME,  D.LAST_NAME
ORDER BY D.ID;

-- SELECT S.CATEGORY,  S.CODE
-- FROM SKILLCODES AS S
-- WHERE S.CATEGORY = 'Front End'

-- 비트 AND연산 (10진수 & 10진수)
-- 400 & 16 (110010000 & 10000)
-- 결과: 16 (10000)
-- SQL에서 비트연산자는 숫자를 2진수로 자동변환해서 연산하기 때문에 변환하지 않아도됨