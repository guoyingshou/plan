<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/topic.css", "/tissue/css/plan.css", "/tissue/css/postForm.css", "/tissue/css/pop.css"] in tissue>

<@tissue.layout "topic">

    <div id="logo">
        <@tissue.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

            <#if topic.activePlan??>
                <#assign activePlan = topic.activePlan in tissue />
            </#if>
            <@tissue.showActivePlan />

            <#--
            <@tissue.showDeadPlans />
            -->
        </div>

       <div id="content">
           <div id="contentInner">
               <#if posts??>
                   <@gadgets.showPostList posts />
               </#if>
               <@gadgets.showPager />
           </div>
       </div>
    </div>

</@tissue.layout>
