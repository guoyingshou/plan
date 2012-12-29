<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "planGadgets.ftl" as planGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/topic.css", "/tissue/css/plan.css", "/tissue/css/postForm.css", "/tissue/css/pop.css"] in tissue>

<@tissue.layout "topic">

    <div id="logo">
        <@topicGadgets.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

            <#if topic.activePlan??>
                <#assign activePlan = topic.activePlan in planGadgets />
            </#if>
            <@planGadgets.showActivePlan />

            <#--
            <@planGadgets.showDeadPlans />
            -->
        </div>

       <div id="content">
           <div id="contentInner">
               <#if posts??>
                   <@postGadgets.showPostList posts />
               </#if>
               <@utilGadgets.showPager />
           </div>
       </div>
    </div>

</@tissue.layout>
