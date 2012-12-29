<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>
<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/exploreTopics.css", "/tissue/css/pop.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@exploreGadgets.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content">
            <div id="contentInner">
                <ul>
                <#list topics as topic>
                    <li><a href="/group/topics/${topic.id}">${topic.title}</a> Created By: <a href="/profile/users/${topic.user.id}">${topic.user.displayName}</a></li>
                </#list>
                </ul>
                <@utilGadgets.showPager />
            </div>
        </div>
   </div>

</@tissue.layout>
