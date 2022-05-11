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
    description text NOT NULL
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
    foreign key (category_id) references category(category_id)
);
INSERT into company (name,slogan,min_age,max_age,summary) VALUES ( 'Ludikids',"Grandir Malin",3,8, "Ludikids est une chaîne de télévision linéaire éducative et pédagogique qui propose  aux enfants âgés de 3 à 8 ans des programmes encadrés, ludiques et éducatifs mettant l’accent sur l’anglais et l’alphabétisation, les maths et l’arithmétique, les sciences et le monde autour de nous, l’apprentissage social et émotionnel" );
