-- TABLE SOCIETE
create table company ( 
    company_id serial  primary key, 
    name varchar(50) NOT NULL,
    slogan varchar(255) ,
    min_age integer NOT NULL,
    max_age integer NOT NULL,
    summary text 
);

-- TABLE CATEGORIE 

create table category ( 
    category_id serial  primary key, 
    name varchar(50) NOT NULL,
    description text  
);

-- TABLE ASSET

create table asset(
    asset_id serial  primary key, 
    company_id integer NOT NULL,
    hashcode varchar(64) unique NOT NULL,
    type varchar(25)NOT NULL,
    file_name varchar(100)NOT NULL,
    file_path varchar(100)NOT NULL,
    extension varchar(50),
    media_title varchar(50)NOT NULL,
    description text NOT NULL,
    category_id integer NOT NULL,
    created_at date NOT NULL,
    foreign key (company_id) references company(company_id),
    foreign key (category_id) referencha1es category(category_id)
);

-- DONNÉE TEST SOCIETE
INSERT into company (name,slogan,min_age,max_age,summary) VALUES ( 'Ludikids','Grandir Malin',3,8, 'Ludikids est une chaîne de télévision linéaire éducative et pédagogique qui propose  aux enfants âgés de 3 à 8 ans des programmes encadrés, ludiques et éducatifs mettant l''accent sur l''anglais et l''alphabétisation, les maths et l''arithmétique, les sciences et le monde autour de nous, l''apprentissage social et émotionnel' );

-- DONNÉE TEST CATEGORIE
INSERT into category (name,description) VALUES ('Les Maths et Arithmétique','Avec ludikids, les enfants seront exposés aux nombres, aux formes, aux calculs, aux concepts d''additions et de soustractions.');

-- DONNÉE TEST ASSET
INSERT into asset (company_id,hashcode,type,file_name,file_path,extension,media_title,description,category_id,created_at) VALUES 
    (
        1,
        'd4c1bce57c3d797dd63389bb3167ff75e5bbbe2ff5a67c1e3efb55207111d6ad',
        'video',
        'testVideo',
        'asset/video/',
        'MIM',
        'Video test',
        'un petit test video',
        1,
        now()
    );