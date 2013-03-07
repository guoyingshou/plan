<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css", "/tissue/css/post.css"] in tissue>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#assign title="topic" in tissue>

<@tissue.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
        <@topicGadgets.topicLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
        <@topicGadgets.topicMenu current />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
                <@topicGadgets.showPlanSidebar />
            </div>

           <div id="main-content">
           <#if posts??>
               <@postGadgets.showPosts posts />
               <@commonGadgets.showPager />
           <#elseif post??>
               <@postGadgets.showPostDetail />
           <#else>
               <@topicGadgets.showTopicDetails />
           </#if>
           </div>
       </div>
    </div>
</@tissue.layout>
