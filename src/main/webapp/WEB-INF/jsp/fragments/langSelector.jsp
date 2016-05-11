<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: ahs
  Date: 11.05.16
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>

<div id='cssmenu'>
    <ul>
        <li class='has-sub'><a href='#'>${pageContext.response.locale}</a>
            <ul>
                <li><a href='?language=en'>English</a></li>
                <li><a href='?language=ru'>Русский</a></li>
            </ul>
        </li>
    </ul>
</div>

