<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout2.css"] in commonGadgets>

<#assign title="tags" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@topicGadgets.exploreLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@topicGadgets.exploreMenu selected />
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

</@commonGadgets.layout>
