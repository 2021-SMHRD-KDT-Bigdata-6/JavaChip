create table member(
	id varchar2(500) primary key,
	pw varchar2(500) not null
);

drop table dama;
create table dama(
	nick varchar2(500) primary key,
	lv number(3) not null,
	exp number(10) not null,
	id varchar2(500),
	constraint fk_id foreign key(id) references member(id)
);

select * from member;
select * from dama;

alter table big_member
add (lv number(10) not null);

select * from dama;
update dama set exp = exp + 30;

insert into dama values('a', 5, 10, 'c'); 
insert into dama values('b', 3, 20, 'c'); 
insert into dama values('c', 2, 30, 'c'); 
insert into dama values('d', 5, 30, 'mj'); 
insert into dama values('e', 2, 30, 'c'); 
insert into dama values('f', 1, 30, 'mj'); 
insert into dama values('g', 3, 30, 'c'); 