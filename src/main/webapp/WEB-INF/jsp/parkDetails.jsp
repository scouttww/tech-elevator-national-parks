<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content" class="details-view">


	
	
	<div id="park-details">
		
		<div class="change-units">
			<h3>Change Units</h3>
			<ul>
				<li>
					<c:url var="thisParkScientificNotationURL" value="/parkChangeUnits">
						<c:param name="parkName" value="${park.parkName}"/>
						<c:param name="parkCode" value="${park.parkCode}"/>
						<c:param name="isScientificNotation" value="true"/>
					</c:url>
					<a class="hyperlink-change-units" href="${thisParkScientificNotationURL}">Scientific Notation<br/>(&deg;C, m, km)
					</a>
				</li>
				<li>
					<c:url var="thisParkImperialNotationURL" value="/parkChangeUnits">
						<c:param name="parkName" value="${park.parkName}"/>
						<c:param name="parkCode" value="${park.parkCode}"/>
						<c:param name="isScientificNotation" value="false"/>
					</c:url>
					<a class="hyperlink-change-units" href="${thisParkImperialNotationURL}">Imperial Notation<br/>(&deg;F, ft, mi)
					</a>
				</li>
			</ul>
		</div>
	
		<div class=title>
			<h2>
				<c:out value="${park.parkName}" />, <em><c:out value="${park.state}" /></em>
			</h2>		
		</div>

		<div class="img-quote">
			<div id="img-box">
				<div class="park-img">	
					<c:url var="parkImageSource" value="/img/parks/${fn:toLowerCase(park.parkCode)}.jpg" />
					<img class="park-img" src="${parkImageSource}" alt="${park.parkName}">
				</div>
			</div>
			<div class="quote" >
				<p id="quote"><c:out value="${park.inspirationalQuote}" /></p>
				<p id="quote-source">	-<c:out value="${park.inspirationalQuoteSource}" /></p>
			</div>
		</div>
		
				
		<div class="description">
			<p><c:out value="${park.parkDescription}" /></p>
		</div>

		
		<div class="table">
			<table>
				<caption>About <c:out value="${park.parkName}" /></caption>
				<c:choose>
					<c:when test="${isScientificNotation==false}">
						<tr>
							<th> Area </th>
							<td> <c:out value="${park.acreage}" /> Acres </td>
						</tr>
						<tr>
							<th> Elevation </th>
							<td> <c:out value="${park.elevationInFeet}" /> Feet </td>
						</tr>
						<tr>
							<th> Total Length of Trails </th>
							<td> <c:out value="${park.milesOfTrail}" /> Miles </td>
						</tr>
					</c:when>
					<c:otherwise>		
						<tr>
							<th> Area </th>
							<td> <c:out value="${park.sqKilometers}" /> Sq. Kilometers </td>
						</tr>
						<tr>
							<th> Elevation </th>
							<td> <c:out value="${park.elevationInMeters}" /> Meters</td>
						</tr>
						<tr>
							<th> Total Length of Trails </th>
							<td> <fmt:formatNumber type="number" maxFractionDigits="2" value="${park.kilometersOfTrail}" /> Kilometers </td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<th>Number of Campsites</th>
					<td><c:out value="${park.numberOfCampsites}" /></td>
				</tr>
				<tr>
					<th>Climate</th>
					<td><c:out value="${park.climate}" /></td>
				</tr>
				<tr>
					<th>Year Founded</th>
					<td><c:out value="${park.yearFounded}" /></td>
				</tr>
				<tr>
					<th>Annual Visitor Count</th>
					<td><c:out value="${park.annualVisitCount}" /></td>
				</tr>
				<tr>
					<th>Entry Fee</th>
					<td>$<c:out value="${park.entryFee}" /></td>
				</tr>
				<tr>
					<th># Animal Species</th>
					<td><c:out value="${park.numberOfAnimalSpecies}" /></td>
				</tr>
			</table>
		</div>
		
		<div class="weather-forecast">
	
			<c:forEach items="${weatherForecasts}" var="singleDayForecast">
				<div class="individual-forecast">

					<c:choose>
						<c:when test="${singleDayForecast.fiveDayForecastValue == 1}">
							<div class="today">
								<h2>TODAY</h2>
							</div>
						</c:when>
						<c:when test="${singleDayForecast.fiveDayForecastValue == 2}">
							<div class="today">
								<h4>TOMORROW</h4>
							</div>
						</c:when>
						<c:otherwise>
							<div class="today">
								<h4 class="day-of-week"><c:out value="${singleDayForecast.day }"/></h4>
							</div>
						</c:otherwise>
					</c:choose>

					<c:set var="forecast" value="${singleDayForecast.forecast}"/>
					<c:url var="parkImageSource" value="/img/weather/${singleDayForecast.forecast}.png" />
					<img class="weather-img" src="${parkImageSource}" alt="${park.parkName}">
					
					<div class="weather-details">
						<c:choose>
							<c:when test="${isScientificNotation==false}">
								<p class="low-temp">Low: <c:out value="${singleDayForecast.lowFahrenheit}" />&deg;F</p>
								<p class="hi-temp">High: <c:out value="${singleDayForecast.highFahrenheit}" />&deg;F</p>
							</c:when>
							<c:otherwise>
								<p class="low-temp">Low: <c:out value="${singleDayForecast.lowCelcius}" />&deg;C</p>
								<p class="hi-temp">High: <c:out value="${singleDayForecast.highCelcius}" />&deg;C</p>
							</c:otherwise>
						</c:choose>
						
						<c:if test="${singleDayForecast.fiveDayForecastValue == 1}">
							<div class="advisories">
								<c:forEach items="${advisoryList}" var="advisory">
									<div>
										<p><c:out value ="${advisory}"/></p>
									</div>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>
		
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />