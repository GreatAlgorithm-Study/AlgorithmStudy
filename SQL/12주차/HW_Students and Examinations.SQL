--  각 학생이 각 시험에 참석한 횟수를 찾는 솔루션을 작성하세요.
SELECT s.student_id, s.student_name, sub.subject_name, COUNT(e.subject_name) AS attended_exams
FROM Students s
CROSS JOIN Subjects sub
LEFT JOIN Examinations e -- 수강하지 않은 과목들 응시 횟수도 들어가야함 : LEFT JOIN 사용
    ON s.student_id = e.student_id -- 학생이 응시한 시험
    AND sub.subject_name = e.subject_name -- 응시한 시험과 과목 이름 매칭
GROUP BY s.student_id, s.student_name, sub.subject_name -- GROUP BY 안하니까 한 학생만 나오던데 왜그런지 공부
ORDER BY s.student_id, s.student_name, sub.subject_name;