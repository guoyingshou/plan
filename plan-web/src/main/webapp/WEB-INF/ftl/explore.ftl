<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "utilGadgets.ftl" as utilGadgets />
<#import "postGadgets.ftl" as postGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in tissue>
<#assign mystyles=["/tissue/css/layout3.css"] in tissue>

<@tissue.layout current>
    <div id="logo">
        <@exploreGadgets.exploreLogo current />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showUsers />
        </div>

        <div id="mid">
            <@postGadgets.showPosts newPosts />
        </div>

        <div id="content">
            <@topicGadgets.showTopics />
            <#if (current == 'topics') || (current == 'tags')>
                <@utilGadgets.showPager />
            </#if>
        </div>
   </div>

</@tissue.layout>
