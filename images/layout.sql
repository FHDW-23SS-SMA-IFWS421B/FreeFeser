-- Tabelle: chatbots
CREATE TABLE chatbots (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          botname VARCHAR(255) NOT NULL UNIQUE,
                          active BOOLEAN NOT NULL
);

-- Tabelle: messages
CREATE TABLE messages (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          text VARCHAR(255) NOT NULL,
                          timestamp DATETIME NOT NULL,
                          user_id BIGINT NOT NULL,
                          bot_id BIGINT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(id),
                          FOREIGN KEY (bot_id) REFERENCES chatbots(id)
);

-- Tabelle: users
CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);