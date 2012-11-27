<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/exploreTopics.css"] in tissue>

<@tissue.layout "topics">
    <div id="logo">
        <@tissue.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content">
            <ul>
                <#list topics as topic>
                    <li><a href="/u2/plan/topics/${topic.id}">${topic.title}</a></li>
                </#list>
            </ul>
<#--
            <@gadgets.showPagedTopics />
        -->
        </div>
   </div>

</@tissue.layout>
