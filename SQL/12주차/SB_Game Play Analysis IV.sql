# Write your MySQL query statement below
# 처음 로그인한 다음 날 다시 로그인한 플레이어의 비율을 소수점 2자리로 반올림하여 보고
# 첫 로그인 날짜부터 최소 이틀 연속으로 로그인한 플레이어 수를 계산한 다음 그 숫자를 총 플레이어 수로 나누어야 함

WITH First_Login AS(
    SELECT player_id,
            MIN(event_date) AS first_date
    FROM Activity
    GROUP BY player_id
)
SELECT ROUND(
        COUNT(DISTINCT(a.player_id))*1.0/COUNT(DISTINCT(f.player_id)), 2
        ) AS fraction
FROM First_Login f
LEFT JOIN Activity a
    ON f.player_id = a.player_id
    AND DATEDIFF(a.event_date, f.first_date)=1;