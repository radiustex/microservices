INSERT INTO users (age, name, last_name, login, password)
VALUES
    (25, 'Петя', 'Иванов', 'petya', '{noop}12345'),
    (30, 'Валентин', 'Григорьев', 'valya', '{noop}12345'),
    (30, 'Егор', 'Егоров', 'egor', '{noop}12345'),
    (30, 'Igor', 'Сидорова', 'igor', '{noop}12345');

INSERT INTO roles (role_name)
values
    ('ROLE_ADMIN'),
    ('ROLE_USER');
INSERT INTO users_roles (user_id, role_id)
values
    (1, 1),
    (1, 2),
    (2,2),
    (2, 1),
    (3, 2),
    (4, 2);
