
insert into role(role) values('PATIENT');
insert into role(role) values('DOCTOR');

insert into status(status) values('In-Progress');
insert into status(status) values('Closed');

insert into user_credentials(email,login_enabled,password,role_id) values ('test.user@gmail.com',true,'password',2);
update user_credentials set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';


insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(1,'Dr Test User','Male',now(),'123444444','Address line1','Address line2','Hyderabad','TS','India','800086');
