# Write your MySQL query statement below
-- 가장 많은 수의 영화를 평가한 사용자의 이름을 찾으세요. 동점일 경우, 사전학적으로 더 작은 사용자 이름을 반환하십시오.
-- 2020 2월 평균 평점이 가장 높은 영화 이름을 찾아라. 동점일 경우, 사전학적으로 더 작은 영화 이름을 반환하십시오.

(SELECT u.name AS results
FROM MovieRating m
JOIN Users u ON m.user_id = u.user_id
GROUP BY u.user_id
ORDER BY COUNT(u.user_id) DESC, u.name
LIMIT 1)
UNION ALL
(SELECT m.title AS results
FROM MovieRating r
JOIN Movies m ON r.movie_id = m.movie_id 
WHERE DATE_FORMAT(r.created_at, '%Y-%m') = "2020-02"
GROUP BY r.movie_id
ORDER BY AVG(r.rating) DESC, m.title
LIMIT 1)