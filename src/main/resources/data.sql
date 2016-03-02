
insert into role(role) values('PATIENT');
insert into role(role) values('DOCTOR');


insert into user_credentials(email,login_enabled,password,role_id) values ('adnan@khan.com',true,'password',2);
insert into user_credentials(email,login_enabled,password,role_id) values (null,false,null,1);
insert into user_credentials(email,login_enabled,password,role_id) values (null,false,null,1);
insert into user_credentials(email,login_enabled,password,role_id) values ('info@adnan.com',false,null,2);
insert into user_credentials(email,login_enabled,password,role_id) values (null,false,null,1);
insert into user_credentials(email,login_enabled,password,role_id) values (null,false,null,1);
insert into user_credentials(email,login_enabled,password,role_id) values ('test',false,null,1);
insert into user_credentials(email,login_enabled,password,role_id) values ('danish@khan.com',true,'password',2);


update user_credentials set password = '$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u';


insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(1,'Adnan Khan','Male',now(),'123444444','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(2,'Rohit Tihor','Male',now(),'123455555','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(3,'Rupam Baaul','Male',now(),'123456666','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(4,'Sree Vidya','Female',now(),'123456777','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(5,'Pallavi Nalluri','Female',now(),'123456788','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(6,'Phani Hero','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(7,'One More Hero','Male',now(),'123456789','Address line1','Address line2','Hyderabad','TS','India','800086');

insert into user_detail(user_id,name,gender,date_of_birth,phone_number,address_line1,address_line2,city,state,country,pincode) values
(8,'Danish Khan','Male',now(),'123444444','Address line1','Address line2','Hyderabad','TS','India','800086');


insert into doctor_patient_mapping(doctor_id,patient_id) values(1,2);
insert into doctor_patient_mapping(doctor_id,patient_id) values(1,3);
insert into doctor_patient_mapping(doctor_id,patient_id) values(4,5);
insert into doctor_patient_mapping(doctor_id,patient_id) values(4,6);
insert into doctor_patient_mapping(doctor_id,patient_id) values(4,2);
insert into doctor_patient_mapping(doctor_id,patient_id) values(4,3);
insert into doctor_patient_mapping(doctor_id,patient_id) values(4,7);
insert into doctor_patient_mapping(doctor_id,patient_id) values(8,2);
insert into doctor_patient_mapping(doctor_id,patient_id) values(8,6);

insert into status(status) values('In-Progress');
insert into status(status) values('Closed');


insert into treatment(user_id,status_id,chief_complaint_description,notes) values(2,1,'Tooth ache|gum sweling|redness','New notes1');
insert into treatment(user_id,status_id,chief_complaint_description,notes) values(2,1,'Tooth ache2|gum sweling2|redness2','New notes2');
insert into treatment(user_id,status_id,chief_complaint_description,notes) values(3,2,'Teeth bug','New notes3');
insert into treatment(user_id,status_id,chief_complaint_description,notes) values(6,2,'Teeth buggggg|teeth break','New notes4');


insert into patient_oral_examination(treatment_id,description,cost) values(1,'Scaling',3000);
insert into patient_oral_examination(treatment_id,description,cost) values(1,'root canal',6000);

insert into patient_oral_examination(treatment_id,description,cost) values(2,'Scaling',2000);

insert into patient_oral_examination(treatment_id,description,cost) values(3,'root canal',4000);

insert into patient_oral_examination(treatment_id,description,cost) values(4,'root canal',1000);
insert into patient_oral_examination(treatment_id,description,cost) values(4,'extraction',400);



insert into payment(treatment_id,payment_date,payment_amount,treatment_done) values(1,now(),4000,'first payment');
insert into payment(treatment_id,payment_date,payment_amount,treatment_done) values(1,now(),1000,'second visit');
insert into payment(treatment_id,payment_date,payment_amount,treatment_done) values(2,now(),3000,'first visit');
insert into payment(treatment_id,payment_date,payment_amount,treatment_done) values(3,now(),2000,'first visit');
insert into payment(treatment_id,payment_date,payment_amount,treatment_done) values(3,now(),1000,'complet payment');

insert into payment(treatment_id,payment_date,payment_amount,treatment_done) values(4,now(),40,'first payment');


