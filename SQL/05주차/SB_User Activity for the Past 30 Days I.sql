# Write your MySQL query statement below
# 2019-07-27까지 30 동안 일일 활성 사용자 수를 찾기

SELECT activity_date AS day,
       COUNT(DISTINCT user_id) AS active_users
FROM Activity
WHERE activity_date BETWEEN '2019-06-28' AND '2019-07-27'
GROUP BY activity_date;