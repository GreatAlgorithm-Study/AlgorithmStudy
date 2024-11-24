-- 코드를 작성해주세요
-- GRADE가 존재하는 개발자의 GRADE별 개발자의 정보를 조회

-- CTE 이용
WITH FE AS(
    SELECT SUM(CODE) AS CODE
    FROM SKILLCODES
    WHERE CATEGORY = "Front End"
)
, Python AS(
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = "Python"
)
, C AS(
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = "C#"
)
, GRADES AS(
    SELECT
        CASE 
            WHEN d.SKILL_CODE & f.CODE !=0 AND d.SKILL_CODE & p.CODE !=0 THEN 'A'
            WHEN d.SKILL_CODE & c.CODE != 0 THEN 'B'
            WHEN d.SKILL_CODE & f.CODE != 0 THEN 'C'
        END AS GRADE
        , d.ID, d.EMAIL
    FROM DEVELOPERS d, FE f, Python p, C c
)
SELECT *
FROM GRADES g
WHERE GRADE IS NOT NULL
ORDER BY g.GRADE, g.ID

-- 각 CTE를 테이블 처럼 암시적 조인으로 사용
-- 각 개발자마다 CTE반복 참조하며 CASE WHEN조건 매번 계산



-- 최적화
WITH FE AS (
    SELECT SUM(CODE) AS CODE
    FROM SKILLCODES
    WHERE CATEGORY = "Front End"
),
SKILLS AS (
    SELECT 
        MAX(CASE WHEN NAME = "C#" THEN CODE END) AS C_CODE,
        MAX(CASE WHEN NAME = "Python" THEN CODE END) AS P_CODE,
        (SELECT CODE FROM FE) AS FE_CODE
    FROM SKILLCODES
),
DEVELOPER_FLAGS AS (
    SELECT
        d.ID,
        d.EMAIL,
        d.SKILL_CODE,
        (d.SKILL_CODE & s.FE_CODE) AS IS_FE,
        (d.SKILL_CODE & s.P_CODE) AS IS_PYTHON,
        (d.SKILL_CODE & s.C_CODE) AS IS_C
    FROM DEVELOPERS d
    CROSS JOIN SKILLS s
),
GRADES AS (
    SELECT
        CASE 
            WHEN IS_FE != 0 AND IS_PYTHON != 0 THEN 'A'
            WHEN IS_C != 0 THEN 'B'
            WHEN IS_FE != 0 THEN 'C'
        END AS GRADE,
        ID,
        EMAIL
    FROM DEVELOPER_FLAGS
)
SELECT *
FROM GRADES
WHERE GRADE IS NOT NULL
ORDER BY GRADE, ID;
