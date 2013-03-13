<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "postGadgets.ftl" as postGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout3.css"] in commonGadgets>

<#assign title="explore" in commonGadgets>
<#assign posts = newPosts in postGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@topicGadgets.exploreLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@topicGadgets.exploreMenu selected/>
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main" class="center">
            <div id="main-sidebar">
                <@userGadgets.showUsers />
            </div>

            <div id="main-mid">
                <@postGadgets.showPosts />
            </div>

            <div id="main-content">
                <@topicGadgets.showTopics />
                <#if (selected == 'topics') || (selected == 'tags')>
                    <@commonGadgets.showPager />
                </#if>
            </div>
        </div>
   </div>
</@commonGadgets.layout>
