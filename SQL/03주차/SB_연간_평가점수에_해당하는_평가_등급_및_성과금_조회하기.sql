-- 사원별 평가 등급, 성과금 정보 조회

-- 등급 구하기 >> HR_GRADE에서 1,2분기 점수 합 구하기
-- 성과금 구하기 >> 위에서 구한 등급에 따른 성과금 기준으로, 연봉에서 성과금 계산

WITH EMP_SC AS(
    SELECT EMP_NO, AVG(SCORE) AS TOTAL
    FROM HR_GRADE
    GROUP BY EMP_NO
)

SELECT e.EMP_NO, e.EMP_NAME,
       CASE
            WHEN s.TOTAL >= 96 THEN 'S'
            WHEN s.TOTAL >= 90 THEN 'A'
            WHEN s.TOTAL >= 80 THEN 'B'
            ELSE "C"
        END AS GRADE,
        CASE
            WHEN s.TOTAL >= 96 THEN e.SAL*0.2
            WHEN s.TOTAL >= 90 THEN e.SAL*0.15
            WHEN s.TOTAL >= 80 THEN e.SAL*0.1
            ELSE 0
        END AS BONUS
FROM HR_EMPLOYEES e
JOIN EMP_SC s ON e.EMP_NO = s.EMP_NO
ORDER BY e.EMP_NO;
