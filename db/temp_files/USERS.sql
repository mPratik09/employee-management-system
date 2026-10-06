-- change column name
alter table users
	rename column employee to status,
	rename column admin to role;
-- change data type
alter table users
	modify column role varchar(10) default null;

select * from users where role like "%ADMIN%" order by id desc ;

select * from users 
	order by id desc ;

    update users
    set status = 'APPROVED' where id = 0;

select * from users where status = 'PENDING' ;

update users
	set status = 'PENDING'
	where id = 6;
select * from users u where id = 4;
