<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>
<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/exploreTags.css", "/tissue/css/pop.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@tissue.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

        </div>

        <div id="content">
            <div id="contentInner">
                <#if tags??>
                <ul>
                <#list tags as tag>
                    <li><a href="/u2/plan/exploreTags/${tag}">${tag}</a></li>
                </#list>
                </ul>
                </#if>
            </div>
        </div>
    </div>

</@tissue.layout>
