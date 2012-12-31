<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>
<#assign mystyles=["/tissue/css/explore.css", "/tissue/css/pop.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@exploreGadgets.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

        </div>

        <div id="content">
            <div id="contentInner">
                <#if tags??>
                <ul>
                <#list tags as tag>
                    <li><a href="/group/exploreTags/${tag}">${tag}</a></li>
                </#list>
                </ul>
                </#if>
            </div>
        </div>
    </div>

</@tissue.layout>
