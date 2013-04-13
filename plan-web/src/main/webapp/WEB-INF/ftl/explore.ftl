<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "siteGadgets.ftl" as site />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title="explore" in site>

<@site.layout>

    <@topicGadgets.exploreHeader />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <div id="content-topics">
                    <ul class="topics">
                        <#list topics as topic>
                        <li>
                            <div class="ts">
                                <a href="/social/users/${topic.account.user.id?replace("#", "")}/posts">${topic.account.user.displayName}</a>
                                [ <@site.showTimeBefore topic.timeBefore /> ]
                            </div>
                            <div>
                                <a href="/group/topics/${topic.id?replace("#", "")}/objective">${topic.title}</a>
                            </div>
                        </li>
                        </#list>
                    </ul>
                    <#if pager??>
                        <@site.showPager />
                    </#if>
                </div>

                <div id="content-posts">
                    <@topicGadgets.showPosts />
                </div>
            </div>

            <div id="main-sidebar">
                <@userGadgets.showUsers />
            </div>

        </div>
   </div>
</@site.layout>
