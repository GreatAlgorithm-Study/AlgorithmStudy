# Write your MySQL query statement below
# 연속된 두 학생마다 좌석 ID를 바꾸는 해결책 작성
# 학생 수가 홀수인 경우 마지막 좌석을 변경할 필요 없음

SELECT
    CASE
        WHEN MOD(id, 2) = 1 AND id = (SELECT MAX(id) FROM Seat) THEN id
        WHEN MOD(id, 2) = 1 THEN id+1
        ELSE id-1
    END AS id,
    student
FROM Seat
ORDER BY id;
