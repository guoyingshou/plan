<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title=topic.title in site>

<@site.layout>

    <@topicGadgets.topicHeader />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <@topicGadgets.showPosts />
               <@site.showPager />
           </div>
           <div id="main-sidebar">
                <@topicGadgets.showPlansArchived />
           </div>
       </div>
    </div>
</@site.layout>
