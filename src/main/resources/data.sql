insert into role(role,description) values('D', 'Doctor');

insert into user_credentials(user_id,email,password,role,login_enabled) values (1,'adnan@khan.com','password','D',true);

update user_credentials set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';

insert into user_detail(user_id,first_name,last_name,gender,date_of_birth,mobile_number,address_line1,address_line2,city,state,country,pincode) values
(1,'Adnan','Khan','Male',now(),123456789,'Address line1','Address line2','Hyderabad','TS','India',800086);