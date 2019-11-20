<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section id="main-content">

<c:url value="/survey" var="surveyURL" />
		<form:form action="${surveyURL}" method="POST" modelAttribute="survey" id="survey-form">
	<div id="survey">
		    <br />
		    <div id="survey-title">
		    	<h2>Please vote for your favorite National Park!</h2>
		    </div>
		    
		    <div id="fav-park">
		        <label for="park">Favorite National Park:</label>
		        <br/>
		        <form:select path="parkCode" id="park">
		            <form:option value="invalidSelection" label="Select A Park"/>
		            <c:forEach items="${parksList}" var="park">
		                <form:option value="${park.parkCode}" label="${park.parkName}"/>
		            </c:forEach>
		        </form:select>
		    </div>
		    <div id="park-error">
		    <form:errors path="validParkCode" cssClass="error" />
		    </div>
		
		    <div id="fav-email">
		        <label for="emailAddress">Email:</label>
		        <br/>
		        <form:input path="emailAddress" placeholder="Please enter your Email" id="email"/>
		    </div>
		    <div id="email-error">
		    <form:errors path="emailAddress" cssClass="error" />
		    </div>
		
		    <div id="fav-state">
		        <label for="state">State of Residence:</label>
		        <br/>
		        <form:select path="state" id="state">
		            <form:option value="invalidSelection" label="State"/>
		            <c:forEach items="${allStateNames}" var="state">
		                <form:option value="${state}" label="${state}"/>
		            </c:forEach>
		        </form:select>
		    </div>
		    <div id="state-error">
		    <form:errors path="validState" cssClass="error" />
		    </div>
		
		    <div id="fav-activity">
		        <label for="activityLevel">Activity Level:</label>
		        <br/> 
		        <div class="radio">
			        <form:radiobutton path="activityLevel" value="inactive" id="inactive"/>Inactive<br/>
			        <form:radiobutton path="activityLevel" value="sedentary" id="sedentary"/>Sedentary<br />
			        <form:radiobutton path="activityLevel" value="active" id="active"/>Active<br />
			        <form:radiobutton path="activityLevel" value="extremelyActive" id="extremelyActive"/>Extremely Active
		        </div>
		    </div>
		    <div id="activity-error">
		    <form:errors path="activityLevel" cssClass="error" />
		    </div>
		
			<div id="fav-submit">
			    <button id="submit-button">Submit</button>
			</div>
	</div>
		</form:form>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />