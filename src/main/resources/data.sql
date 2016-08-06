insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(1,'PMM','Patient Management Module',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(2,'PIMM','Patient Image Management Module',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(3,'TMM','Treatment Management Module',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(4,'TIMM','Treatment Image Management Module',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(5,'AMM_ALERTS','Appointment management Module with Alerts',200,500,1000,1800);

insert into module(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(6,'AMM_NO_ALERTS','Appointment management Module without Alerts',200,500,1000,1800);

insert into role(role) values('DENTOCARE_ADMIN');
insert into role(role) values('CLINIC_ADMIN');
insert into role(role) values('CLINIC_USER');
insert into role(role) values('PATIENT');

insert into status(status) values('In-Progress');
insert into status(status) values('Closed');

insert into user_credentials(login_id,login_enabled,password,role_id) values ('dento-care-admin',true,'password',1);
insert into user_detail(user_id,name,gender,date_of_birth) values(1,'Dento Care Admin','Male',now());
insert into contact_detail(contact_id,user_id,primary_phone_number,secondary_phone_number,email,address_line1,address_line2,city,state,country,pincode) values(1,1,'123444444','9876543201','test@gmail.com','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_credentials(login_id,login_enabled,password,role_id) values ('clinic-admin',true,'password',2);
insert into user_detail(user_id,name,gender,date_of_birth) values(2,'Clinic Admin','Male',now());
insert into contact_detail(contact_id,user_id,primary_phone_number,secondary_phone_number,email,address_line1,address_line2,city,state,country,pincode) values(2,2,'123444444','9876543201','test@gmail.com','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_credentials(login_id,login_enabled,password,role_id) values ('clinic-user',true,'password',3);
insert into user_detail(user_id,name,gender,date_of_birth) values(3,'Clinic User','Male',now());
insert into contact_detail(contact_id,user_id,primary_phone_number,secondary_phone_number,email,address_line1,address_line2,city,state,country,pincode) values(3,3,'123444444','9876543201','test@gmail.com','Address line1','Address line2','Hyderabad','TS','India','800086');

update user_credentials set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';

insert into clinic(clinic_id,name) values(2, 'Dento Care Clinic');

insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,1,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,2,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,3,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,4,now(),now());
insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(2,5,now(),now());
update clinic_modules_mapping set expiry_date = '2016-12-31';

insert into clinic_user_module_access(user_id, module_id) values(3,1);
insert into clinic_user_module_access(user_id, module_id) values(3,3);

insert into clinic_user_mapping(clinic_id, user_id) values(2,3);
