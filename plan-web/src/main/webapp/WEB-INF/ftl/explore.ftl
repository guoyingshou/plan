<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/explore.css"] in tissue>

<@tissue.layout "explore">
    <div id="logo">
        <@exploreGadgets.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

        </div>

        <div id="content" class="trend">
            <div id="contentInner">
                <div class="trending">
                    <h2><@spring.message "i18n.explore.heading.trendingTopics" /></h2>
                    <ul>
                        <#list trendingTopics as topic>
                        <li>
                            <a href="/group/topics/${topic.id}">${topic.title}</a>
                            Created By: <a href="/social/users/${topic.user.id}">${topic.user.displayName}</a>
                        </li>
                        </#list>
                    </ul>
                </div>

                <div class="feature">
                    <h2><@spring.message "i18n.explore.heading.featuredTopics" /></h2>
                    <ul>
                        <#list featuredTopics as topic>
                        <li>
                            <a href="/group/topics/${topic.id}">${topic.title}</a> 
                            Created By: <a href="/social/users/${topic.user.id}">${topic.user.displayName}</a>
                        </li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</@tissue.layout>
