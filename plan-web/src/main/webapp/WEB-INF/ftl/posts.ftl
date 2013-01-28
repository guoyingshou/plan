<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "planGadgets.ftl" as planGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/topic.css", "/tissue/css/plan.css"] in tissue>

<@tissue.layout "topic">

    <div id="logo">
        <@topicGadgets.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

            <@planGadgets.showLiveGroup />

            <#--
            <@planGadgets.showArchivedGroup />
            -->
        </div>

       <div id="content">
           <#if posts??>
               <@postGadgets.showPosts posts />
               <@utilGadgets.showPager />
           </#if>
       </div>
    </div>

</@tissue.layout>
