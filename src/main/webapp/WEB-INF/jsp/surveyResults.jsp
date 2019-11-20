<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content">

	<c:forEach items="${favoriteParksList}" var="park">
		<div class="favored-parks">

			<div class="park-img-survey">
				<c:url var="parkImageSource" value="/img/parks/${fn:toLowerCase(park.parkCode)}.jpg" />
					
				<c:url var="parkDetailUrl" value="/park">
					<c:param name="parkName" value="${park.parkName}"/>
					<c:param name="parkCode" value="${park.parkCode}"/>
				</c:url>
				
				<a href="${parkDetailUrl}">
					<img class="park-img" src="${parkImageSource}" alt="${park.parkName}">
				</a>
			</div>

			<div class="survey-results">
				<div class="park-name-survey">
					<h2>
						<c:out value="${park.parkName}" />
					</h2>
				</div>
				
				<div class = num-likes=survey>
					<h3 class="numberOfLikes">Number of Likes: <c:out value="${park.numberOfSurveys}" /></h3>
				</div>
			</div>


			

		</div>
	</c:forEach>

</section>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />