<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>home</title>
    </head>
    <body>
        <c:url var="url" value="FrontControleur?section=menu-main" />
        <c:import url="${url}" />
        <h1>Home</h1>
        <div>
            <p>Bienvenue dans mon magasin.</p>
            <c:if test="${not empty msg}">
                <p>
                    ${msg}
                </p>
            </c:if>
        </div>
    </body>
</html>
