-- TABLE SOCIETE
create table company ( 
    id serial  primary key, 
    name varchar(50) NOT NULL,
    slogan varchar(255) ,
    min_age integer NOT NULL,
    max_age integer NOT NULL,
    summary text 
);
-- TABLE THEME DE COURS

create table courseTheme ( 
    id serial  primary key, 
    name varchar(50) NOT NULL,
    company_id integer NOT NULL,
    description text,
    image_path varchar(255),
    foreign key (company_id) references company(id)
);

-- TABLE  COURS
create table course(
    id serial  primary key, 
    courseTheme_id integer NOT NULL,
    title varchar(50)
    description text,
    video_path varchar(255) NOT NULL,
    thumbnail varchar(255)
    foreign key (courseTheme_id) references courseTheme(id),
);

-- TABLE  CATEGORIE  DES ASSET

create table assetCategory ( 
    id serial  primary key, 
    name varchar(50) NOT NULL,
    description text  
);

-- TABLE  TYPE DES ASSET

create table assetType ( 
    id serial  primary key, 
    name varchar(50) NOT NULL 
);
-- TABLE ASSET

create table asset(
    id serial  primary key, 
    company_id integer NOT NULL,
    assetType_id varchar(25)NOT NULL,
    file_path varchar(255)NOT NULL,
    media_title varchar(50)NOT NULL,
    description text NOT NULL,
    assetCategory_id integer NOT NULL,
    created_at date NOT NULL,
    foreign key (company_id) references company(id),
    foreign key (category_id) references assetCategory(id),
    foreign key (assetType_id) references assetType(id)
);

-- DONNÉE TEST SOCIETE
INSERT into company (name,slogan,min_age,max_age,summary) VALUES ( 'Ludikids','Grandir Malin',3,8, 'Ludikids est une chaîne de télévision linéaire éducative et pédagogique qui propose  aux enfants âgés de 3 à 8 ans des programmes encadrés, ludiques et éducatifs mettant l''accent sur l''anglais et l''alphabétisation, les maths et l''arithmétique, les sciences et le monde autour de nous, l''apprentissage social et émotionnel' );

-- DONNÉE TEST COURSE THEME

INSERT into courseTheme (name,company_id,description,image_path) VALUES ('Les Maths et Arithmétique',1,'Avec ludikids, les enfants seront exposés aux nombres, aux formes, aux calculs, aux concepts d''additions et de soustractions.','https://ludikids.com/wp-content/uploads/2020/03/Layer-4.png');
INSERT into courseTheme (name,company_id,description,image_path) VALUES ('Sciences et Monde Autour De Nous',1,'Avec ludikids, les enfants acquerront de la connaissance sur les sciences, la nature, la technologie, le monde environnant et sur la façon dont les choses fonctionnent.','https://ludikids.com/wp-content/uploads/2020/03/Layer-2.png');
INSERT into courseTheme (name,company_id,description,image_path) VALUES ('L''apprentissage Social et émotionnel',1,'Ludikids introduit des programmes pour encourager l''apprentissage social et émotionnel essentiel au développement de l''enfant.','https://ludikids.com/wp-content/uploads/2020/03/Layer-3.png');
INSERT into courseTheme (name,company_id,description,image_path) VALUES ('Anglais et Alphabétisation',1,'ludikids introduit l''alphabétisation française et des compétences en anglais telles que la phonétique, les lettres et les mots.','https://ludikids.com/wp-content/uploads/2020/03/Layer-1.png');

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