CREATE TABLE livre (
    id SERIAL NOT NULL PRIMARY KEY,
    titre_livre VARCHAR(50),
    nom_auteur VARCHAR(50),
    nbr_exemplaire INT
);
