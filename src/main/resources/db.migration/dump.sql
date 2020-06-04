create table m_users
(
	id bigserial not null
		constraint m_customers_pk
			primary key,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	phone_number varchar(50),
	passport_data varchar(255)
);

alter table m_users owner to postgres;

create unique index m_customers_passport_data_uindex
	on m_users (passport_data);

create table m_car_model
(
	id bigserial not null
		constraint car_model_pk
			primary key,
	brand varchar(100) not null,
	model varchar(100) not null,
	color varchar(50),
	capacity integer,
	transmission varchar(50),
	fuel_type varchar(50)
);

alter table m_car_model owner to postgres;

create table m_rental_office
(
	id bigserial not null
		constraint rental_company_pk
			primary key,
	office_name varchar(100) not null,
	phone_number varchar(50)
);

alter table m_rental_office owner to postgres;

create table m_cars
(
	id bigserial not null
		constraint m_car_pk
			primary key,
	registration_number varchar(100) not null,
	model_id bigint not null
		constraint m_cars_m_car_model_id_fk
			references m_car_model
				on update cascade on delete set null,
	price_per_day double precision not null,
	office_id bigint
		constraint m_cars_m_rental_office_id_fk
			references m_rental_office
				on update set null on delete set null,
	availability_status varchar(50) not null,
	insurance_cost double precision
);

alter table m_cars owner to postgres;

create table m_rental
(
	id bigserial not null
		constraint m_bookings_pk
			primary key,
	customer_id bigint not null
		constraint m_booking_m_customers_id_fk
			references m_users
				on update cascade on delete set null,
	car_id bigint not null
		constraint m_booking_m_cars_id_fk
			references m_cars
				on update cascade on delete set null,
	rent_start_date timestamp(6) not null,
	rent_end_date timestamp(6) not null,
	created timestamp(6) not null,
	changed timestamp(6) not null,
	rent_price double precision not null,
	rent_status varchar(50) not null
);

alter table m_rental owner to postgres;

create index m_booking_rent_end_date_index
	on m_rental (rent_end_date);

create index m_booking_rent_start_date_index
	on m_rental (rent_start_date);

create unique index m_cars_registration_number_uindex
	on m_cars (registration_number);

create table m_invoice
(
	id bigserial not null
		constraint m_account_pk
			primary key,
	owner_id bigint not null
		constraint m_account_m_customers_id_fk
			references m_users
				on update cascade on delete set null,
	amount double precision not null,
	other_details integer
);

alter table m_invoice owner to postgres;

create table m_payments
(
	id bigserial not null
		constraint m_payments_pk
			primary key,
	invoice_id bigint not null
		constraint m_payments_m_invoice_id_fk
			references m_invoice
				on update cascade on delete set null,
	payment_date timestamp(6),
	payment_amount double precision not null
);

alter table m_payments owner to postgres;

create table m_address
(
	id bigserial not null
		constraint m_address_pk
			primary key,
	country varchar(100) not null,
	region varchar(100),
	city varchar(100),
	street varchar(200),
	house varchar(10),
	apt varchar(10),
	owner_id bigint not null
		constraint m_address_m_rental_office_id_fk
			references m_rental_office
				on update cascade on delete cascade
);

alter table m_address owner to postgres;

create index m_address_city_index
	on m_address (city);

create index m_address_country_index
	on m_address (country);

create table m_roles
(
	id bigserial not null
		constraint m_roles_pk
			primary key,
	role_name varchar(50) not null,
	user_id bigint not null
		constraint m_roles_m_users_id_fk
			references m_users
				on update cascade on delete cascade
);

alter table m_roles owner to postgres;

create table m_location
(
	id bigserial not null
		constraint m_location_pk
			primary key,
	country varchar(50) not null,
	city varchar(50) not null
);

alter table m_location owner to postgres;

