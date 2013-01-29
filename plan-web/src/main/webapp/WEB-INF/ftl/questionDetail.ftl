<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css", "/tissue/css/post.css"] in tissue>

<@tissue.layout "question">
    <div id="logo">
        <@topicGadgets.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@topicGadgets.showLiveGroup />
            <@topicGadgets.showArchivedGroup />
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


