CREATE SCHEMA movies;
CREATE USER 'movies'@'%' IDENTIFIED BY 'fEvfvJz8mKfjkV943JTA';
GRANT ALL PRIVILEGES ON `movies`.* TO 'movies'@'%';