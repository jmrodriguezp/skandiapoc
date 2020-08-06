<%@ include file="/init.jsp" %>
<%String s=(String)request.getAttribute("GREETER_MESSAGE");%>
<liferay-portlet:actionURL name="createContract" var="createContract" />
<%=s %>
<aui:form action="<%= createContract %>" method="post" >
	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>
</aui:form>