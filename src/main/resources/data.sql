insert into modules(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(1,'PMM','Patient Managment Module',200,500,1000,1800);

insert into modules(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(2,'TMM','Treatment Management Module',200,500,1000,1800);

insert into modules(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(3,'TIMM','Treatment Image Management Module',200,500,1000,1800);

insert into modules(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(4,'AMMALERTS','Appointment management Module with Alerts',200,500,1000,1800);

insert into modules(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(5,'AMMNOALERTS','Appointment management Module without Alerts',200,500,1000,1800);

insert into modules(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(6,'IMM','Invoces management Module',200,500,1000,1800);

insert into modules(module_id,module_code,module_description,monthly_cost,quaterly_cost,half_yearly_cost,yearly_cost)
values(7,'RMM','Report management Module',200,500,1000,1800);

insert into role(role) values('DENTOCAREADMIN');
insert into role(role) values('CLINICADMIN');
insert into role(role) values('CLINICUSER');
insert into role(role) values('PATIENT');

insert into status(status) values('In-Progress');
insert into status(status) values('Closed');

insert into user_credentials(email,login_enabled,password,role_id) values ('test.user@gmail.com',true,'password',2);
update user_credentials set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';


insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(1,'Dr Test User','Male',now(),'123444444','Address line1','Address line2','Hyderabad','TS','India','800086');
