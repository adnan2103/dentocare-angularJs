insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(1,'PMM','Patient Management Module',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(2,'PIMM','Patient Image Management Module',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(3,'TMM','Treatment Management Module',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(4,'TIMM','Treatment Image Management Module',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(5,'AMM','Appointment management',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(6,'ALERTS','SMS/EMAIL Alerts System',200,500,1000,1800);

insert into role(role) values('DENTOCARE_ADMIN');
insert into role(role) values('CLINIC_ADMIN');
insert into role(role) values('CLINIC_USER');
insert into role(role) values('PATIENT');

insert into status(status) values('In-Progress');
insert into status(status) values('Closed');

insert into user_credentials(login_id,login_enabled,password,role_id) values ('test-dento-care-admin',true,'password',1);
insert into user_detail(user_id,name,gender,age) values(1,'Dento Care Admin','Male',40);
insert into contact_detail(contact_id,user_id,primary_phone_number,secondary_phone_number,email,address_line1,address_line2,city,state,country,pincode) values(1,1,'123444444','9876543201','test@gmail.com','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_credentials(login_id,login_enabled,password,role_id) values ('test-clinic-admin',true,'password',2);
insert into user_detail(user_id,name,gender,age) values(2,'Clinic Admin','Male',35);
insert into contact_detail(contact_id,user_id,primary_phone_number,secondary_phone_number,email,address_line1,address_line2,city,state,country,pincode) values(2,2,'123444444','9876543201','test@gmail.com','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_credentials(login_id,login_enabled,password,role_id) values ('test-clinic-user',true,'password',3);
insert into user_detail(user_id,name,gender,age) values(3,'Clinic User','Male',30);
insert into contact_detail(contact_id,user_id,primary_phone_number,secondary_phone_number,email,address_line1,address_line2,city,state,country,pincode) values(3,3,'123444444','9876543201','test@gmail.com','Address line1','Address line2','Hyderabad','TS','India','800086');

update user_credentials set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';

insert into clinic(clinic_id,name) values(2, 'Test Clinic');

insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,1,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,2,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,3,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,4,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,5,now(),now());
update clinic_modules_mapping set expiry_date = '2016-12-31';

insert into clinic_user_module_access(user_id, module_id) values(3,1);
insert into clinic_user_module_access(user_id, module_id) values(3,3);

insert into clinic_user_mapping(clinic_id, user_id) values(2,3);
insert into clinic_user_mapping(clinic_id, user_id) values(2,2);


insert into user_credentials(login_id,login_enabled,password,role_id) values ('dr.danishkhan',true,'dentocare@123',2);
insert into user_detail(user_id,name,gender,age) values(4,'Dr. Danish Khan','Male',28);
insert into contact_detail(contact_id,user_id,primary_phone_number,secondary_phone_number,email,address_line1,address_line2,city,state,country,pincode) values(4,4,'9926414144','8878647837','dr.danishkhan@dento-care.com','Dento Care Clinic','Jahangirabad','Bhopal','MP','India','462008');
insert into clinic(clinic_id,name) values(3, 'Dento Care Clinic Admin');
insert into clinic_user_mapping(clinic_id, user_id) values(3,4);
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(3,1,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(3,2,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(3,3,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(3,4,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(3,5,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(3,6,now(),now());
update clinic_modules_mapping set expiry_date = '2099-12-31';
insert into clinic_user_module_access(user_id, module_id) values(4,1);
insert into clinic_user_module_access(user_id, module_id) values(4,2);
insert into clinic_user_module_access(user_id, module_id) values(4,3);
insert into clinic_user_module_access(user_id, module_id) values(4,4);
insert into clinic_user_module_access(user_id, module_id) values(4,5);
insert into clinic_user_module_access(user_id, module_id) values(4,6);
update user_credentials set password = '$2a$10$EuS3R0se59Ir4.kI56.yBuZXVbW1wpdbWxrRYVUj3ke4kUmoL7LFO' where user_id=4;
