<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "userGadgets.ftl" as userGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title="tags" in site>

<@site.layout>

    <@topicGadgets.exploreHeader />

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

</@site.layout>
