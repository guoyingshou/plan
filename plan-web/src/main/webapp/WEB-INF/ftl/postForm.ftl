<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css"] in tissue>

<@tissue.layout "topic">

    <div id="logo">
        <@topicGadgets.topicLogo current />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@topicGadgets.showLivePlan />
            <@topicGadgets.showArchivedPlans />
        </div>

       <div id="content">
           <@formGadgets.postForm />
       </div>
    </div>

</@tissue.layout>
