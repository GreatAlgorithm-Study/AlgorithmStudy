-- https://school.programmers.co.kr/learn/courses/30/lessons/276034
-- Python이나 C# 스킬을 가진 개발자의  ID, 이메일, 이름, 성을 조회
SELECT
    d.ID, d.EMAIL, d.FIRST_NAME, d.LAST_NAME
FROM DEVELOPERS d
         INNER JOIN SKILLCODES s
                    ON d.SKILL_CODE & s.CODE > 0
WHERE s.NAME IN ('Python','C#')
GROUP BY d.ID, d.EMAIL, d.FIRST_NAME, d.LAST_NAME
ORDER BY d.ID;