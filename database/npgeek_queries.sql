SELECT * FROM park;
SELECT * FROM survey_result;
SELECT * FROM weather;

BEGIN TRANSACTION;
TRUNCATE weather, park;
INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES (350, 'aaaa', 'c', 'l', 'l');
ROLLBACK;



--Favorite Parks Page
--SurveyResultsDAO

SELECT park.parkname, park.parkcode, COUNT(*) AS num_surveys 
FROM survey_result
JOIN park ON park.parkcode=survey_result.parkcode --join will enforce all surveys that attach to a park code, but not necessarily all parks (any park that isn't represented in by a survey won't show in this result)
GROUP BY park.parkname, park.parkcode
ORDER BY num_surveys DESC, park.parkname;

BEGIN TRANSACTION;
INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES (DEFAULT, ?, ?, ?, ?) RETURNING surveyid; --Submit Survey/SurveyResultsDAO
INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES (DEFAULT, 'ENP', 'test@test.test', 'testState', 'testActivityLevel');
INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES (DEFAULT, 'GNP', 'test@test.test', 'testState', 'testActivityLevel');



--weatherDAO
--SELECT five-day forecast weather from a given park
SELECT parkCode, fivedayforecastvalue, low, high, forecast FROM weather WHERE parkcode = ? ORDER BY fivedayforecastvalue ASC;
-- testing: list should always be length 5 given a correct park code

--parkDAO
--SELECT details from 1 park for detail page
SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies 
FROM park
WHERE parkcode = ?;

--parkDAO
--SELECT list of all parks, ordered alphabetically by name
SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies 
FROM park;
ORDER BY parkname;


DELETE FROM survey_result WHERE emailaddress ILIKE '%test@GoodEmail.scout.wallace.brandon.gardner%';