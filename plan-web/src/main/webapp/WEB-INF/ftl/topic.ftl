<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#assign title="topic" in site>

<@site.layout>

    <#include "topicHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <div class="ts">
                   <span>
                      ${topic.account.user.displayName}
                      [ <@site.showTimeBefore topic.timeBefore /> ]
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
               <@site.confirmForm />
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

            <div id="main-sidebar">
                <@topicGadgets.showPlanSidebar />
            </div>


       </div>
    </div>
</@site.layout>
