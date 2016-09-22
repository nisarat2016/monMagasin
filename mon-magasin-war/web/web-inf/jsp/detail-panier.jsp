<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>panier</title>
    </head>
    <body>
        <c:url var="url" value="FrontControleur?section=menu-main" />
        <c:import url="${url}" />
        <h1>Detail du panier</h1>
        <c:url var="url02" value="FrontControleur?section=afficher-panier" />
        <c:import url="${url02}" />
    </body>
</html>
