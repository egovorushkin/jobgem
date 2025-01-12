-- Grant the 'root' user superuser privileges
ALTER USER jobgemadmin WITH SUPERUSER;

-- Connect to the 'jobgem' database
\connect jobgem;

-- Create the jobs table with columns based on your JobEntity fields
CREATE TABLE IF NOT EXISTS jobs
(
    id          BIGSERIAL PRIMARY KEY,                                                                         -- Auto-incrementing ID (Long)
    title       VARCHAR(255)     NOT NULL,                                                                     -- Job title
    company     VARCHAR(255)     NOT NULL,                                                                     -- Company name
    location    VARCHAR(255)     NOT NULL,                                                                     -- Job location
    description VARCHAR(1000),                                                                                 -- Job description (optional)
    type        VARCHAR(50)      NOT NULL CHECK (type IN ('FULL_TIME', 'PART_TIME', 'CONTRACT', 'FREELANCE')), -- Employment type (FULL_TIME, PART_TIME, etc.)
    salary      DOUBLE PRECISION NOT NULL,                                                                     -- Job salary (double)
    level       VARCHAR(50),                                                                                   -- Job level (e.g., Junior, Mid, Senior)
    posted_date DATE             NOT NULL DEFAULT CURRENT_DATE                                                 -- Job posting date (default to current date)
);

-- Insert test data into the jobs table
INSERT INTO jobs (title, company, location, description, type, salary, level, posted_date)
VALUES
    ('Software Engineer', 'TechCorp', 'New York', 'Develop and maintain software solutions.', 'FULL_TIME', 100000, 'Mid', '2025-01-01'),
    ('Data Scientist', 'DataTech', 'San Francisco', 'Analyze and interpret complex data to inform business decisions.', 'FULL_TIME', 120000, 'Senior', '2025-01-05'),
    ('Product Manager', 'Productify', 'Remote', 'Lead product strategy and development.', 'PART_TIME', 60000,  'Mid', '2024-12-15'),
    ('DevOps Engineer', 'CloudWorks', 'Austin', 'Manage and optimize cloud infrastructure.', 'FULL_TIME', 110000, 'Senior', '2025-01-08'),
    ('UI/UX Designer', 'Designify', 'Los Angeles', 'Design user-friendly and visually appealing interfaces.', 'PART_TIME', 70000, 'Junior', '2024-12-25'),
    ('Backend Developer', 'CodeCraft', 'Seattle', 'Build and maintain backend APIs and services.', 'FREELANCE', 95000,  'Mid', '2025-01-10'),
    ('Marketing Specialist', 'MarketMinds', 'Boston', 'Develop and execute marketing campaigns.', 'PART_TIME', 55000,  'Junior', '2024-12-20'),
    ('Machine Learning Engineer', 'AIWorks', 'Palo Alto', 'Develop machine learning models and pipelines.', 'FULL_TIME', 130000,  'Senior', '2025-01-03'),
    ('HR Coordinator', 'PeopleFirst', 'Chicago', 'Assist in HR operations and employee engagement.', 'CONTRACT', 50000,  'Junior', '2024-12-30'),
    ('Business Analyst', 'Analytica', 'New York', 'Analyze business processes and recommend solutions.', 'FULL_TIME', 85000, 'Mid', '2025-01-06');