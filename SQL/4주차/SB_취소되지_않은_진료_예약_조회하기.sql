-- 코드를 입력하세요
-- 2022년 4월 13일 취소되지 않은 흉부외과(CS) 진료 예약 내역을 조회
SELECT a.APNT_NO, p.PT_NAME, p.PT_NO, a.MCDP_CD, d.DR_NAME, a.APNT_YMD
FROM APPOINTMENT a
JOIN DOCTOR d ON a.MDDR_ID = d.DR_ID
JOIN PATIENT p ON a.PT_NO = p.PT_NO
WHERE 
    DATE_FORMAT(a.APNT_YMD, '%Y-%m-%d') = '2022-04-13'
    AND a.MCDP_CD = 'CS'
    AND a.APNT_CNCL_YN = 'N'
ORDER BY a.APNT_YMD;