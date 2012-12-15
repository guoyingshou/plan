<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#--
<#assign myscripts=["/ckeditor/ckeditor.js", "/ckeditor/adapters/jquery.js"] in tissue>
-->
<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/topic.css", "http://www.tissue.com/resources/css/plan.css", "http://www.tissue.com/resources/css/postForm.css"] in tissue>

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
               <p>${topic.content}</p>
               <p><a href="<@spring.url '/plan/topics/${topic.id}/edit' />">edit</a></p>
           </div>
       </div>

    </div>

</@tissue.layout>
