<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "planGadgets.ftl" as planGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/topic.css", "/tissue/css/plan.css", "/tissue/css/post.css"] in tissue>

<@tissue.layout "post detail">
    <div id="logo">
        <@topicGadgets.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
           <@planGadgets.showLiveGroup />
        </div>
        <div id="content">
           <@postGadgets.showPostDetail />
        </div>
    </div>

    <#if viewer??>
    <@formGadgets.oneItemForm />
    <@formGadgets.postEditForm />
    <@formGadgets.confirmForm />
    </#if>

</@tissue.layout>


