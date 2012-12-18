<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/js/pop.js"] in tissue>

<#assign mystyles=["/resources/css/content-2cols.css", "/resources/css/topic.css", "/resources/css/plan.css", "/resources/css/postForm.css", "/resources/css/pop.css"] in tissue>

<@tissue.layout "post detail">
    <div id="logo">
        <#assign topic = post.plan.topic in tissue />
        <@tissue.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <#assign activePlan = topic.activePlan in tissue />
            <@tissue.showActivePlan />
        </div>
        <div id="content">
            <div id="contentInner">
               <@gadgets.showPostDetail />
            </div>
        </div>
    </div>


</@tissue.layout>


