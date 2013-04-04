<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign title="post detail" in site>

<@site.layout>

    <#include "topicHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <div class="article">
                    <h3 class="title">
                        ${article.title}
                        <#if article.deleted>
                        <span>[ closed ]</span>
                        </#if>
                    </h3>

                    <div class="owner">
                        <span class="ts">
                            <a href="/social/users/${article.account.user.id?replace("#", "")}/posts">
                                ${article.account.user.displayName} 
                            </a>
                            [ <@site.showTimeBefore article.timeBefore /> ] 
                        </span>

                        <#if article.isOwner(viewerAccount.id)>
                        <span class="owner-action">
                            <a class="pop" data-form-selector="#confirmForm" data-dialog-width="650" data-action="<@spring.url '/articles/${article.id?replace("#", "")}/_delete' />" href="#">
                                <@spring.message 'Delete.article' />
                            </a>
                            <a href="<@spring.url '/articles/${article.id?replace("#","")}/_update' />">
                                <@spring.message 'Update.article' />
                            </a>
                        </span>
                        <#else>
                        <@sec.authorize access="hasRole('ROLE_ADMIN')">
                        <span class="admin">
                        <a class="pop" data-form-selector="#confirmForm" data-dialog-width="650" data-action="<@spring.url '/articles/${article.id?replace("#", "")}/_delete' />" href="#">
                            <@spring.message 'Delete.article' />
                        </a>
                        </@sec.authorize>
                        </span>
                        </#if>
                    </div>

                    <div class="content">
                       ${article.content}
                    </div>
                </div>

                <#if !(topic.deleted || article.deleted) && isMember>
                <div class="member">
                   <a class="pop" data-editor-name="message-editor" data-form-selector="#messageForm" data-dialog-width="650" data-action="<@spring.url '/articles/${article.id?replace("#","")}/messages/_create' />" href="#">
                       <@spring.message 'AddMessage.article' />
                   </a>
                </div>
                </#if>

                <#if article.messages??>
                <ul class="messages">
                   <#list article.messages as msg>
                   <li class="message">
                       <div id="message-${msg.id?replace("#","")?replace(":","-")}">
                           <div class="owner">
                               <span class="ts">
                                   <a href="/social/users/${msg.account.user.id?replace("#","")}/posts">
                                       ${msg.account.user.displayName}  
                                   </a>
                                   [ <@site.showTimeBefore msg.timeBefore /> ]
                               </span>

                               <#if msg.isOwner(viewerAccount.id)>
                               <span class="owner-action">
                                   <a class="pop" data-form-selector="#confirmForm" data-dialog-width="650" data-action="<@spring.url '/messages/${msg.id?replace("#","")}/_delete' />" href="#">
                                       <@spring.message 'Delete.message' />
                                   </a>
                                   <a class="pop" data-editor-name="message-editor" data-form-selector="#messageForm" data-action="<@spring.url '/messages/${msg.id?replace("#", "")}/_update' />" data-target-selector="#message-${msg.id?replace('#', '')?replace(':', '-')} .content" data-dialog-width="650" href="#">
                                       <@spring.message 'Update.message' />
                                   </a>
                               </span>
                               </#if>
                           </div>

                           <div class="content">
                               ${msg.content}
                           </div>
                       </div>

                       <#if !(topic.deleted || article.deleted) && isMember>
                       <div class="member">
                          <a class="pop" data-editor-name="reply-editor" data-form-selector="#replyForm" data-dialog-width="650" data-action="<@spring.url '/messages/${msg.id?replace("#", "")}/messageReplies/_create' />" href="#">
                              <@spring.message 'Reply.message' />
                          </a>
                       </div>
                       </#if>

                       <#if msg.replies??>
                       <ul class="replies">
                           <#list msg.replies as reply>
                           <li class="reply">
                               <div id="reply-${reply.id?replace('#','')?replace(':','-')}">
                                   <div class="owner"> 
                                       <span class="ts">
                                           <a href="/social/users/${reply.account.user.id?replace('#', '')}/posts">
                                               ${reply.account.user.displayName} 
                                           </a>
                                           [ <@site.showTimeBefore reply.timeBefore /> ]
                                       </span>

                                       <#if !(topic.deleted || article.deleted) && reply.isOwner(viewerAccount.id)>
                                       <span class="owner-action">
                                           <a class="pop" data-form-selector="#confirmForm" data-action="<@spring.url '/messageReplies/${reply.id?replace("#", "")}/_delete' />" href="#">
                                               <@spring.message 'Delete.reply' />
                                           </a>
                                           <a class="pop" data-form-selector="#replyForm" data-editor-name="reply-editor" data-action="<@spring.url '/messageReplies/${reply.id?replace("#", "")}/_update' />" data-target-selector='#reply-${reply.id?replace("#","")?replace(":", "-")} .content' href="#">
                                               <@spring.message 'Update.reply' />
                                           </a>
                                       </span>
                                       </#if>
                                   </div>

                                   <div class="content">
                                       ${reply.content}
                                   </div>
                               </div>
                           </li>
                           </#list>
                       </ul>
                       </#if>
                   </li>
                   </#list>
               </ul>
               </#if>

              <#if !(topic.deleted || article.deleted) && isMember>
              <@topicGadgets.messageForm />
              <@topicGadgets.replyForm />
              <@site.confirmForm />
              <#else>
              <@sec.authorize access="hasRole('ROLE_ADMIN')">
              <@topicGadgets.messageForm />
              <@topicGadgets.replyForm />
              <@site.confirmForm />
              </@sec.authorize>
              </#if>
            </div>

            <div id="main-sidebar">
               <@topicGadgets.showPlanSidebar />
            </div>

        </div>
    </div>
</@site.layout>
