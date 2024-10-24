# 점수의 순위 찾기
# 동점은 같은 순위, 이후 값은 연속된 수

# RANK(), OVER 이용한 방법
SELECT score, DENSE_RANK() OVER (ORDER BY score DESC) AS "RANK"
FROM Scores


# 서브테이블, GROUP BY 이용 
SELECT S.score, COUNT(S2.score) AS "RANK"
FROM Scores S,
(SELECT DISTINCT score FROM Scores) S2
WHERE S.score <= S2.score
GROUP BY S.id
ORDER BY S.score DESC;