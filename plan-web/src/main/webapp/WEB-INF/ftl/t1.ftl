<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<@sec.authorize ifAnyGranted="ROLE_user">
    Add user because of ifAnyGranted attr<hr/>
</@sec.authorize>

<@sec.authorize access="hasRole('ROLE_user')">
    Add user<hr/>
</@sec.authorize>

hello

