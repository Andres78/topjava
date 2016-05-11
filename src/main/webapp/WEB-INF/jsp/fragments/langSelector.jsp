<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%--
  Created by IntelliJ IDEA.
  User: ahs
  Date: 11.05.16
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<div class="langs"><b>${pageContext.response.locale}</b>
    <div class="langs_body">
        <a href="?language=en">English</a><br>
        <a href="?language=ru">Русский</a><br>
    </div>
</div>