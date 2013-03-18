<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "questionGadgets.ftl" as questionGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css", "/tissue/css/post.css"] in commonGadgets>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#assign title="questions" in commonGadgets>

<@commonGadgets.layout>
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
               <@questionGadgets.showQuestions questions />
               <@commonGadgets.showPager />
           </div>
       </div>
    </div>
</@commonGadgets.layout>