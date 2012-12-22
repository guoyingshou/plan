<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/topic.css", "/tissue/css/plan.css", "/tissue/css/postForm.css"] in tissue>

<@tissue.layout "question">
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
               <@gadgets.showQuestionDetail />
            </div>
        </div>

    </div>
</@tissue.layout>


