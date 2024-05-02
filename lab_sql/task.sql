-- 예제 1~40
-- 예제_003
 select empno as 사원번호, ename as 사원이름, sal as "Salary"
 from emp;
 
 -- 예제_004
 select ename || sal
 from emp;
 
 -- 예제_005-2
 select unique job
 from emp;
 
 -- 예제_006-2
 select ename, sal as 월급
 from emp
 order by 월급 asc;
 
 -- 예제_006-4
 select ename, deptno, sal
 from emp
 order by 2, 3 desc;
 
 -- 예제_008-3
 select *
 from nls_session_parameters
 where PARAMETER = 'NLS_DATE_FORMAT';
 
 -- 예제_009
 select ename, sal*12 as 연봉
 from emp
 where sal*12 >= 36000;
 
 -- 예제_013
 select ename, comm
 from emp
 where comm is null;
 
 -- 예제_016
 select upper(ename), lower(ename), initcap(ename)
 from emp;
 
 -- 예제_017
 select substr('SMITH', 1, 3)
 from dual;
 
 -- 예제_018
 select ename, length(ename)
 from emp;
 
 -- 예제_019
 select instr('SMITH', 'M')
 from dual;
 
 select rtrim(substr('abcdefg@naver.com', instr('abcdefg@naver.com', '@')+1), '.com')
 from dual;
 
 -- 예제_020
 select ename, replace(sal, 0, '*')
 from emp;
 
 select ename, regexp_replace(sal, '[0-3]', '*') as SALARY
 from emp;
 
 -- 예제_021
 select ename, lpad(sal, 10, '*') as salary1, rpad(sal, 10, '*') as salary2
 from emp;
 
 -- 예제_022
 select 'smith', LTRIM('smith', 's'), RTRIM('smith', 'h'), trim('s' from 'smiths')
 from dual;
 
 -- 예제_023
 select '876.567' as 숫자, round(876.567, 1)
 from dual;
 
 -- 예제_024
 select '876.567' as 숫자 , trunc(876.567,1)
 from dual;
 
 -- 예제_025
 select mod(10, 3)
 from dual;
 
 select empno, mod(empno, 2)
 from emp;
 
 select empno, ename
 from emp
 where mod(empno,2) = 0;
 
 select floor(10/3)
 from dual;
 
 -- 예제_026
 select ename, months_between(sysdate,hiredate)
 from emp;
 
 -- 예제_027
 select add_months(to_date('2019-05-01', 'RRRR-MM-DD'), 100)
 from dual;
 
 select to_date('2019-05-01', 'RRRR-MM-DD') + 100
 from dual;
 
 select to_date('2019-05-01', 'RRRR-MM-DD') + interval '100' month
 from dual;
 
 -- 예제_028
 select '2019/05/22', next_day('2019/05/22', '월요일')
 from dual;
 
 select next_day(sysdate, '월요일') 
 from dual;
 
 -- 예제_029
 select last_day(sysdate) 
 from dual;
 
 select ename, hiredate, last_day(hiredate)
 from emp
 where ename='KING';
 
 -- 예제_030
 select ename, to_char(hiredate, 'DAY') as 요일, to_char(sal, '999,999') as 월급
 from emp
 where ename = 'SCOTT';
 
 select ename as 이름, extract(year from hiredate) as 연도,
                       extract(month from hiredate) as 달,
                       extract(day from hiredate) as 요일
 from emp;
 
 select ename as 이름, to_char(sal, '999,999') as 월급
 from emp;
 
 -- 예제_031
 select ename, hiredate
 from emp
 where hiredate = to_date('81/11/17', 'RR/MM/DD');
 
 -- 예제_034
 select ename, deptno, decode(deptno,10,300,20,400,0) as 보너스
 from emp;
 
 select empno, mod(empno,2), decode(mod(empno,2), 0, '짝수', 1, '홀수') as 보너스
 from emp;
 
 select ename, job, decode(job, 'SALESMAN', 5000, 2000) as 보너스
 from emp;
 
 -- 예제_035
 select ename, job, sal, case when sal >= 3000 then 500
                              when sal >= 2000 then 300
                              when sal >= 1000 then 200
                              else 0 end as bonus
 from emp
 where job in ('SALESMAN', 'ANALYST');