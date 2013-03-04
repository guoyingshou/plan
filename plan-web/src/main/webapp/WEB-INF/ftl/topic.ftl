<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css", "/tissue/css/post.css"] in tissue>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<@tissue.layout "topic">
    <div id="page-logo">
        <@topicGadgets.topicLogo current/>
    </div>

    <div id="page-content-wrapper">
        <div id="page-sidebar">
            <@topicGadgets.showPlanSidebar />
        </div>

       <div id="page-content">
           <#if posts??>
               <@postGadgets.showPosts posts />
               <@utilGadgets.showPager />
           <#elseif post??>
               <@postGadgets.showPostDetail />
           <#else>
               <@topicGadgets.showTopicDetails />
           </#if>
       </div>
    </div>
</@tissue.layout>
