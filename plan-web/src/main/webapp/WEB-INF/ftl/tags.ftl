<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "personGadgets.ftl" as personGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/explore.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@exploreGadgets.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@personGadgets.showUsers />
        </div>

        <div id="content">
            <div id="contentInner">
                <#if tags??>
                <#list tags as tag>
                    <span><a href="/group/tags/${tag}">${tag}</a></span>
                </#list>
                </#if>
            </div>
        </div>
    </div>

</@tissue.layout>
