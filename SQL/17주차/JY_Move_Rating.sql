-- 1341. Movie Rating
-- https://leetcode.com/problems/movie-rating/
(SELECT name AS results
FROM MovieRating a 
JOIN Users b 
ON a.user_id = b.user_id
GROUP BY name
ORDER BY COUNT(*) DESC, name
LIMIT 1)

UNION ALL

(SELECT title AS results
FROM MovieRating a
JOIN Movies b
ON a.move_id = b.move_id
WHERE created_at like "2020-02%"
GROUP BY title
ORDER BY AVG(rating) DESC, title
LIMIT 1);
