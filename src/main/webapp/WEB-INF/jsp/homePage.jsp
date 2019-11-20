<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content">

	<c:forEach items="${parksList}" var="park">
		<div class="individual-park">
			<h2>
				<c:out value="${park.parkName}" />, <em><c:out value="${park.state}" /></em>
			</h2>

			<div class="basic-park-info">
				<c:url var="parkImageSource" value="/img/parks/${fn:toLowerCase(park.parkCode)}.jpg" />
					
				<c:url var="parkDetailUrl" value="/park">
					<c:param name="parkName" value="${park.parkName}"/>
					<c:param name="parkCode" value="${park.parkCode}"/>
				</c:url>
				
				<a href="${parkDetailUrl}">
					<img class="park-img" src="${parkImageSource}" alt="${park.parkName}">
				</a>
				
				<p>
					<c:out value="${park.parkDescription}" />
				</p>
			</div>

		</div>
	</c:forEach>

</section>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />