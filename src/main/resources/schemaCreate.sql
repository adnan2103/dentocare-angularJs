
-- Table: user_credentials

CREATE TABLE IF NOT EXISTS role
(
role_id SERIAL NOT NULL,
role character varying(30),
CONSTRAINT role_pkey PRIMARY KEY (role_id)
);
ALTER TABLE role
  OWNER TO dentocaa;


CREATE TABLE IF NOT EXISTS user_credentials
(
  user_id SERIAL NOT NULL,
  email character varying(30),
  login_enabled boolean,
  password character varying(100),
  role_id INTEGER NOT NULL ,
  CONSTRAINT user_credentials_pkey PRIMARY KEY (user_id),
  CONSTRAINT user_credentials_role_fkey FOREIGN KEY (role_id)
      REFERENCES role (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_credentials
  OWNER TO dentocaa;

-- Table: user_detail


CREATE TABLE IF NOT EXISTS user_detail
(
  user_id integer NOT NULL,
  name character varying(100) NOT NULL,
  gender character varying(6) NOT NULL,
  date_of_birth date,
  phone_number character varying(10) NOT NULL,
  address_line1 character varying(30),
  address_line2 character varying(30),
  city character varying(20),
  state character varying(30),
  country character varying(20),
  pincode character varying(8),
  CONSTRAINT user_detail_pkey PRIMARY KEY (user_id),
  CONSTRAINT user_detail_user_fkey FOREIGN KEY (user_id)
      REFERENCES user_credentials (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_detail
  OWNER TO dentocaa;


-- Table: doctor_patient_mapping

CREATE TABLE IF NOT EXISTS doctor_patient_mapping
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
  OWNER TO dentocaa;


CREATE TABLE IF NOT EXISTS status
(
status_id SERIAL NOT NULL,
status character varying(50),
CONSTRAINT status_pkey PRIMARY KEY (status_id)
);
ALTER TABLE status
  OWNER TO dentocaa;

-- Table: treatment


CREATE TABLE IF NOT EXISTS treatment
(
  treatment_id SERIAL NOT NULL,
  user_id integer NOT NULL,
  status_id integer NOT NULL,
  chief_complaint_description character varying(100),
  notes character varying(100),
  pre_image_count integer NOT NULL,
  post_image_count integer NOT NULL,
  creation_date timestamp,
  created_by integer NOT NULL,
  last_updated_date timestamp,
  last_modified_by integer NOT NULL,
  CONSTRAINT treatment_pkey PRIMARY KEY (treatment_id),
  CONSTRAINT treatment_fkey FOREIGN KEY (user_id)
      REFERENCES user_detail (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT treatment_status_fkey FOREIGN KEY (status_id)
     REFERENCES status (status_id) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE treatment
  OWNER TO dentocaa;


-- Table: patient_oral_examination

CREATE TABLE IF NOT EXISTS patient_oral_examination
(
  oral_examination_id SERIAL NOT NULL,
  treatment_id integer NOT NULL,
  description character varying(100),
  cost integer,
  creation_date timestamp,
  created_by integer NOT NULL,
  last_updated_date timestamp,
  last_modified_by integer NOT NULL,
  CONSTRAINT patient_oral_examination_pkey PRIMARY KEY (oral_examination_id),
  CONSTRAINT patient_oral_examination_fkey FOREIGN KEY (treatment_id)
      REFERENCES treatment (treatment_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE patient_oral_examination
  OWNER TO dentocaa;


-- Table: payment

CREATE TABLE IF NOT EXISTS patient_payment
(
  payment_id SERIAL NOT NULL,
  treatment_id integer NOT NULL,
  payment_date date,
  payment_amount integer,
  treatment_done character varying(500),
  creation_date timestamp,
  created_by integer NOT NULL,
  last_updated_date timestamp,
  last_modified_by integer NOT NULL,
  CONSTRAINT patient_payment_pkey PRIMARY KEY (payment_id),
  CONSTRAINT payment_treatment_fkey FOREIGN KEY (treatment_id)
      REFERENCES treatment (treatment_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE patient_payment
  OWNER TO dentocaa;


-- Table: appointment

CREATE TABLE IF NOT EXISTS appointment
(
  appointment_id SERIAL NOT NULL,
  doctor_id integer NOT NULL,
  patient_id integer NOT NULL,
  appointment_start timestamp,
  appointment_end timestamp,
  planned_treatment character varying(100),
  CONSTRAINT appointment_pkey PRIMARY KEY (appointment_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE appointment
  OWNER TO dentocaa;

-- Table: modules

CREATE TABLE IF NOT EXISTS modules
(
  module_id SERIAL NOT NULL,
  module_code character varying(15),
  module_description character varying(100),
  monthly_cost integer NOT NULL,
  quaterly_cost integer NOT NULL,
  half_yearly_cost integer NOT NULL,
  yearly_cost integer NOT NULL,
  CONSTRAINT modules_pkey PRIMARY KEY (module_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE modules
  OWNER TO dentocaa;
