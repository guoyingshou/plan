<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/explore.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@exploreGadgets.exploreLogo "tags"/>
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showUsers />
        </div>

        <div id="content">
            <#if tags??>
            <#list tags as tag>
                <span><a href="/group/tags/${tag}">${tag}</a></span>
            </#list>
            </#if>
        </div>
    </div>

</@tissue.layout>
