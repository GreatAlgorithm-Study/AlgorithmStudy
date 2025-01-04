# Write your MySQL query statement below
# 사용자의 확인율은 '확인'된 메시지의 수를 요청된 확인 메시지의 총 수로 나눈 값입니다. 확인 메시지를 요청하지 않은 사용자의 확인율은 0입니다. 확인율을 소수점 두 자리로 반올림합니다.

SELECT s.user_id,
        ROUND(IFNULL(AVG(c.action="confirmed"),0),2) AS confirmation_rate
FROM Signups s
LEFT JOIN Confirmations c ON s.user_id = c.user_id
GROUP BY s.user_id