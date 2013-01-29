<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css", "/tissue/css/plan.css", "/tissue/css/post.css"] in tissue>

<@tissue.layout "post detail">
    <div id="logo">
        <@topicGadgets.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
           <@topicGadgets.showLivePlan />
           <@topicGadgets.showArchivedPlans />
        </div>
        <div id="content" class="post-detail">
           <@postGadgets.showPostDetail />
        </div>
    </div>

    <#if viewer??>
    <@formGadgets.oneItemForm />
    <@formGadgets.postEditForm />
    <@formGadgets.confirmForm />
    </#if>

</@tissue.layout>


