INSERT INTO public.m_location (id, country, city, street, house, apartment, is_deleted) VALUES (1, 'Belarus', 'Minsk', null, null, null, false);
INSERT INTO public.m_location (id, country, city, street, house, apartment, is_deleted) VALUES (2, 'Belarus', 'Brest', null, null, null, false);
INSERT INTO public.m_location (id, country, city, street, house, apartment, is_deleted) VALUES (3, 'Belarus', 'Gomel', null, null, null, false);
INSERT INTO public.m_location (id, country, city, street, house, apartment, is_deleted) VALUES (4, 'Belarus', 'Borisov', null, null, null, false);
INSERT INTO public.m_location (id, country, city, street, house, apartment, is_deleted) VALUES (7, 'Great Britain', 'Edinburg', 'Shakespear str', '12', '453', false);
INSERT INTO public.m_location (id, country, city, street, house, apartment, is_deleted) VALUES (8, 'Country', 'New City', '', '', '', true);
INSERT INTO public.m_location (id, country, city, street, house, apartment, is_deleted) VALUES (9, 'Country', 'New City', '', '', '', true);
INSERT INTO public.m_location (id, country, city, street, house, apartment, is_deleted) VALUES (11, 'Belarus', 'Minsk', 'Nezavisimosti', '12123', '123', false);
INSERT INTO public.m_location (id, country, city, street, house, apartment, is_deleted) VALUES (10, 'India', 'Minsk', 'Nezavisimosti', '12123', '123', true);

INSERT INTO public.m_office (id, office_name, phone_number, location_id) VALUES (1, 'Office1', '121212313', 1);

INSERT INTO public.m_car_model (id, brand, model, color, capacity, transmission, fuel_type) VALUES (1, 'Audi', 'A5', 'Red', 3, '234', '23');

INSERT INTO public.m_cars (id, registration_number, model_id, price_per_day, office_id, availability_status) VALUES (18, '-6822102194267453545', 1, 5, 1, 'AVAILABLE');
INSERT INTO public.m_cars (id, registration_number, model_id, price_per_day, office_id, availability_status) VALUES (21, '2281488', 1, 1488, 1, 'AVAILABLE');
INSERT INTO public.m_cars (id, registration_number, model_id, price_per_day, office_id, availability_status) VALUES (23, 'newRegNumber', 1, 40, 1, 'AVAILABLE');
INSERT INTO public.m_cars (id, registration_number, model_id, price_per_day, office_id, availability_status) VALUES (20, 'gtyttytyt', 1, 10, 1, 'AVAILABLE');
INSERT INTO public.m_cars (id, registration_number, model_id, price_per_day, office_id, availability_status) VALUES (14, 'htdmytdmjfj,ufkil', 1, 0, 1, 'AVAILABLE');
INSERT INTO public.m_cars (id, registration_number, model_id, price_per_day, office_id, availability_status) VALUES (16, '6550629437262074595', 1, 5, 1, 'AVAILABLE');
INSERT INTO public.m_cars (id, registration_number, model_id, price_per_day, office_id, availability_status) VALUES (17, '6134168863457573000', 1, 5, 1, 'EXPIRED');
INSERT INTO public.m_cars (id, registration_number, model_id, price_per_day, office_id, availability_status) VALUES (15, '5118358169546642839', 1, 5, 1, 'AVAILABLE');

INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (49, 'Tanya', 'Murashka', '123123123123', '12312312312', 'tanyamur', '123123', '2020-07-31 23:31:39.353000', '2020-07-31 23:31:39.354000', 4, true, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (56, 'Tanya', 'user1', '1234567890', 'string', 'user11', '111', '2020-08-02 21:27:43.483000', '2020-08-02 21:32:24.174000', 4, true, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (57, 'Ivan', 'Sidorov', '1231231231', 'string', 'ivansid', '111', '2020-08-02 22:01:02.117000', '2020-08-02 22:01:55.633000', 1, true, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (58, 'create', 'test', '1234567890', 'string', 'string', 'string', '2020-08-03 02:16:36.326000', '2020-08-03 02:16:36.326000', 1, false, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (50, 'Johns', 'Brown', '375291111111', '131212111', 'johnsbr', '111111', '2020-08-01 00:36:05.410000', '2020-08-01 00:36:05.411000', 4, true, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (51, 'Alex', 'Miller', '123456789012', 'sdsdfsfsdsd', 'alexmil', 'Admin123', '2020-08-01 04:15:15.984000', '2020-08-01 18:20:38.329000', 4, false, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (52, 'Jack', 'Davis', '123123123123', 'sdsdfsfsdsd', 'Admin123', 'Admin123', '2020-08-01 04:15:15.984000', '2020-08-01 04:15:15.984000', 4, false, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (53, 'Alexandr', 'Ignatov', '111111111111', 'wefewggewe', 'alexandrig', '123123', '2020-08-01 17:53:13.217000', '2020-08-01 17:53:25.344000', 4, false, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (54, 'Darya', 'Ivanova', '111111111111', 'wefewggewe', 'daryaiv', '123123', '2020-08-01 17:55:00.367000', '2020-08-01 17:55:00.367000', 4, false, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (55, 'Lena', 'Smirnova', '111111111111', 'wefewggewe', 'lenasmir', '123123', '2020-08-01 18:00:30.675000', '2020-08-01 18:00:30.675000', 4, false, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (47, 'Alex', 'Smith', '123456789012', 'string', 'alexsmith', 'ertertert', '2020-07-26 01:10:57.601000', '2020-07-26 01:10:57.601000', 4, true, 'NOT_SELECTED');
INSERT INTO public.m_users (id, first_name, last_name, phone_number, passport_data, login, password, created, changed, location_id, is_deleted, gender) VALUES (44, 'Nick', 'Williams', '123456789012', 'string', 'nickwill', 'ertertert', '2020-07-26 00:51:51.418000', '2020-07-26 00:51:51.418000', 4, true, 'MALE');

INSERT INTO public.m_roles (id, role_name, user_id) VALUES (14, 'ROLE_USER', 44);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (17, 'ROLE_USER', 47);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (19, 'ROLE_USER', 49);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (20, 'ROLE_USER', 50);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (22, 'ROLE_USER', 53);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (23, 'ROLE_USER', 54);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (24, 'ROLE_USER', 55);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (25, 'ROLE_USER', 56);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (26, 'ROLE_USER', 57);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (21, 'ROLE_USER', 51);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (27, 'ROLE_ADMIN', 52);
INSERT INTO public.m_roles (id, role_name, user_id) VALUES (28, 'ROLE_USER', 58);

INSERT INTO public.m_rent (id, user_id, car_id, rent_start_date, rent_end_date, created, changed, rent_price, rent_status) VALUES (26, 47, 17, '2020-07-01 15:42:34.467000', '2020-08-01 15:42:34.467000', '2020-08-01 18:55:38.371000', '2020-08-01 18:55:38.371000', 328, 'CLOSED');
INSERT INTO public.m_rent (id, user_id, car_id, rent_start_date, rent_end_date, created, changed, rent_price, rent_status) VALUES (27, 51, 17, '2020-08-01 18:56:32.309000', '2020-08-01 18:56:32.309000', '2020-08-01 22:42:01.386000', '2020-08-01 22:42:01.386000', 0, 'CLOSED');
INSERT INTO public.m_rent (id, user_id, car_id, rent_start_date, rent_end_date, created, changed, rent_price, rent_status) VALUES (28, 51, 17, '2020-08-01 18:56:32.309000', '2020-08-01 18:56:32.309000', '2020-08-01 23:01:28.484000', '2020-08-01 23:01:28.484000', 0, 'CLOSED');
INSERT INTO public.m_rent (id, user_id, car_id, rent_start_date, rent_end_date, created, changed, rent_price, rent_status) VALUES (29, 50, 18, '2020-01-02 20:24:23.154000', '2020-08-02 20:24:23.154000', '2020-08-02 21:36:16.276000', '2020-08-02 23:39:05.501000', 10, 'CLOSED');