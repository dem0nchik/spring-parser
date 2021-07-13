CREATE TABLE users(
    u_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE file_result(
    fr_id SERIAL PRIMARY KEY,
    file_name TEXT NOT NULL,
    file_size BIGINT NOT NULL,
    file_last_modified TIMESTAMP DEFAULT NULL,
    file_path VARCHAR NOT NULL
);

CREATE TABLE user_file_status(
    ufs_id SERIAL PRIMARY KEY,
    fr_id INTEGER REFERENCES file_result(fr_id),
    u_id INTEGER REFERENCES users(u_id),
    date_adding TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);