CREATE ROLE dbuser WITH LOGIN ENCRYPTED PASSWORD 'dbpwd';
CREATE DATABASE annvarsearch_db OWNER dbuser;
-- GRANT CREATE ON DATABASE annvarsearch_db TO dbuser;
-- GRANT ALL PRIVILEGES ON DATABASE annvarsearch_db TO dbuser;
