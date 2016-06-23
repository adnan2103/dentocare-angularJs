
insert into role(role) values('PATIENT');
insert into role(role) values('DOCTOR');

insert into status(status) values('In-Progress');
insert into status(status) values('Closed');

insert into user_credentials(email,login_enabled,password,role_id) values ('adnan@gmail.com',true,'password',2);
insert into user_credentials(email,login_enabled,password,role_id) values ('rohit@gmail.com',true,'password',2);
insert into user_credentials(email,login_enabled,password,role_id) values ('siva@gmail.com',true,'password',2);
update user_credentials set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';


insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(1,'Dr Adnan','Male',now(),'123444444','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(2,'Dr Rohit','Male',now(),'123444444','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(3,'Dr Siva','Male',now(),'123444444','Address line1','Address line2','Hyderabad','TS','India','800086');
