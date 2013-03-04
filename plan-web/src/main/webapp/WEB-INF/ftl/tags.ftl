<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<@tissue.layout "topics">
    <div id="page-logo">
        <@exploreGadgets.exploreLogo "tags"/>
    </div>

    <div id="page-content-wrapper">
        <div id="page-sidebar">
            <@userGadgets.showUsers />
        </div>

        <div id="page-content">
            <#if tags??>
            <#list tags as tag>
                <span><a href="/group/tags/${tag}">${tag}</a></span>
            </#list>
            </#if>
        </div>
    </div>

</@tissue.layout>
