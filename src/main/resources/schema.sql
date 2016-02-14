
DROP TABLE payment;
DROP TABLE patient_oral_examination;
DROP TABLE default_oral_examination;
DROP TABLE treatment;
DROP TABLE status;
DROP TABLE doctor_patient_mapping;
DROP TABLE user_detail;
DROP TABLE user_credentials;
DROP TABLE role;


-- Table: user_credentials

CREATE TABLE role
(
role_id SERIAL NOT NULL,
role character varying(30),
CONSTRAINT role_pkey PRIMARY KEY (role_id)
);
ALTER TABLE role
  OWNER TO dentocar;


CREATE TABLE user_credentials
(
  user_id SERIAL NOT NULL,
  email character varying(30),
  login_enabled boolean,
  password character varying(100),
  role_id INTEGER NOT NULL ,
  CONSTRAINT user_credentials_pkey PRIMARY KEY (user_id),
  CONSTRAINT user_credentials_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES role (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_credentials
  OWNER TO dentocar;

-- Table: user_detail


CREATE TABLE user_detail
(
  user_id integer NOT NULL,
  first_name character varying(30) NOT NULL,
  last_name character varying(30),
  gender character varying(6) NOT NULL,
  date_of_birth date,
  phone_number character varying(10) NOT NULL,
  address_line1 character varying(30),
  address_line2 character varying(30),
  city character varying(20),
  state character varying(30),
  country character varying(20),
  pincode integer,
  CONSTRAINT user_detail_pkey PRIMARY KEY (user_id),
  CONSTRAINT user_detail_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES user_credentials (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_detail
  OWNER TO dentocar;


-- Table: doctor_patient_mapping

CREATE TABLE doctor_patient_mapping
(
  doctor_id integer NOT NULL,
  patient_id integer NOT NULL,
  CONSTRAINT doctor_patient_mapping_pkey PRIMARY KEY (doctor_id, patient_id),
  CONSTRAINT doctor_patient_mapping_doctor_id_fkey FOREIGN KEY (doctor_id)
      REFERENCES user_credentials (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT doctor_patient_mapping_patient_id_fkey FOREIGN KEY (patient_id)
      REFERENCES user_credentials (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE doctor_patient_mapping
  OWNER TO dentocar;


CREATE TABLE status
(
status_id SERIAL NOT NULL,
status character varying(50),
CONSTRAINT status_pkey PRIMARY KEY (status_id)
);
ALTER TABLE status
  OWNER TO dentocar;

-- Table: treatment


CREATE TABLE treatment
(
  treatment_id SERIAL NOT NULL,
  user_id integer NOT NULL,
  status_id integer NOT NULL,
  chief_complaint_description character varying(100) NOT NULL,
  CONSTRAINT treatment_pkey PRIMARY KEY (treatment_id),
  CONSTRAINT treatment_treatment_id_fkey FOREIGN KEY (treatment_id)
      REFERENCES user_detail (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT treatment_status_id_fkey FOREIGN KEY (status_id)
     REFERENCES status (status_id) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE treatment
  OWNER TO dentocar;


-- Table: default_oral_examination

CREATE TABLE default_oral_examination
(
  oral_examination_id SERIAL NOT NULL,
  description character varying(100),
  cost integer,
  CONSTRAINT default_oral_examination_pkey PRIMARY KEY (oral_examination_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE default_oral_examination
  OWNER TO dentocar;

-- Table: patient_oral_examination

CREATE TABLE patient_oral_examination
(
  treatment_id integer NOT NULL,
  oral_examination_id integer NOT NULL,
  cost integer,
  CONSTRAINT patient_oral_examination_pkey PRIMARY KEY (treatment_id, oral_examination_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE patient_oral_examination
  OWNER TO dentocar;


-- Table: payment

CREATE TABLE payment
(
  payment_id SERIAL NOT NULL,
  treatment_id integer NOT NULL,
  payment_date date,
  payment_amount integer,
  notes character varying(100),
  CONSTRAINT payment_pkey PRIMARY KEY (payment_id),
  CONSTRAINT payment_treatment_id_fkey FOREIGN KEY (treatment_id)
      REFERENCES treatment (treatment_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE payment
  OWNER TO dentocar;

