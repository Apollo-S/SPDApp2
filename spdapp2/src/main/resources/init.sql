drop table if exists `authorities`;
drop table if exists `users`;
drop table if exists `group_members`;
drop table if exists `group_authorities`;
drop table if exists `groups`;

create table `users` (
	`username` varchar(50) NOT NULL PRIMARY KEY,
	`password` varchar(150) NOT NULL,
   `enabled` boolean NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `authorities` (
	`username` varchar(50) NOT NULL,
	`authority` varchar(50) NOT NULL,
   CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users`(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create unique index ix_auth_username on authorities (username, authority);

create table `groups` (
	id bigint UNSIGNED NOT NULL PRIMARY KEY  AUTO_INCREMENT,
	group_name varchar(50) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `group_authorities` (
	group_id bigint UNSIGNED not null,
	authority varchar(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `group_members` (
	id bigint UNSIGNED NOT NULL PRIMARY KEY  AUTO_INCREMENT,
	username varchar(50) not null,
	group_id bigint UNSIGNED not null,
	constraint fk_group_members_group foreign key(group_id) references groups(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insertion

insert into `users`(`username`, `password`, `enabled`) 
values 
	('admin', '64b484d528cccca4dfe3036419f8a94f823ae76e8e1ffc1c245273827718469f83982b2e7a3f1e0d', true),
	('user', 'de606efdad4166552eac27deba0fd33827839d9fe47d7d268c962fbe533b527d7a95c9c711125130', true),
	('user_blocked', '60c7c3a707aff3cf7d2d5926685691bedd2e541db6f568597f55ba6f2e6598804232b87a0122988a', false);

insert into `groups`(`group_name`) 
VALUES 
	('Admins'),
	('Users');
	
insert into `group_authorities`(`group_id`, `authority`) 
VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');
	
INSERT INTO `group_members` (`username`, `group_id`) 
VALUES 
	('admin', 1),
	('user', 2),
	('user_blocked', 2);
