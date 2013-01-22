<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "planGadgets.ftl" as planGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/topic.css", "/tissue/css/plan.css", "/tissue/css/post.css"] in tissue>

<@tissue.layout "question">
    <div id="logo">
        <@topicGadgets.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <#assign activePlan = topic.activePlan in planGadgets />
            <@planGadgets.showActivePlan />
        </div>
        <div id="content">
           <@postGadgets.showQuestionDetail />
        </div>
    </div>

    <#if viewer??>
    <@formGadgets.postEditForm />
    <@formGadgets.oneItemForm />
    <@formGadgets.confirmForm />
    </#if>

</@tissue.layout>


