
insert into user_credentials(user_id,email,role,login_enabled,password) values (1,'adnan@khan.com','DOC',true,'password');
insert into user_credentials(user_id,email,role,login_enabled,password) values (2,null,'PAT',false,null);
insert into user_credentials(user_id,email,role,login_enabled,password) values (3,null,'PAT',false,null);
insert into user_credentials(user_id,email,role,login_enabled,password) values (4,null,'PAT',false,null);
insert into user_credentials(user_id,email,role,login_enabled,password) values (5,null,'PAT',false,null);
insert into user_credentials(user_id,email,role,login_enabled,password) values (6,null,'PAT',false,null);


update user_credentials set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';

insert into user_detail(user_id,first_name,last_name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(1,'Adnan','Khan','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(2,'Rohit','Tihor','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(3,'Rupam','Boul','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(4,'Sree','Vidya','Female',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(5,'Pallavi','Start','Female',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(6,'Phani','Hero','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);