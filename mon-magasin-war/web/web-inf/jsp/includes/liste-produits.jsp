<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table border="1">
        <thead>
            <tr>
                <th>Reference</th>
                <th>nom</th>
                <th>prixHT/u</th>
                <th>TVA</th>
                <th>prixTTC/u</th>
                <th>Quantité dispo</th>
                <th></th>
            </tr>
        </thead>
        <c:forEach items="${produits}" var="p">
            <tr>
                <td>${p.reference}</td>
                <td>${p.nom}</td>
                <td><fmt:formatNumber value="${p.prixHT}" maxFractionDigits="2" minFractionDigits="2" /> €</td>
                <td>${p.tva.taux}</td>
                <td><fmt:formatNumber value="${p.prixTTC}" maxFractionDigits="2" minFractionDigits="2" /> €</td>
                <td>${p.stock}</td>
                <td>
                    <c:url var="url03" value="FrontControleur?section=operation-panier&action=ajouter&ref=${p.reference}" />
                    <a href="${url03}">ajouter au panier</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
