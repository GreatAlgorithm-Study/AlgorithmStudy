// https://school.programmers.co.kr/learn/courses/30/lessons/276013
// * SKILL_1, SKILL_2, SKILL_3 열 중에 하나라도 Python이 있으면 출력하는 문제
// * 새로 알게된 점
//    - 원래는 '열 이름 in (찾는 값)' 형식만 쓸 수 있는 줄 알았는데,
//    - '찾는 값' in ('열 이름') 이렇게 해도 되는 걸 알게 되었음

SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPER_INFOS
WHERE 'Python' in (SKILL_1, SKILL_2, SKILL_3)
ORDER BY ID ASC