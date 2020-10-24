INSERT INTO users (username, password, role, first_name, second_name, last_name) VALUES ('admin', 'admin', 'ADMIN', '','','');
INSERT INTO items (type, name, cost) VALUES ('face', 'standartFace1', 0);
INSERT INTO items (type, name, cost) VALUES ('face', 'standartFace2', 0);
INSERT INTO items (type, name, cost) VALUES ('face', 'standartFace3', 0);
INSERT INTO items (type, name, cost) VALUES ('face', 'usualFace1', 1000);
INSERT INTO items (type, name, cost) VALUES ('face', 'usualFace2', 1000);
INSERT INTO items (type, name, cost) VALUES ('face', 'usualFace3', 1000);
INSERT INTO items (type, name, cost) VALUES ('mouth', 'standartMouth1', 0);
INSERT INTO items (type, name, cost) VALUES ('mouth', 'standartMouth2', 0);
INSERT INTO items (type, name, cost) VALUES ('mouth', 'standartMouth3', 0);
INSERT INTO items (type, name, cost) VALUES ('mouth', 'usualMouth1', 1000);
INSERT INTO items (type, name, cost) VALUES ('mouth', 'usualMouth2', 1000);
INSERT INTO items (type, name, cost) VALUES ('mouth', 'usualMouth3', 1000);
INSERT INTO items (type, name, cost) VALUES ('hat', 'warning', 10000);
INSERT INTO items (type, name, cost) VALUES ('hat', 'lowBattery', 10000);
INSERT INTO items (type, name, cost) VALUES ('hat', 'nimb', 10000);
INSERT INTO items (type, name, cost) VALUES ('hat', 'otkritie', 10000);
INSERT INTO items (type, name, cost) VALUES ('hat', 'loveEyes', 10000);
INSERT INTO items (type, name, cost) VALUES ('hat', 'smallCrown', 10000);
INSERT INTO items (type, name, cost) VALUES ('hat', 'irokez', 10000);
INSERT INTO items (type, name, cost) VALUES ('body', 'standartBody', 0);
INSERT INTO items (type, name, cost) VALUES ('body', 'skeletonBody', 50000);
INSERT INTO items (type, name, cost) VALUES ('arm', 'standartArm', 0);
INSERT INTO items (type, name, cost) VALUES ('arm', 'skeletonArm', 50000);
INSERT INTO items (type, name, cost) VALUES ('leg', 'standartLeg', 0);
INSERT INTO items (type, name, cost) VALUES ('leg', 'skeletonLeg', 50000);
INSERT INTO items (type, name, cost) VALUES ('backpack', 'standartBackpack', 0);
INSERT INTO items (type, name, cost) VALUES ('backpack', 'skeletonBackpack', 50000);
INSERT INTO bugs (
    bugName, description, testedSystem, betaVersion, OSModel, screenshot, date,  time, status)
    VALUES ('crash system', 'all system crash', 'website', '2.0', 'Windows XP', 'screenshot',
    '2001-09-11', '00:00:00', 1);
INSERT INTO bugs (
    bugName, description, testedSystem, betaVersion, OSModel, screenshot,  date,  time, status)
    VALUES ('button bug', 'wrong color of button', 'website', '3.0', 'MacOS', 'screenshot',
    '2020-02-20', '00:00:00', 0);