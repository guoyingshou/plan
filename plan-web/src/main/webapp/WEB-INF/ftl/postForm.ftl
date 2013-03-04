<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "postGadgets.ftl" as postGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css"] in tissue>

<@tissue.layout "topic">
    <div id="page-logo">
        <@topicGadgets.topicLogo current />
    </div>

    <div id="page-content-wrapper">
        <div id="page-sidebar">
            <@topicGadgets.showLivePlan />
            <@topicGadgets.showArchivedPlans />
        </div>

       <div id="page-content">
           <@postGadgets.postForm />
       </div>
    </div>
</@tissue.layout>
