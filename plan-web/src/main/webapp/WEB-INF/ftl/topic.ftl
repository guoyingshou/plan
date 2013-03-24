<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "postGadgets.ftl" as postGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css"] in commonGadgets>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#assign title="topic" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
        <@topicGadgets.topicLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
        <@topicGadgets.topicMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
                <@topicGadgets.showPlanSidebar />
            </div>

           <div id="main-content">
               <div class="ts">
                   <span>
                      ${topic.account.user.displayName}
                      [ <@commonGadgets.showTimeBefore topic.timeBefore /> ]
                   </span>
               </div>
               <div class="tags">
                   <#list topic.tags as tag>
                   <span><a href="<@spring.url '/tags/${tag}' />">${tag}</a></span>
                   </#list>
               </div>
               <div class="content">
                   ${topic.content}
               </div>

               <#if !topic.deleted>
               <@commonGadgets.deleteConfirmForm />
               <#if viewerAccount?? && topic.isOwner(viewerAccount.id)>
               <a href="<@spring.url '/topics/${topic.id?replace("#", "")}/_update' />">
                   <@spring.message 'Update.topic' />
               </a>
               <a class="delete" data-action="<@spring.url '/topics/${topic.id?replace("#", "")}/_delete' />" href="#">
                   <@spring.message 'Delete.topic' />
               </a>
               <#else>
               <@sec.authorize access="hasRole('ROLE_ADMIN')">
               <a class="delete" data-action="<@spring.url '/topics/${topic.id?replace("#", "")}/_delete' />" href="#">
                   <@spring.message 'Delete.topic' />
               </a>
               </@sec.authorize>
               </#if>
               </#if>
           </div>
       </div>
    </div>
</@commonGadgets.layout>
