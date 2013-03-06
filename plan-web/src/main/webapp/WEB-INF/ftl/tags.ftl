<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css"] in tissue>

<#assign title="tags" in tissue>

<@tissue.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@exploreGadgets.exploreLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@exploreGadgets.exploreMenu "tags" />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
                <@userGadgets.showUsers />
            </div>

            <div id="main-content">
            <#if tags??>
            <#list tags as tag>
                <span><a href="/group/tags/${tag}">${tag}</a></span>
            </#list>
            </#if>
            </div>
        </div>
    </div>

</@tissue.layout>
