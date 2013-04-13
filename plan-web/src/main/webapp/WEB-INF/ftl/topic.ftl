<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title=topic.title in site>

<@site.layout>

    <@topicGadgets.topicHeader />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <div class="meta">
                   <span class="owner">
                      ${topic.account.user.displayName}
                      [ <@site.showTimeBefore topic.timeBefore /> ]
                   </span>

                   <#if !topic.deleted>
                   <@site.confirmForm />

                   <#if viewerAccount??>
                   <#if topic.isOwner(viewerAccount.id)>
                   <span class="owner-action">
                       <a href="<@spring.url '/topics/${topic.id?replace("#", "")}/_update' />">
                           <@spring.message 'Update' />
                       </a>
                       <a class="pop" data-form-selector="#confirmForm" data-dialog-width="320" data-action="<@spring.url '/topics/${topic.id?replace("#", "")}/_delete' />" href="#">
                           <@spring.message 'Delete' />
                       </a>
                   </span>
                   <#elseif viewerAccount.hasRole('ROLE_ADMIN')>
                   <span class="admin-action">
                       <a class="pop" data-form-selector="#confirmForm" data-dialog-width="320" data-action="<@spring.url '/topics/${topic.id?replace("#", "")}/_delete' />" href="#">
                           <@spring.message 'Delete' />
                       </a>
                   </span>
                   </#if>
                   </#if>

                   </#if>
               </div>

               <div class="tags">
                   <#list topic.tags as tag>
                   <span><a href="<@spring.url '/tags/${tag}' />">${tag}</a></span>
                   </#list>
               </div>
               <div class="content">
                   ${topic.content}
               </div>

           </div>

            <div id="main-sidebar">
                <@topicGadgets.showPlansArchived />
            </div>


       </div>
    </div>
</@site.layout>
