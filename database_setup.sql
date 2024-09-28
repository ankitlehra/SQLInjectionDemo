CREATE DATABASE `user`;
USE `user`;
CREATE TABLE IF NOT EXISTS `tbluser` (
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`username`)
);

-- Insert initial user data
INSERT INTO `tbluser` (`username`, `password`) VALUES 
('admin', 'admin123'),
('alice', 'alice321'),
('Ankit', 'Ankit123'),
('bob12', 'bob_12345'),
('chris', 'Chris678'),
('dave1', 'dave_456'),
('jane9', 'jane987'),
('john5', 'john5123'),
('mike3', 'mike_654'),
('sara7', 'sara_789'),
('user1', 'user_123');
