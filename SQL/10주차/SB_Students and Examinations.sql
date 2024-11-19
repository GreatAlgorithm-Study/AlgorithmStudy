-- 각 학생이 각 시험에 응시한 횟수를 찾기 위한 해결책

-- CROSS JOIN 이용
SELECT st.student_id, st.student_name ,sb.subject_name, 
        COUNT(e.student_id) AS attended_exams
FROM Students st
CROSS JOIN Subjects sb
LEFT JOIN Examinations e
    ON e.student_id = st.student_id
    AND e.subject_name = sb.subject_name
GROUP BY st.student_id, st.student_name, sb.subject_name
ORDER BY st.student_id, st.student_name, sb.subject_name

-- CORSS JOIN 이용X, (잘못된 값 반환 가능) 
SELECT a.student_id, a.student_name, b.subject_name,
       COUNT(c.subject_name) AS attended_exams
FROM Students a
JOIN Subjects b
LEFT JOIN Examinations c ON a.student_id = c.student_id AND b.subject_name = c.subject_name
GROUP BY a.student_id, a.student_name, b.subject_name
ORDER BY a.student_id, b.subject_name;

