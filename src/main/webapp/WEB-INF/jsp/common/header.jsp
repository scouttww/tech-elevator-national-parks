<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>National Parks Geek</title>
    <c:url value="/css/npgeek.css" var="cssHref" />
    <link rel="stylesheet" href="${cssHref}">
</head>

<body>
    <header>
    		<c:url value="/" var="homePageHref" />
    		<c:url value="/img/logo.png" var="logoSrc" />
        <a href="${homePageHref}">
        		<img src="${logoSrc}" alt="National Parks Geek logo" class="logo" />
        </a>
    </header>
    <nav>
        <ul>
        	<c:url var="homeUrl" value="/"/>
        	<c:url var="surveyUrl" value="/survey"/>
        	<c:url var="surveyResultsUrl" value="/surveyResults"/>

            <li><a href="${homeUrl}" class="homeBtn">Home Page</a></li>   
            <li><a href="${surveyUrl}" class="surveyBtn">Submit a Survey</a></li>            
            <li><a href="${surveyResultsUrl}" class="resultsBtn">See Survey Results</a></li>            
        </ul>
    </nav>