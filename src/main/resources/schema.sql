
DROP SEQUENCE treatment_id_sequence;
DROP SEQUENCE user_id_sequence;
DROP SEQUENCE chief_complaint_id_sequence;
DROP SEQUENCE oral_examination_id_sequence;
DROP SEQUENCE payment_id_sequence;

-- Sequence: user_id_sequence

CREATE SEQUENCE user_id_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE user_id_sequence
  OWNER TO dentocar;
GRANT ALL ON SEQUENCE user_id_sequence TO dentocar;
GRANT ALL ON SEQUENCE user_id_sequence TO public;


-- Sequence: treatment_id_sequence

CREATE SEQUENCE treatment_id_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE treatment_id_sequence
  OWNER TO dentocar;


-- Sequence: chief_complaint_id_sequence

CREATE SEQUENCE chief_complaint_id_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE chief_complaint_id_sequence
  OWNER TO dentocar;

-- Sequence: oral_examination_id_sequence

CREATE SEQUENCE oral_examination_id_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE oral_examination_id_sequence
  OWNER TO dentocar;


-- Sequence: payment_id_sequence

CREATE SEQUENCE payment_id_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE payment_id_sequence
  OWNER TO dentocar;


DROP TABLE payment;
DROP TABLE chief_complaint;
DROP TABLE patient_oral_examination;
DROP TABLE default_oral_examination;
DROP TABLE treatment;
DROP TABLE doctor_patient_mapping;
DROP TABLE user_detail;
DROP TABLE user_credentials;



-- Table: user_credentials


CREATE TABLE user_credentials
(
  user_id integer NOT NULL,
  email character varying(30),
  role character varying(3),
  login_enabled boolean,
  password character varying(100),
  CONSTRAINT user_credentials_pkey PRIMARY KEY (user_id)
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



-- Table: treatment


CREATE TABLE treatment
(
  treatment_id integer NOT NULL,
  user_id integer NOT NULL,
  status character varying(3) NOT NULL, -- PEN,COM,INP
  total_treatment_cost integer NOT NULL,
  CONSTRAINT treatment_pkey PRIMARY KEY (treatment_id),
  CONSTRAINT treatment_treatment_id_fkey FOREIGN KEY (treatment_id)
      REFERENCES user_detail (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE treatment
  OWNER TO dentocar;
COMMENT ON COLUMN treatment.status IS 'PEN,COM,INP';


-- Table: default_oral_examination

CREATE TABLE default_oral_examination
(
  oral_examination_id integer NOT NULL,
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


-- Table: chief_complaint


CREATE TABLE chief_complaint
(
  chief_complaint_id integer NOT NULL,
  treatment_id integer NOT NULL,
  description character varying(100) NOT NULL,
  CONSTRAINT chief_complaint_pkey PRIMARY KEY (chief_complaint_id),
  CONSTRAINT chief_complaint_treatment_id_fkey FOREIGN KEY (treatment_id)
      REFERENCES treatment (treatment_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE chief_complaint
  OWNER TO dentocar;

-- Table: payment


CREATE TABLE payment
(
  payment_id integer NOT NULL,
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

