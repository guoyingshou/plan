<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "siteGadgets.ftl" as site />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title="explore" in site>

<@site.layout>

    <#include "exploreHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content" class="layout3-content">
                <ul id="content-topics">
                    <#list topics as topic>
                    <li>
                        <div class="ts">
                            <a href="/social/users/${topic.account.user.id?replace("#", "")}/posts">${topic.account.user.displayName}</a>
                            [ <@site.showTimeBefore topic.timeBefore /> ]
                        </div>
                        <div class="title">
                            <a href="/group/topics/${topic.id?replace("#", "")}/objective">${topic.title}</a>
                        </div>
                    </li>
                    </#list>
                </ul>

                <#if pager??>
                <@site.showPager />
                </#if>

                <@topicGadgets.showPosts />
            </div>

            <div id="main-sidebar">
                <@userGadgets.showUsers />
            </div>

        </div>
   </div>
</@site.layout>
