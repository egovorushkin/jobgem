-- Grant the 'root' userEntity superuser privileges
ALTER USER jobgemadmin WITH SUPERUSER;

-- Connect to the 'jobgem' database
\connect jobgem;

-- Create tables
CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    password      VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    first_name    VARCHAR(50),
    last_name     VARCHAR(50),
    date_of_birth DATE,
    phone_number  VARCHAR(20)
);

CREATE TABLE user_roles
(
    user_id INT,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE companies
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    website_url VARCHAR(200),
    logo_url    VARCHAR(200),
    rating      DECIMAL(3, 2)
);

CREATE TABLE jobs
(
    id              SERIAL PRIMARY KEY,
    title           VARCHAR(100) NOT NULL,
    description     TEXT,
    company_id      INT,
    salary_min      DECIMAL(10, 2),
    salary_max      DECIMAL(10, 2),
    post_date       DATE,
    expiration_date DATE,
    job_type        VARCHAR(50),
    location        VARCHAR(100),
    FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TABLE resumes
(
    id         SERIAL PRIMARY KEY,
    user_id    INT,
    title      VARCHAR(100) NOT NULL,
    file_url   VARCHAR(200),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE job_applications
(
    id               SERIAL PRIMARY KEY,
    user_id          INT,
    job_id           INT,
    resume_id        INT,
    application_date TIMESTAMP,
    status           VARCHAR(50),
    cover_letter     TEXT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (job_id) REFERENCES jobs (id),
    FOREIGN KEY (resume_id) REFERENCES resumes (id)
);

CREATE TABLE skills
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_skills
(
    user_id  INT,
    skill_id INT,
    PRIMARY KEY (user_id, skill_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (skill_id) REFERENCES skills (id)
);

CREATE TABLE saved_jobs
(
    id       SERIAL PRIMARY KEY,
    user_id  INT,
    job_id   INT,
    saved_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (job_id) REFERENCES jobs (id)
);

-- Insert test data
INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_COMPANY'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, email, first_name, last_name, date_of_birth, phone_number)
VALUES ('john_doe', '$2a$10$Xt5wUUwNs8X9q8.yY7GX7O1HS5cPFJhGmEIvBn7FAT3BzWcyhL3.6', 'john@example.com', 'John', 'Doe',
        '1990-01-15', '1234567890'),
       ('jane_smith', '$2a$10$Xt5wUUwNs8X9q8.yY7GX7O1HS5cPFJhGmEIvBn7FAT3BzWcyhL3.6', 'jane@example.com', 'Jane',
        'Smith', '1992-05-20', '9876543210'),
       ('company_user', '$2a$10$Xt5wUUwNs8X9q8.yY7GX7O1HS5cPFJhGmEIvBn7FAT3BzWcyhL3.6', 'company@example.com',
        'Company', 'User', '1985-10-01', '5555555555');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (3, 2);

INSERT INTO companies (name, description, website_url, logo_url, rating)
VALUES ('Tech Corp', 'A leading technology company', 'https://techcorp.com', 'https://techcorp.com/logo.png', 4.5),
       ('Finance Inc', 'A reputable financial services provider', 'https://financeinc.com',
        'https://financeinc.com/logo.png', 4.2);

INSERT INTO jobs (title, description, company_id, salary_min, salary_max, post_date, expiration_date, job_type,
                  location)
VALUES ('Software Engineer', 'Developing cutting-edge software solutions', 1, 70000, 120000, '2023-05-01', '2023-06-30',
        'FULL_TIME', 'New York, NY'),
       ('Financial Analyst', 'Analyzing financial data and creating reports', 2, 60000, 100000, '2023-05-05',
        '2023-07-05', 'FULL_TIME', 'Chicago, IL'),
       ('UX Designer', 'Creating user-friendly interfaces', 1, 65000, 110000, '2023-05-10', '2023-07-10', 'FULL_TIME',
        'San Francisco, CA');

INSERT INTO resumes (user_id, title, file_url, created_at, updated_at)
VALUES (1, 'John Doe Resume', 'https://storage.com/resume1.pdf', '2023-05-02 10:00:00', '2023-05-02 10:00:00'),
       (2, 'Jane Smith Resume', 'https://storage.com/resume2.pdf', '2023-05-03 11:00:00', '2023-05-03 11:00:00');

INSERT INTO job_applications (user_id, job_id, resume_id, application_date, status, cover_letter)
VALUES (1, 1, 1, '2023-05-10 14:30:00', 'APPLIED', 'I am excited to apply for this position...'),
       (2, 2, 2, '2023-05-12 09:45:00', 'APPLIED', 'I believe my skills make me a great fit for...');

INSERT INTO skills (name)
VALUES ('Java'),
       ('Python'),
       ('JavaScript'),
       ('SQL'),
       ('React');

INSERT INTO user_skills (user_id, skill_id)
VALUES (1, 1),
       (1, 3),
       (1, 4),
       (2, 2),
       (2, 3),
       (2, 5);

INSERT INTO saved_jobs (user_id, job_id, saved_at)
VALUES (1, 2, '2023-05-11 16:20:00'),
       (2, 1, '2023-05-13 10:15:00');