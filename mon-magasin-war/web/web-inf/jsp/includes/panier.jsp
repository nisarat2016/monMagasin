<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <table border="1">
        <thead>
            <tr>
                <th>Reference</th>
                <th>nom</th>
                <th>Quantité</th>
                <th>prixHT/u</th>
                <th>TVA</th>
                <th>prixTTC/u</th>
                <th>prixHT</th>
                <th>prixTTC</th>                
                <th></th>
            </tr>
        </thead>
        <c:forEach items="${panier}" var="lp">
            <tr>
                <td>${lp.reference}</td>
                <td>${lp.nom}</td>
                <td>
                    ${lp.qte}
                </td>
                <td><fmt:formatNumber value="${lp.prixUnitaireHT}" maxFractionDigits="2" minFractionDigits="2" /> €</td>
                <td>${lp.tva}</td>
                <td><fmt:formatNumber value="${lp.prixUnitaireTTC}" maxFractionDigits="2" minFractionDigits="2" /> €</td>
                <td><fmt:formatNumber value="${lp.prixLigneHT}" maxFractionDigits="2" minFractionDigits="2" /> €</td>
                <td><fmt:formatNumber value="${lp.prixLigneTTC}" maxFractionDigits="2" minFractionDigits="2" /> €</td>            
                <td>
                    <c:url var="urlAjouter" value="FrontControleur?section=operation-panier&action=ajouter&ref=${lp.reference}" />
                    <a href="${urlAjouter}"> + |</a>
                    <c:url var="urlEnlever" value="FrontControleur?section=operation-panier&action=enlever&ref=${lp.reference}" />
                    <a href="${urlEnlever}"> - |</a>
                    <c:url var="urlSupprimer" value="FrontControleur?section=operation-panier&action=supprimer&ref=${lp.reference}" />
                    <a href="${urlSupprimer}"> x </a>
                    
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
