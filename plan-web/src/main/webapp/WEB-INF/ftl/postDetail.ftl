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
        <#--
        <#assign topic = post.plan.topic in topicGadgets />
        -->
        <@topicGadgets.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
           <#--
            <#if post.plan.topic.activePlan??>
                <#assign activePlan = post.plan.topic.activePlan in planGadgets />
            </#if>
            -->
            <#if topic.activePlan??>
                <#assign activePlan = topic.activePlan in planGadgets />
            </#if>
             <@planGadgets.showActivePlan />
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


