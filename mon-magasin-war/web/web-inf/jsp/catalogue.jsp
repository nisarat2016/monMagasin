<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>catalogue</title>
    </head>
    <body>
        <c:url var="url" value="FrontControleur?section=menu-main" />
        <c:import url="${url}" />
        <h1>catalogue</h1>
        <c:url var="url02" value="FrontControleur?section=liste-produit" />
        <c:import url="${url02}" />
    </body>
</html>
