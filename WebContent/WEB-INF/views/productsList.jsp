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
int disabledLINK = 0;
if(offSet!=null){
	disabledLINK = Integer.parseInt(offSet);
}

System.out.print(pageList);

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
<a href="productsHome.html?offSet=<%=0%>">Start</a>
<%} %>
<%for(Integer i:pageList) {
if(disabledLINK == i ){
	if(disabledLINK!=size){
%>
<%=i %>
<%}}else{ %>
<a href="productsHome.html?offSet=<%=i%>"><%=i+"" %></a>
<%}} %>
<%if(disabledLINK == size){ %>
<%-- <%="End"%> --%>
<%}else{ %>
<a href="productsHome.html?offSet=<%=size%>">End</a>
<%} %>
</body>
</html> 