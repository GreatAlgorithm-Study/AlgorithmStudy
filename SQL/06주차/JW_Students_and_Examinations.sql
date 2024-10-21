SELECT
    A.student_id
    , A.student_name
    , B.subject_name
    , COUNT(C.student_id) attended_exams
FROM
    Students A
    CROSS JOIN Subjects B
    LEFT JOIN Examinations C
        ON A.student_id = C.student_id
        AND B.subject_name = C.subject_name
GROUP BY
    A.student_id
    , A.student_name
    , B.subject_name
ORDER BY
    A.student_id
    , A.student_name
    , B.subject_name