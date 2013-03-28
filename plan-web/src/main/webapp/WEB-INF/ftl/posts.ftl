<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#assign title="topic" in site>

<@site.layout>

    <#include "topicHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <@topicGadgets.showPosts />
               <@site.showPager />
           </div>
           <div id="main-sidebar">
                <@topicGadgets.showPlanSidebar />
           </div>
       </div>
    </div>
</@site.layout>
