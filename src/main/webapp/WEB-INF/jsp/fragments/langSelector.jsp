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
        <a onclick="showlang('en')">English</a><br>
        <a onclick="showlang('ru')">Русский</a><br>
    </div>
    <script type="text/javascript">
        function showlang(lng) {
            window.location.href = window.location.href.split('?')[0] + '?language=' + lng;
        }
    </script>
</div>