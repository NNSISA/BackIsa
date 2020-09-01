INSERT INTO AUTHORITY (name) VALUES ('ROLE_PATIENT');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_CCADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DOCTOR');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_NURSE');

INSERT INTO clinic_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, clinic_id) VALUES ('cadmin', 'cadmin', 'probaprobaisa@gmail.com', 'Cadmin', 'Cadmin', '354168465', 'Glavna 54', 'Beograd', 'Srbija', '0645865499', 1);
INSERT INTO clinic_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, clinic_id) VALUES ('cadmin1', 'cadmin1', 'probaprobaisa@gmail.com', 'Cadmin1', 'Cadmin1', '354168465', 'Glavna 20', 'Beograd', 'Srbija', '0645865499', 1);

INSERT INTO clinical_center_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log,enabled) VALUES ('ccadmin', 'ccadmin', 'probaprobaisa@gmail.com', 'Admin', 'Adminic', '58768', 'Glavna BB', 'Novi Sad', 'Srbija', '06648299', 1,true);
INSERT INTO clinical_center_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log,enabled) VALUES ('akc', '123', 'probaprobaisa@gmail.com', 'Admin', 'Admin', '111111', 'Glavna BB', 'Novi Sad', 'Srbija', '06648299', 0,true);
INSERT INTO clinical_center_administrator (username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, first_log,enabled) VALUES ('akc1', '123', 'probaprobaisa@gmail.com', 'Admin', 'Admin', '111111', 'Glavna BB', 'Novi Sad', 'Srbija', '06648299', 0,true);

INSERT INTO clinic (version,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (0,'Koste Todorovica 26, 11000 Beograd', 'Klinika za pulmologiju je zdravstvena ustanova.', 'Klinika za pulmologiju', 0, 0, 0,44.797860, 20.458775);
INSERT INTO clinic (version,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (0,'New York, NY 10011', 'Nas prioritet je nase zdravlje 24/7', 'NYC Free Clinic', 0, 0, 3,44.797860, 20.458775);
INSERT INTO clinic (version,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (0,'Pasterova 2, Beograd', 'Nasa klinika nudi zdravlje nasim pacijentima', 'Klinika za ocne bolesti', 0, 0, 4,44.797860, 20.458775);
INSERT INTO clinic (version,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (0,' Koste Todorovića 4, 11000 Beograd', 'Klinika za neurohirurgiju je organizaciona jedinica Klinickog centra Srbije', 'Klinka za nerohirurgiju', 0, 0, 4,44.797860, 20.458775);
INSERT INTO clinic (version,address, description, name, pricelist, profit, rating,longitude,lat) VALUES (0,'Pasterova 2', 'Vama na usluzi 00-24', 'Klinika za fizikalnu medicinu i rehabilitaciju', 0, 0, 4,44.797860, 20.458775);

INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 6', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent1', 1497,'Pacijent1', '0640589536', 'pacijent1', 'pacijent1', '1', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 7', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent2', 5097,'Pacijent2', '0640589536', 'p1', 'p1', '2', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 7A', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent3', 25097,'Pacijent3', '0640589536', 'p2', 'p2', '3', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 7B', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent4', 1497,'Pacijent4', '0640589536', 'p3', 'p3', '4', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 7C', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Pacijent5', 5097,'Pacijent5', '0640589536', 'p4', 'p4', '5', true);
INSERT INTO patient (address, city, country, email, first_name, jmbg, last_name, mobile_number, password, username, record_id, is_enabled) VALUES ('Bulevar Oslobodjenja 8D', 'Novi Sad', 'Srbija', 'probaprobaisa@gmail.com', 'Sale', 5097,'Sale', '0640589536', 'sale', 'sale', '6', true);

INSERT INTO medical_staff (medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role,pocetak_radnog_vremena,kraj_radnog_vremena, clinic_id) VALUES ('2', 's2', '123', 'probaprobaisa@gmail.com', 'Sestra', 'Medicinski', '315787', 'Bulevar Oslobodjenja 103', 'Novi Sad', 'Srbija', '06658651', 2, 'nurse',"07:00","14:00",1);
INSERT INTO medical_staff (version,medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES (0,'1', 'doctor', '123', 'probaprobaisa@gmail.com', 'Doktor1', 'Doktor1', '6872368', 'Bulevar Kralja Petra I 2', 'Beograd', 'Serbia', '0612645665', 1, 'doctor', 1,"08:00","15:00", 1);
INSERT INTO medical_staff (version,medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES (0,'1', 'd1', '123', 'probaprobaisa@gmail.com', 'Doktor2', 'Doktor2', '2855558', 'Bulevar Cara Dusana 5', 'Novi Sad', 'Serbia', '0655555565', 3, 'doctor', 2,"08:00","15:00", 1);
INSERT INTO medical_staff (version,medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES (0,'1', 'd2', '123', 'probaprobaisa@gmail.com', 'Doktor3', 'Doktor3', '566987', 'Bulevar Cara Lazara 65', 'Novi Sad', 'Serbia', '0623566665', 0, 'doctor', 3,"09:00","16:00", 2);
INSERT INTO medical_staff (version,medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES (0,'1', 'd3', '123', 'probaprobaisa@gmail.com', 'Doktor4', 'Doktor4', '1111558', 'Danila Kisa 5', 'Novi Sad', 'Serbia', '0644455565', 4, 'doctor', 1,"07:00","14:00", 5);
INSERT INTO medical_staff (version,medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES (0,'1', 'd4', '123', 'probaprobaisa@gmail.com', 'Doktor5', 'Doktor6', '6546546', 'Glavna 123', 'Novi Sad', 'Serbia', '065684652', 4, 'doctor', 1,"07:00","14:00", 3);
INSERT INTO medical_staff (version,medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role, clinic_id,pocetak_radnog_vremena,kraj_radnog_vremena, appointment_type_id) VALUES (0,'1', 'd5', '123', 'probaprobaisa@gmail.com', 'Doktor7', 'Doktor7', '6595026', 'Bulevar Oslobodjenja 13', 'Novi Sad', 'Serbia', '063585526', 4, 'doctor', 1,"07:00","14:00", 3);
INSERT INTO medical_staff (version,medical_staff_type, username, password, email, first_name, last_name, jmbg, address, city, country, mobile_number, review, role,pocetak_radnog_vremena,kraj_radnog_vremena, clinic_id) VALUES (0,'2', 's1', '123', 'probaprobaisa@gmail.com', 'Sestra1', 'Medicinovic', '315787', 'Bulevar Oslobodjenja 103', 'Novi Sad', 'Serbia', '06658651', 2, 'nurse',"07:00","14:00",1);

INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('A', '+2.25', '185', '95',1);
INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('AB', '-2.25', '192', '82',2);
INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('0','-0.25', '156', '52',3);
INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('B+', '-0.0', '189', '72',4);
INSERT INTO medical_record (blood_type, diopter, height, weight, patient_id) VALUES ('B-', '-5.0', '150', '45',5);

INSERT INTO hospital_room (name,room_number,clinic_id,version) VALUES ('Glavna sala',1, 1,0);
INSERT INTO hospital_room (name,room_number,clinic_id,version) VALUES ('Operaciona sala',2, 1,0);
INSERT INTO hospital_room (name,room_number,clinic_id,version) VALUES ('Operaciona sala2',3, 1,0);
INSERT INTO hospital_room (name,room_number,clinic_id,version) VALUES ('Mala sala',23, 2,0);
INSERT INTO hospital_room (name,room_number,clinic_id,version) VALUES ('Velika sala',24, 2,0);

INSERT INTO surgery (version,date, description, patient, duration, clinic_id, medical_record_id) VALUES (0,"2020-02-22T16:00", 'Blefaroplastika', 'p1', 2, 1, 5);
INSERT INTO surgery (version,date, description, patient, duration, clinic_id, medical_record_id) VALUES (0,"2020-02-22T16:00", 'Rinoplastika', 'p4', 2, 2, 3);
INSERT INTO surgery (version,date, description, patient, duration, clinic_id, medical_record_id) VALUES (0,"2020-02-22T16:00", 'Smanjivanje zeluca', 'pacijent1', 2, 2, 1);
INSERT INTO surgery (version,date, description, patient, duration, clinic_id, hospital_room_id, medical_record_id) VALUES (0,"2020-02-22T14:00", 'Kolonoskopija', 'p4', 2, 1, 1, 4);
INSERT INTO surgery (version,date, description, patient, duration, clinic_id, hospital_room_id, medical_record_id) VALUES (0,"2020-02-23T14:00", 'Uklanjanje melanoma', 'p1', 2, 1, 2, 2);
INSERT INTO surgery (version,date, description, patient, duration, clinic_id, medical_record_id) VALUES (0,"2020-02-22T16:00", 'Hitna intervencija', 'p3', 2, 1, 4);
INSERT INTO surgery (version,date, description, patient, duration, clinic_id, medical_record_id) VALUES (0,"2020-02-14T14:00", 'Zasivanje glave', 'pacijent1', 2, 1, 1);
INSERT INTO surgery (version,date, description, patient, duration, clinic_id, medical_record_id) VALUES (0,"2020-02-14T14:00", 'Zasivanje prsta', 'p2', 2, 1, 2);

INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (5, 1);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (3, 2);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (1, 3);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (4, 4);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (2, 5);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (4, 6);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (1, 7);
INSERT INTO medical_record_surgeries(medical_record_id, surgeries_id) VALUES (2, 8);

INSERT INTO surgery_doctors(surgery_id, doctors_id) VALUES (4,2);
INSERT INTO surgery_doctors(surgery_id, doctors_id) VALUES (4,5);
INSERT INTO surgery_doctors(surgery_id, doctors_id) VALUES (5,5);

INSERT INTO medical_staff_surgeries(doctor_id, surgeries_id) VALUES (2,4);
INSERT INTO medical_staff_surgeries(doctor_id, surgeries_id) VALUES (5,4);
INSERT INTO medical_staff_surgeries(doctor_id, surgeries_id) VALUES (5,5);

INSERT INTO hospital_room_surgeries(hospital_room_id, surgeries_id) VALUES (1,4);
INSERT INTO hospital_room_surgeries(hospital_room_id, surgeries_id) VALUES (2,5);

INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,1);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (2,2);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (2,3);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,4);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,5);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,6);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,7);
INSERT INTO clinic_surgeries (clinic_id, surgeries_id) VALUES (1,8);

INSERT INTO request_appointments (version,price,date, description, duration, patient, type,clinic_id,doctor_id,doctor_username) VALUES (0,500,'2020-01-27T16:00', 'Sistematski pregled', 2, 'p1', 'tip1',1,2,'doctor');
INSERT INTO request_appointments (version,price,date, description, duration, patient, type,clinic_id,doctor_id,doctor_username) VALUES (0,300,'2020-01-23T10:00', 'Redovna kontrola', 2, 'p2', 'tip1',1,2,'doctor');
INSERT INTO request_appointments (version,price,date, description, duration, patient, type,clinic_id,doctor_id,doctor_username) VALUES (0,200,'2020-04-02T10:00', 'Redovna kontrola', 2, 'p2', 'tip1',1,3,'d1');

INSERT INTO medical_staff_request_appointments(doctor_id,request_appointments_id) values (2,1);
INSERT INTO medical_staff_request_appointments(doctor_id,request_appointments_id) values (2,2);
INSERT INTO medical_staff_request_appointments(doctor_id,request_appointments_id) values (3,3);

INSERT INTO appointments (price,version, date, description, duration, patient,finished, type, doctor_id, hospital_room_id, medical_record_id, doctor_username,type2_id) VALUES (1,0,'2020-02-05T16:00', 'cold', 2, 'p2', false,'Redovna kontrola', 2, 1, 5, 'doctor',1);
INSERT INTO appointments (price,version, date, description, duration, patient,finished, type, doctor_id, hospital_room_id, medical_record_id, doctor_username,diagnosis_id,type2_id, recipe_id, info) VALUES (1,0,'2020-02-03T16:00', 'Prelom noge', 2, 'pacijent1',true, 'surgery', 2, 2, 5, 'doctor', 1,1, 1, "Pacijent je pregledan, mirovanje i kontrola za nedelju dana");

INSERT INTO appointments (price,version, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id) VALUES (1,0,'2020-02-26T10:00', 'Provera mladeza', 2, 'pacijent1', 'tip pregleda', false, 3, 5, 'd1',1);
INSERT INTO appointments (price,version, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id) VALUES (1,0,'2020-02-25T16:00', 'Redovna kontrola', 2, 'p2', 'tip2', false, 2, 3, 'doctor',2);
INSERT INTO appointments (price,version, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id) VALUES (1,0,'2020-02-23T16:00', 'Pregled krajnika', 2, 'p2', 'tip2', false, 2, 3, 'doctor',1);
INSERT INTO appointments (price,version, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id) VALUES (1,0,'2020-02-24T16:00', 'Redovna kontrola', 2, 'p2', 'tip2', false, 2, 3, 'doctor',2);
INSERT INTO appointments (price,version, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id) VALUES (1,0,'2020-02-25T16:00', 'Redovna kontrola', 2, 'p2', 'tip2', false, 2, 3, 'doctor',4);
INSERT INTO appointments (price,version, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id) VALUES (1,0,'2020-02-15T16:00', 'Redovna kontrola', 2, 'p2', 'tip2', false, 2, 3, 'doctor',4);
INSERT INTO appointments (price,version, date, description, duration, patient, type, finished, doctor_id, medical_record_id, doctor_username,type2_id) VALUES (1,0,'2020-02-10T16:00', 'Redovna kontrola', 2, 'p2', 'tip2', false, 2, 3, 'doctor',3);

INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (5,1);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (5,2);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (5,3);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (3,4);
INSERT INTO medical_record_appointments(medical_record_id, appointments_id) VALUES (3,5);

INSERT INTO hospital_room_appointments(hospital_room_id, appointments_id) VALUES (1,1);
INSERT INTO hospital_room_appointments(hospital_room_id, appointments_id) VALUES (2,2);

--brzi pregledi(Nemaju dodeljenog pacijenta, ni medical_record)
INSERT INTO appointments (price,version, date, description, duration, type, finished, doctor_id, hospital_room_id, doctor_username) VALUES (200,0,'2020-08-20T12:00', 'Redovna kontrola', 2, 'tip7', false, 3, 4, 'd1');
INSERT INTO appointments (price,version, date, description, duration, type, finished, doctor_id, hospital_room_id, doctor_username) VALUES (500,0,'2020-10-15T08:00', 'Redovna kontrola', 2, 'tip5', false, 2, 5, 'doctor');

INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 1);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 2);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(3, 3);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 4);
INSERT INTO medical_staff_appointments(doctor_id, appointments_id) VALUES(2, 5);

INSERT INTO hospital_room_appointments(hospital_room_id, appointments_id) VALUES (4,8);
INSERT INTO hospital_room_appointments(hospital_room_id, appointments_id) VALUES (5,9);

INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,2);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (2,3);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,5);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (3,4);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,6);
INSERT INTO clinic_doctors(clinic_id, doctors_id) VALUES (1,7);

INSERT INTO clinic_nurses(clinic_id, nurses_id) VALUES (1,1);
INSERT INTO clinic_nurses(clinic_id, nurses_id) VALUES (1,8);

INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (5, 5, 2);
INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (1, 3, 2);
INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (3, 5, 3);
INSERT INTO patient_rated_doctor(ocena, patient_id, doctor_id) VALUES (5, 3, 3);

INSERT INTO patient_rated_clinic(ocena, patient_id, clinic_id) VALUES (3, 5, 1);

INSERT INTO drug(name, price, quantity) VALUES ("Brufen", 259, 100);
INSERT INTO drug(name, price, quantity) VALUES ("Fervex", 550, 200);
INSERT INTO drug(name, price, quantity) VALUES ("Paracetamol", 550, 200);
INSERT INTO drug(name, price, quantity) VALUES ("Febricet", 550, 200);

INSERT INTO recipe(authenticated, description, appointment_id, version) VALUES (false, "Dva puta na dan po dve tablete", 2,0);
INSERT INTO recipe(authenticated, description, version) VALUES (false, "Dnevno tri kesice",0);
INSERT INTO recipe(authenticated, description, version) VALUES (false, "Dnevno jedna kesica",0);
INSERT INTO recipe(authenticated, description, version) VALUES (true, "Dnevno dve kesice",0);

INSERT INTO recipe_drug(recipe_id, drug_id) VALUES (1,1);
INSERT INTO recipe_drug(recipe_id, drug_id) VALUES (2,2);
INSERT INTO recipe_drug(recipe_id, drug_id) VALUES (3,2);
INSERT INTO recipe_drug(recipe_id, drug_id) VALUES (4,2);

INSERT INTO appointment_type(name) VALUES ("Kardioloski");
INSERT INTO appointment_type(name) VALUES ("Stomatoloski");
INSERT INTO appointment_type(name) VALUES ("Ginekoloski");
INSERT INTO appointment_type(name) VALUES ("Oftamoloski");
INSERT INTO appointment_type(name) VALUES ("Opsti");
INSERT INTO appointment_type(name) VALUES ("Hirurski");
INSERT INTO appointment_type(name) VALUES ("Specijalisticki");

INSERT INTO clinic_clinic_administrator(clinic_id, clinic_administrator_id) VALUES (1,1);

INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,1);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,2);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (1,3);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (2,4);
INSERT INTO clinic_hospital_rooms(clinic_id, hospital_rooms_id) VALUES (2,5);

INSERT INTO diagnosis(description, name) VALUE ("Temperatura", "Upala krajnika");
INSERT INTO diagnosis(description, name) VALUE ("Kasalj", "Korona");
INSERT INTO diagnosis(description, name) VALUE ("Temperatura", "Korona");
INSERT INTO diagnosis(description, name) VALUE ("Upala krajnika", "Bakterijska infekcija");

INSERT INTO price_list(price,appointment_type_id,clinic_id) values (1200,1,1);
INSERT INTO price_list(price,appointment_type_id,clinic_id) values (1000,2,1);
INSERT INTO price_list(price,appointment_type_id,clinic_id) values (3000,3,1);
INSERT INTO price_list(price,appointment_type_id,clinic_id) values (2000,4,1);

INSERT INTO appointment_type_price_list(appointment_type_id,price_list_id) values (1,1);
insert  into clinic_price_list(clinic_id,price_list_id) values (1,1);

INSERT INTO appointment_type_price_list(appointment_type_id,price_list_id) values (2,2);
insert  into clinic_price_list(clinic_id,price_list_id) values (1,2);

INSERT INTO appointment_type_price_list(appointment_type_id,price_list_id) values (3,3);
insert  into clinic_price_list(clinic_id,price_list_id) values (1,3);

INSERT INTO appointment_type_price_list(appointment_type_id,price_list_id) values (4,4);
insert  into clinic_price_list(clinic_id,price_list_id) values (1,4);

insert into appointment_type_appointments(appointment_type_id,appointments_id) values(1,1);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(1,2);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(1,3);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(2,4);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(1,5);

insert into appointment_type_appointments(appointment_type_id,appointments_id) values(4,6);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(4,7);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(3,8);
insert into appointment_type_appointments(appointment_type_id,appointments_id) values(1,9);

INSERT INTO holiday_request(version, finished, confirmed, date_end, date_start, medical_staff_id) values (0, false, false, '2020-03-16', '2020-02-10', 2);