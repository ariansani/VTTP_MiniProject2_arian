use miniproject;


CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(256) NOT NULL,
    email VARCHAR(64) NOT NULL,
    role VARCHAR(10),
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    user_id INT NOT NULL ,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id,role_id),
     FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles( name) VALUES('ROLE_USER');
INSERT INTO roles( name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

CREATE TABLE gym(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    location VARCHAR(256) NOT NULL,
    route_reset DATE,
    latitude VARCHAR(64),
    longitude VARCHAR(64),
    PRIMARY KEY (id)
);


INSERT INTO gym(name, location, route_reset, latitude, longitude) values('Boulder World (Paya Lebar)', 'East', CURDATE(), '1.3195510396636287','103.89510830738271');
INSERT INTO gym(name, location, route_reset, latitude, longitude) values('Boulder Planet (Sembawang)', 'North', CURDATE(),'1.4431114944200985', '103.82536810738752');
INSERT INTO gym(name, location, route_reset, latitude, longitude) values('Boruda Climbing (Alexandra)', 'South', CURDATE(),'1.2749626421517632', '103.8036534');
INSERT INTO gym(name, location, route_reset, latitude, longitude) values('Boulder Plus+ (Jurong)', 'West', CURDATE(),'1.3312419919045406', '103.74823265311768');
CREATE TABLE package(
	uuid VARCHAR(10) NOT NULL,
    gym_id INT NOT NULL,
	user_id INT NOT NULL,
    entry_passes INT NOT NULL,
    expiry_date DATE,
    expired BOOL,
    PRIMARY KEY (uuid),
   FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (gym_id) REFERENCES gym (id)
);

