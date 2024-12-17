alter table users add active tinyint;
update users set active = 1;

alter table courses add active tinyint;
update courses set active = 1;

alter table answers add active tinyint;
update answers set active = 1;