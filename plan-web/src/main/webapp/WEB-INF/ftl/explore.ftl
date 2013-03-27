<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>

<#assign title="explore" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@topicGadgets.exploreLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@topicGadgets.exploreMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content" class="layout3-content">
                <ul id="content-topics">
                    <#list topics as topic>
                    <li>
                        <div class="ts">
                            <a href="/social/users/${topic.account.user.id?replace("#", "")}/posts">${topic.account.user.displayName}</a>
                            [ <@commonGadgets.showTimeBefore topic.timeBefore /> ]
                        </div>
                        <div class="title">
                            <a href="/group/topics/${topic.id?replace("#", "")}/objective">${topic.title}</a>
                        </div>
                    </li>
                    </#list>
                </ul>

                <#if pager??>
                <@commonGadgets.showPager />
                </#if>

                <@topicGadgets.showPosts />
            </div>

            <div id="main-sidebar">
                <@userGadgets.showUsers />
            </div>

        </div>
   </div>
</@commonGadgets.layout>
