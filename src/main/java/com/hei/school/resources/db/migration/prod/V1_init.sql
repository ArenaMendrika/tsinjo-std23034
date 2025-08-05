CREATE TABLE donor (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255),
                       full_name VARCHAR(255)
);

CREATE TABLE beneficiary (
                             id SERIAL PRIMARY KEY,
                             email VARCHAR(255),
                             full_name VARCHAR(255)
);

CREATE TABLE payment (
                         id SERIAL PRIMARY KEY,
                         psp_payment_id VARCHAR(255),
                         psp_type VARCHAR(255),
                         amount INT,
                         status VARCHAR(20),
                         creation_date TIMESTAMP
);

CREATE TABLE donation (
                          id SERIAL PRIMARY KEY,
                          donor_id INT REFERENCES donor(id),
                          payment_id INT REFERENCES payment(id)
);

CREATE TABLE help (
                      id SERIAL PRIMARY KEY,
                      beneficiary_id INT REFERENCES beneficiary(id),
                      payment_id INT REFERENCES payment(id),
                      description TEXT
);

-- Insérer un donneur
INSERT INTO donor (email, full_name)
VALUES ('sarah.rakoto@donor.org', 'Sarah Rakotomalala');

-- Insérer un paiement pour un don
INSERT INTO payment (
    psp_payment_id,
    psp_type,
    amount,
    status,
    creation_date
) VALUES (
             'MP250901.0810.D84213',
             'MVola',
             75000,
             'SUCCEEDED',
             '2025-09-01 08:10:00'
         );

INSERT INTO donation (donor_id, payment_id)
VALUES (1, 1);

INSERT INTO beneficiary (email, full_name)
VALUES ('rina.malagasy@helpme.mg', 'Rina Malagasy');

INSERT INTO payment (
    psp_payment_id,
    psp_type,
    amount,
    status,
    creation_date
) VALUES (
             'MP250902.1015.E57290',
             'Airtel Money',
             150000,
             'SUCCEEDED',
             '2025-09-02 10:15:00'
         );

INSERT INTO help (beneficiary_id, payment_id, description)
VALUES (1, 2, 'Rina a besoin d’un traitement urgent pour une infection grave.');
