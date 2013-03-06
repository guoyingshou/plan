<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "utilGadgets.ftl" as utilGadgets />
<#import "postGadgets.ftl" as postGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in tissue>
<#assign mystyles=["/tissue/css/layout3.css"] in tissue>

<#assign title="explore" in tissue>

<@tissue.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@exploreGadgets.exploreLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@exploreGadgets.exploreMenu current/>
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main" class="center">
            <div id="main-sidebar">
                <@userGadgets.showUsers />
            </div>

            <div id="main-mid">
                <@postGadgets.showPosts newPosts />
            </div>

            <div id="main-content">
                <@topicGadgets.showTopics />
                <#if (current == 'topics') || (current == 'tags')>
                    <@utilGadgets.showPager />
                </#if>
            </div>
        </div>
   </div>
</@tissue.layout>
