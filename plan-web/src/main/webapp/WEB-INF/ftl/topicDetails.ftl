<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css"] in tissue>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<@tissue.layout "topic">
    <div id="logo">
        <@topicGadgets.topicLogo current/>
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@topicGadgets.showLivePlan />
            <@topicGadgets.showArchivedPlans />
        </div>

       <div id="content">
           <@topicGadgets.showTopicDetails />
       </div>
    </div>
</@tissue.layout>
