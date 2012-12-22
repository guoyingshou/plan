<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/explore.css", "/tissue/css/pop.css"] in tissue>

<@tissue.layout "explore">
    <div id="logo">
        <@tissue.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

        </div>

        <div id="content" class="trend">
            <div id="contentInner">
                <div class="trending">
                    <h2>Trending topics</h2>
                    <ul>
                        <#list trendingTopics as topic>
                            <li><a href="/u2/plan/topics/${topic.id}">${topic.title}</a> Created By: <a href="http://www.tissue.com/u1/profile/users/${topic.user.id}">${topic.user.displayName}</a></li>
                        </#list>
                    </ul>
                </div>

                <div class="feature">
                    <h2>Featured topics</h2>
                    <ul>
                        <#list featuredTopics as topic>
                            <li><a href="/u2/plan/topics/${topic.id}">${topic.title}</a> Created By: <a href="http://www.tissue.com/u1/profile/users/${topic.user.id}">${topic.user.displayName}</a></li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</@tissue.layout>
