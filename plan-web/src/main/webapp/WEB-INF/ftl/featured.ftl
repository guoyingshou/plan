<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/explore.css"] in tissue>

<@tissue.layout "Trending">
    <div id="logo">
        <@exploreGadgets.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

        </div>

        <div id="content">
            <ul class="featured">
                <#list featuredTopics as topic>
                <li>
                    <a href="/group/topics/${topic.id}">${topic.title}</a> 
                    Created By: <a href="/social/users/${topic.user.id}">${topic.user.displayName}</a>
                </li>
                </#list>
            </ul>
        </div>
    </div>
</@tissue.layout>
