
create table seller
	(seller_name   varchar(12),
	 primary key(seller_name)
	);	

create table all_orders
	(name varchar(16),
	 address varchar(30),
	 num_shredded numeric(4,0) DEFAULT 0,
	 num_nugget numeric(4,0) DEFAULT 0,
	 num_black numeric(4,0) DEFAULT 0,
	 num_red numeric(4,0) DEFAULT 0,
	 seller varchar(12),
	 amount_due varchar(12),
	 primary key(name,address,amount_due)
	);
	
create table orders_big_bird_forest
	(order_id numeric(4,0),
	 name varchar(16),
	 address varchar(30),
	 num_shredded numeric(4,0),
	 num_nugget numeric(4,0),
	 num_black numeric(4,0),
	 num_red numeric(4,0),
	 seller varchar(12),
	 amount_due varchar(12),
	 primary key(order_id,name,address)
	);
	
create table orders_apple_orchard
	(order_id numeric(4,0),
	 name varchar(16),
	 address varchar(30),
	 num_shredded numeric(4,0),
	 num_nugget numeric(4,0),
	 num_black numeric(4,0),
	 num_red numeric(4,0),
	 seller varchar(12),
	 amount_due varchar(12),
	 primary key(order_id,name,address)
	);
	
create table orders_eumc
	(order_id numeric(4,0),
	 name varchar(16),
	 address varchar(30),
	 num_shredded numeric(4,0),
	 num_nugget numeric(4,0),
	 num_black numeric(4,0),
	 num_red numeric(4,0),
	 seller varchar(12),
	 amount_due varchar(12),
	 primary key(order_id,name,address)
	);
	
create table orders_harry_halls
	(order_id numeric(4,0),
	 name varchar(16),
	 address varchar(30),
	 num_shredded numeric(4,0),
	 num_nugget numeric(4,0),
	 num_black numeric(4,0),
	 num_red numeric(4,0),
	 seller varchar(12),
	 amount_due varchar(12),
	 primary key(order_id,name,address)
	);
	
create table orders_harry_village
	(order_id numeric(4,0),
	 name varchar(16),
	 address varchar(30),
	 num_shredded numeric(4,0),
	 num_nugget numeric(4,0),
	 num_black numeric(4,0),
	 num_red numeric(4,0),
	 seller varchar(12),
	 amount_due varchar(12),
	 primary key(order_id,name,address)
	);
	 
create table orders_savages
	(order_id numeric(4,0),
	 name varchar(16),
	 address varchar(30),
	 num_shredded numeric(4,0),
	 num_nugget numeric(4,0),
	 num_black numeric(4,0),
	 num_red numeric(4,0),
	 seller varchar(12),
	 amount_due varchar(12),
	 primary key(order_id,name,address)
	);
	
create table orders_saints
	(order_id numeric(4,0),
	 name varchar(16),
	 address varchar(30),
	 num_shredded numeric(4,0),
	 num_nugget numeric(4,0),
	 num_black numeric(4,0),
	 num_red numeric(4,0),
	 seller varchar(12),
	 amount_due varchar(12),
	 primary key(order_id,name,address)
	);