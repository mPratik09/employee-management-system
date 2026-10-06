drop table emp_department_requests;
show create table emp_department_requests;

select * from emp_depart_requests;

insert into emp_depart_requests (emp_id, depart_id) values (31, 3	);

select * from emp_depart_requests;
delete from emp_depart_requests where id = 1;

delete from emp_depart_requests where emp_id = 0 and depart_id = 4;
select * from emp_depart_requests where emp_id = 0;

select u.email, d.department, edr.* from emp_depart_requests edr
	join departments d on edr.depart_id = d.id
    join users u on edr.emp_id = u.id;

select edr.*, u.email from emp_depart_requests edr 
	join users u on edr.emp_id = u.id
    join departments d on edr.depart_id = d.id;
    
    
--    =====================
    
select edr.*, edr.emp_id, u.first_name, u.last_name, edr.depart_id, d.department from emp_depart_requests edr
	join users u on u.id = edr.emp_id
    join departments d on d.id = edr.depart_id		;
    
select edr.id, edr.emp_id, u.first_name, u.last_name, edr.depart_id, d.department from emp_depart_requests edr
	join users u on u.id = edr.emp_id
    join departments d on d.id = edr.depart_id;