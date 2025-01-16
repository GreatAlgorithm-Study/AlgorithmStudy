-- 1. 가장 많은 영화를 평가한 사용자의 이름 찾기 (동점인 경우, 사전 순으로 더 작은 사용자 이름을 반환)
-- 2. 2020년 2월에 평균 평점이 가장 높은 영화 이름 (동점인 경우, 사전 순으로 더 작은 영화 이름 반환)

(SELECT name AS results
FROM Users a
JOIN MovieRating b ON a.user_id = b.user_id
GROUP BY name
ORDER BY COUNT(*) DESC, name
LIMIT 1)

UNION ALL

(SELECT title AS results
FROM MovieRating b
JOIN Movies c ON b.movie_id = c.movie_id
WHERE YEAR(b.created_at) = 2020 AND MONTH(b.created_at) = 2
GROUP BY title
ORDER BY AVG(rating) DESC, title
LIMIT 1);

-- ORDER BY rating DESC 로 했어서 틀렸음
-- AVG(rating) 평균 평점이 가장 높은 영화
-- 문제 잘 읽기