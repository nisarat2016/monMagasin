<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>info client</title>
        <link href="css/css01.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:url var="url" value="FrontControleur?section=menu-main" />
        <c:import url="${url}" />
        <h1>Formulaire</h1>
        <c:url var="url" value="FrontControleur" />
        <c:if test="${not empty erreur}">
            <p class="erreur">${erreur}</p>
        </c:if>
        <form action="${url}" method="POST" accept-charset="UTF-8">
            <input type="hidden" name="section" value="inscription" />
            <div>
                <label>Nom : </label>
                <input type="text" name="nom" value="${nomF}" />
                <label class="erreur">${errNom}</label>
            </div>
            <div>
                <label>Prénom : </label>
                <input type="text" name="prenom" value="${prenomF}" />
                <label class="erreur">${errPrenom}</label>
            </div>
            <div>
                <label>email : </label>
                <input type="text" name="mail" value="${emailF}" />
                <label class="erreur">${errMail}</label>
            </div>
            <div>
                <label>mot de passe : </label>
                <input type="password" name="mdp" />
                <label class="erreur">${errMdp}</label>
            </div>
            <div>
                <input type="submit" value="Valider" />
            </div>
        </form>
        
    </body>
</html>
