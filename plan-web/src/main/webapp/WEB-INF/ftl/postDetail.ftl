<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/topic.css", "http://www.tissue.com/resources/css/plan.css", "http://www.tissue.com/resources/css/postForm.css"] in tissue>

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
           <@gadgets.showPostDetail />
        </div>

    </div>
</@tissue.layout>


