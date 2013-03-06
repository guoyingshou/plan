<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "postGadgets.ftl" as postGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in tissue>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css"] in tissue>

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
                <@topicGadgets.showLivePlan />
                <@topicGadgets.showArchivedPlans />
            </div>

           <div id="main-content">
               <@postGadgets.postForm />
           </div>
       </div>
    </div>
</@tissue.layout>
