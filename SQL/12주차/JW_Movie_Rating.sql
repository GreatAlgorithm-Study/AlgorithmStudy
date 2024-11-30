(SELECT
    B.name results
FROM
    MovieRating A
    JOIN Users B
        ON A.user_id = B.user_id
GROUP BY
    A.user_id
ORDER BY
    COUNT(A.user_id) DESC
    , B.name
LIMIT 1)

UNION ALL

(SELECT
    B.title
FROM
    MovieRating A
    JOIN Movies B
        ON A.movie_id = B.movie_id
WHERE
    A.created_at LIKE '2020-02-%'
GROUP BY
    A.movie_id
ORDER BY
    AVG(A.rating) DESC
    , B.title 
LIMIT 1)