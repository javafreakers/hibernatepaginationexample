 <%@page import="com.javafreakers.model.Product"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Product List</title>
</head>
<body>
<%
List<Product>  products = (List<Product>)session.getAttribute("productList");
List<Integer>  pageList = (List<Integer>)session.getAttribute("pageList");
String offSet = request.getParameter("offSet");
 /* disabledLINK has been used to to make current page number nonhiperlink i.e unclickable
 e.g if user is at page number 15 then page number 15 should not be clickable*/
int disabledLINK = 0;
if(offSet!=null){
	disabledLINK = Integer.parseInt(offSet);
}
/* size is used for moving user to end page  by clicking on END link*/
int   size = Integer.parseInt(session.getAttribute("size").toString());

%>

<table border="1px">

<tr>
<th>Product ID</th>
<th>Product Name</th>
<th>Product MFD</th>

</tr>
<%
for(Product product:products){
%>
<tr>
<td><%=product.getpIdLong()%></td>
<td><%=product.getpNameStr() %></td>
<td><%=product.getpExpirDate() %></td>

</tr>
<%} %>

</table>
<%if(disabledLINK != 0){ %>
<!-- if user is on start page then it should not be visible to user or it should not be hyper-link-->
<a href="productsHome.html?offSet=<%=0%>">Start</a>
<%} %>
<%for(Integer i:pageList) {
if(disabledLINK == i ){
	if(disabledLINK!=size){
%>
<!-- Current page should not be hyper-link-->
<%=i %>
<%}}else{ %>
<!-- page previous to current page and next to current page has to be hyper link  -->
<a href="productsHome.html?offSet=<%=i%>"><%=i+"" %></a>
<%}} %>
<%if(disabledLINK == size){ %>
<%-- <%="End"%> --%>
<%}else{ %>
<!-- if user is on last page then it should not be visible to user or it should not be hyper-link-->
<a href="productsHome.html?offSet=<%=size%>">End</a>
<%} %>
</body>
</html> 