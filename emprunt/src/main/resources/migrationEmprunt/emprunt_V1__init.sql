CREATE TABLE emprunt (
                         id SERIAL NOT NULL PRIMARY KEY,
                         id_emprunteur BIGINT,
                         id_livre BIGINT,
                         date_debut DATE,
                         date_fin DATE
);
