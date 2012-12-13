<#import "gadgets.ftl" as gadgets>
<!doctype html>
<html>
<head>

<style type="text/css">
    .selected {
        color: red;
    }
    span {
        border: 1px solid black;
    }
    span.cur {
        background: blue;
    }
</style>

</head>
<body>

t3<hr/>


<#assign pages = (pager.total / pager.size)?ceiling />
<#if (pager.current > 1)>
    prev
</#if>

<#if (pages < 7)>
    <#list 1..pages as page>
        <#if (page = pager.current)>
           <span class="cur">
        <#else> 
           <span>
        </#if>
        ${page}</span>
    </#list>
    
<#else>
    <#if (pager.current < 5)>
        <#list 1..5 as page>
            <#if (page = pager.current)>
                <span class="cur">
            <#else>
                <span>
            </#if>
             
             ${page}</span>
        </#list>
        ...<span>${pages}</span>
    <#else>
        <#if ((pager.current +3) >= pages)>
             <span>1</span>...
             <#list (pages - 4)..pages as page>
                 <#if (page = pager.current)>
                     <span class="cur">
                 <#else>
                     <span>
                 </#if> 
                  ${page}</span>
             </#list>
        <#else>
            <span>1</span>...
            <#list (pager.current - 2)..(pager.current +2) as page>
                <#if (page = pager.current)>
                    <span class="cur">
                <#else>
                    <span>
                </#if>
                
                ${page}</span>
            </#list>
            ...<span>${pages}</span>
        </#if>
    </#if>
</#if>

<#if (pager.current < pages)>
    next
</#if>

<hr/>

</body>
</html>

