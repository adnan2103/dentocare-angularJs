
insert into user_credentials(user_id,email,login_enabled,password) values (1,'adnan@khan.com',true,'password');
insert into user_credentials(user_id,email,login_enabled,password) values (2,null,false,null);
insert into user_credentials(user_id,email,login_enabled,password) values (3,null,false,null);
insert into user_credentials(user_id,email,login_enabled,password) values (4,null,false,null);
insert into user_credentials(user_id,email,login_enabled,password) values (5,null,false,null);
insert into user_credentials(user_id,email,login_enabled,password) values (6,null,false,null);


update user_credentials set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';

insert into user_detail(user_id,first_name,last_name,role,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(1,'Adnan','Khan','DOC','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,role,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(2,'Rohit','Tihor','PAT','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,role,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(3,'Rupam','Boul','PAT','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,role,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(4,'Sree','Vidya','PAT','Female',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,role,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(5,'Pallavi','Start','PAT','Female',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);

insert into user_detail(user_id,first_name,last_name,role,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(6,'Phani','Hero','PAT','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India',800086);