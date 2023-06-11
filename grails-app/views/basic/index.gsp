<%--
  Created by IntelliJ IDEA.
  User: billbaran
  Date: 6/10/2023
  Time: 12:55 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${ticker}</title>
</head>

<body>
Ticker: ${ticker}
<!-- Loop over $elements and display -->
<ul>
    <g:each var="element" in="${elements}">
        <li>${element.symbol} - ${element.name} - ${element.amountPercentage}%</li>
    </g:each>
</ul>
</body>
</html>