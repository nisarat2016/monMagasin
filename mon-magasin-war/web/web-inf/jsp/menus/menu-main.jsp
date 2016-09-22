<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <p>
        <c:url var="url01" value="FrontControleur?section=vers-formulaire-client" />
        <a href="${url01}">creer un nouveau client</a> |
        <c:url var="url02" value="FrontControleur?section=catalogue" />
        <a href="${url02}">catalogue</a> |
        <c:url var="url03" value="FrontControleur?section=panier" />
        <a href="${url03}" > panier - ${infoPanier} </a> |
        <c:url var="url04" value="FrontControleur?section=creer-donnees" />
        <a href="${url04}" > créer jeux de test </a>
    </p>
    <hr />
</div>
