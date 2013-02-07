<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/post.js"] in tissue>

<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css", "/tissue/css/post.css"] in tissue>

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
</@tissue.layout>


