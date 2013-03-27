<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#assign title="topic" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
        <@topicGadgets.topicLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
        <@topicGadgets.topicMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <@topicGadgets.showPosts />
               <@commonGadgets.showPager />
           </div>
           <div id="main-sidebar">
                <@topicGadgets.showPlanSidebar />
           </div>
       </div>
    </div>
</@commonGadgets.layout>
