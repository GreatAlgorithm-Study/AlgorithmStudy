# https://school.programmers.co.kr/learn/courses/30/lessons/284530

# * 문제 설명 *
# 수원지역의 연도 별 평균 미세먼지 오염도, 평균 초미세먼지 오염도 조회
# 각 평균 값은 소수점 셋째자리에서 반올림
# 결과는 연도 기준 오름 차순

SELECT 
    YEAR(YM) AS YEAR, 
    ROUND(AVG(PM_VAL1), 2) AS PM10,
    ROUND(AVG(PM_VAL2), 2) AS "PM2.5"
FROM AIR_POLLUTION
WHERE LOCATION2 = '수원'
GROUP BY YEAR
ORDER BY YEAR;

# 컬럼명에 소수점이 들어갈 경우, 문자열 처리해주기
# YEAR는 정수형, DATEFORMAT은 문자열