-- ------------ EMP_DEPART
SHOW CREATE TABLE emp_depart;
desc emp_depart;
SHOW FULL COLUMNS FROM users;	-- 	sc utf8mb4_0900_ai_ci

INSERT INTO emp_depart (emp_id, depart_id) 
SELECT u.id, d.id FROM users u, departments d WHERE u.id = 2 AND d.id = 3;

SELECT  ed.*, u.email, d.department
	FROM emp_depart ed
	JOIN users u ON u.id = emp_id
	JOIN departments d ON ed.depart_id = d.id;

select emp_id, depart_id from emp_depart where emp_id = 1 and depart_id = ?;

select ed.emp_id, ed.depart_id, u.email, d.department
	from emp_depart ed
    join users u on u.id = ed.emp_id
    join departments d on ed.depart_id = d.id
    WHERE u.id = 1 AND d.id = 4;

select department from departments d
	join emp_depart ed on ed.depart_id = d.id
    join users u on u.id = ed.emp_id
    where u.id = 1;

select ed.emp_id, u.email, ed.depart_id, d.department from emp_depart ed
	join departments d on ed.depart_id = d.id
    join users u on ed.emp_id = u.id 
	where ed.emp_id = 0; 

insert into emp_depart(emp_id, depart_id) values (0,2);

select ed.*, u.email, d.department from emp_depart ed
	join users u on u.id = ed.emp_id
    join departments d on d.id = ed.depart_id where d.department =  "finance";
order by emp_id desc;

select u.email, ed.* from emp_depart ed
	join users u on ed.emp_id = u.id
	where emp_id = 2;

delete from emp_depart where emp_id = 34 and depart_id = 2;

select row_number() over (order by ed.emp_id) as id, ed.*, u.email from emp_depart ed
	join users u on ed.emp_id = u.id order by id desc;

select * from emp_depart 
	where emp_id = 34;

SELECT ROW_NUMBER() OVER (ORDER BY emp_id) AS row_num,
    emp_id, depart_id FROM emp_depart;

select * from emp_depart where emp_id = 1;