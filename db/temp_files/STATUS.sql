-- ---------	status

drop table status;
create table status(
    id int not null auto_increment primary key,
	status varchar(10) not null);

select * from status;

SET SESSION sql_mode = CONCAT(@@sql_mode, ',NO_AUTO_VALUE_ON_ZERO');
insert into status (id, status) values (0, 'unassigned'), (1, 'approved'), (2, 'pending'), (3, 'rejected');

update status
	set status = 'approved'
    where id = 2;
