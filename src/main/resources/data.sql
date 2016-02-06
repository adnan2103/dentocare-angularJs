
insert into role(role) values('PATIENT');
insert into role(role) values('DOCTOR');


insert into user_credentials(email,login_enabled,password,role_id) values ('adnan@khan.com',true,'password',2);
insert into user_credentials(email,login_enabled,password,role_id) values (null,false,null,1);
insert into user_credentials(email,login_enabled,password,role_id) values (null,false,null,1);
insert into user_credentials(email,login_enabled,password,role_id) values (null,false,null,2);
insert into user_credentials(email,login_enabled,password,role_id) values (null,false,null,1);
insert into user_credentials(email,login_enabled,password,role_id) values (null,false,null,1);

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


insert into doctor_patient_mapping(doctor_id,patient_id) values(1,2);
insert into doctor_patient_mapping(doctor_id,patient_id) values(1,3);
insert into doctor_patient_mapping(doctor_id,patient_id) values(4,5);
insert into doctor_patient_mapping(doctor_id,patient_id) values(4,6);

