<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>
<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/exploreTopics.css", "/tissue/css/pop.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@tissue.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content">
            <div id="contentInner">
                <ul>
                <#list topics as topic>
                    <li><a href="/u2/plan/topics/${topic.id}">${topic.title}</a> Created By: <a href="http://www.tissue.com/u1/profile/users/${topic.user.id}">${topic.user.displayName}</a></li>
                </#list>
                </ul>
                <@gadgets.showPager />
            </div>
        </div>
   </div>

</@tissue.layout>
