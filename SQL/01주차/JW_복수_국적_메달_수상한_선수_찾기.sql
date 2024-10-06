SELECT B.name FROM records A
  INNER JOIN athletes B
    ON A.athlete_id = B.id
  INNER JOIN games C
    ON A.game_id = C.id
  INNER JOIN teams D
    ON A.team_id = D.id
WHERE C.year >= 2000 
  AND A.medal NOTNULL
GROUP BY B.id
HAVING COUNT(distinct D.team) > 1
ORDER BY B.name