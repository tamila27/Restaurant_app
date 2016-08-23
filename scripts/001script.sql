create database RESTAURANT;

create table EMPLOYEE(
	ID int primary key not null,
	LAST_NAME varchar(20) not null,
	NAME varchar(20) not null,
	BIRTH_DATE date,
	PHONE varchar(15),
	POSITION int not null,
	SALARY real
);

create table INGREDIENT(
	ID int primary key not null,
	TITLE varchar(20) not null
);

create table STORAGE(
	ID int primary key not null,
	INGREDIENT_ID int not null,
	QUANTITY int not null
);

alter table STORAGE add constraint INGREDIENT_ID_FK foreign key (INGREDIENT_ID) references INGREDIENT (ID);

insert into INGREDIENT values(1, 'cheese' );
insert into INGREDIENT values(2, 'potato' );
insert into INGREDIENT values(3, 'tomato' );

insert into STORAGE values(1, 1, 5);
insert into STORAGE values(2, 3, 6);
insert into STORAGE values(3, 2, 7);

create table DISH(
	ID int primary key not null,
	NAME varchar(20) not null,
	CATEGORY int not null,
	PRICE real,
	WEIGHT real
);


create table DISHES_INGREDIENTS(
	INGREDIENT_ID int not null,
	DISH_ID int not null,
	primary key(INGREDIENT_ID, DISH_ID)
);

alter table DISHES_INGREDIENTS add constraint DISH_INGR_INGR_FK foreign key (INGREDIENT_ID) references INGREDIENT (ID);

alter table DISHES_INGREDIENTS add constraint DISH_INGR_DISH_FK foreign key (DISH_ID) references DISH (ID);

create table MENU(
	ID int primary key not null,
	NAME varchar(20) not null
);

create table MENU_DISH(
	MENU_ID int not null,
	DISH_ID int not null,
	primary key(MENU_ID, DISH_ID)
);

alter table MENU_DISH add constraint MENU_DISH_MENU_FK foreign key (MENU_ID) references MENU (ID);

alter table MENU_DISH add constraint MENU_DISH_DISH_FK foreign key (DISH_ID) references DISH (ID);

create table ORDERS(
	ID int primary key not null,
	WAITER_ID int not null,
	TABLE_NUM int,
	ORD_DATE date
);

alter table ORDERS add constraint WAITER_FK foreign key(WAITER_ID) references EMPLOYEE(ID);

create table ORDER_DISH(
	ORDER_ID int not null,
	DISH_ID int not null,
	primary key(ORDER_ID, DISH_ID)
);

alter table ORDER_DISH add constraint ORD_DISH_ORD_FK foreign key(ORDER_ID) references ORDERS(ID);

alter table ORDER_DISH add constraint ORD_DISH_DISH_FK foreign key(DISH_ID) references DISH(ID);

create table PREPARED_DISH(
	ID int not null primary key,
	DISH_ID int not null,
	COOK_ID int not null,
	ORDER_ID int,
	DATE date
);

alter table PREPARED_DISH add constraint PREP_DISH_DISH_FK foreign key (DISH_ID) references DISH(ID);

alter table PREPARED_DISH add constraint PREP_DISH_COOK_FK foreign key (COOK_ID) references EMPLOYEE(ID);

alter table PREPARED_DISH add constraint PREP_DISH_ORD_FK foreign key (ORDER_ID) references ORDERS(ID);

alter table DISH modify CATEGORY int not null;

create table CATEGORY(
	ID int primary key not null,
	NAME varchar(20) not null
);

alter table DISH add constraint CATEGORY_FK foreign key (CATEGORY) references CATEGORY(ID);

create table POSITIONS(
	ID int primary key not null,
	TITLE varchar(20) not null
);

alter table EMPLOYEE add constraint POSITION_FK foreign key (POSITION) references POSITIONS(ID);

insert into CATEGORY values(0, 'drink');
insert into CATEGORY values(1, 'salad');
insert into CATEGORY values(2, 'hot dish');

insert into INGREDIENT values(0, 'apple');
insert into INGREDIENT values(4, 'flour');
insert into INGREDIENT values(5, 'meat');
insert into INGREDIENT values(6, 'mushrooms');

insert into DISH values(0, 'pizza', 2, 2.0, 200.0);
insert into DISH values(1, 'apple juice', 0, 0.5, 100.0);

insert into DISHES_INGREDIENTS values(0, 1);
insert into DISHES_INGREDIENTS values(1, 0);
insert into DISHES_INGREDIENTS values(3, 0);
insert into DISHES_INGREDIENTS values(4, 0);
insert into DISHES_INGREDIENTS values(5, 0);
insert into DISHES_INGREDIENTS values(6, 0);

insert into POSITIONS values(0, 'cook');
insert into POSITIONS values(1, 'waiter');

insert into EMPLOYEE values(0, 'Smith', 'Ann', '1980-10-21', '02134556789', 0, 100000.0 );
insert into EMPLOYEE values(1, 'Johnson', 'Alan', '1994-06-15', '02112345678', 1, 50000.0 );

insert into MENU values(0, 'breakfast');
insert into MENU values(1, 'dinner');

insert into MENU_DISH values(0, 1);
insert into MENU_DISH values(1, 0);

insert into ORDERS values(0, 1, 1, '2016-01-01');
insert into ORDERS values(1, 1, 2, '2016-05-01');

insert into ORDER_DISH values(0, 0);
insert into ORDER_DISH values(0, 1);
insert into ORDER_DISH values(1, 0);