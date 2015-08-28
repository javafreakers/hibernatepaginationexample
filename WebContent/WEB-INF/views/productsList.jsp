<%@page import="com.javafreakers.model.Product"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Product List</title>
</head>
<body>
<%
List<Product>  products = (List<Product>)session.getAttribute("pList");
//int   size = Integer.parseInt((String)session.getAttribute("size"));

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
<%for(int i=0;i<10;i++) {%>
<a href="productsHome.html?offSet=<%=i%>"><%=i+"" %></a>
<%} %>
</table>

</body>
</html>