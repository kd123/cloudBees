SET SCHEMA booking;

CREATE USER IF NOT EXISTS app_user PASSWORD 'abc@123';
GRANT ALL ON SCHEMA PUBLIC TO app_user;