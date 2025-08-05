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

INSERT INTO donor (email, full_name)
VALUES ('lou@hei.school', 'Lou Andria');

INSERT INTO payment (
    psp_payment_id,
    psp_type,
    amount,
    status,
    creation_date
) VALUES (
             'MP250804.0904.A01637',
             'Orange Money',
             50000,
             'VERIFYING',
             '2025-08-11 10:15:00'
         );

INSERT INTO donation (donor_id, payment_id)
VALUES (1, 1);


-- Insérer un bénéficiaire
INSERT INTO beneficiary (email, full_name) VALUES ('koto@kely.mg', 'Koto Kely');

-- Insérer un paiement pour l'aide
INSERT INTO payment (
    psp_payment_id,
    psp_type,
    amount,
    status,
    creation_date
) VALUES (
             'MP250804.1224.B31974',
             'Orange Money',
             500000,
             'SUCCEEDED',
             '2025-08-12 14:30:00'
         );

INSERT INTO help (beneficiary_id, payment_id, description)
VALUES (1, 2, 'Koto a été renversé par une moto et nécessite une chirurgie.');
