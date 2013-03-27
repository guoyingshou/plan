<#import "spring.ftl" as spring />
<#import "commonGadgets.ftl" as commonGadgets />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in commonGadgets>

<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#assign title="post detail" in commonGadgets>

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
            <div id="main-content">
                <div class="article">
                    <div class="item-ts">
                        <a href="/social/users/${article.account.user.id?replace("#", "")}/posts">
                            ${article.account.user.displayName} 
                        </a>
                        [ <@commonGadgets.showTimeBefore article.timeBefore /> ] 

                        <@sec.authorize access="hasRole('ROLE_ADMIN')">
                        <a class="delete action" data-action="<@spring.url '/articles/${article.id?replace("#", "")}/_delete' />" href="#">
                            <@spring.message 'Delete.article' />
                        </a>
                        </@sec.authorize>
                    </div>

                    <h3 class="item-title">
                        ${article.title}
                        <#if article.deleted>
                        <span>[ closed ]</span>
                        </#if>
                    </h3>

                   <div class="item-content">
                       ${article.content}
                   </div>

                   <div class="response">
                       <#if !(topic.deleted || article.deleted) && isMember>
                       <a class="create-message action" data-action="<@spring.url '/articles/${article.id?replace("#","")}/messages/_create' />" href="#">
                           <@spring.message 'AddMessage.article' />
                       </a>
                       <#if article.isOwner(viewerAccount.id)>
                       <a class="delete action" data-action="<@spring.url '/articles/${article.id?replace("#", "")}/_delete' />" href="#">
                           <@spring.message 'Delete.article' />
                       </a>
                       <a class="action" href="<@spring.url '/articles/${article.id?replace("#","")}/_update' />">
                           <@spring.message 'Update.article' />
                       </a>
                       </#if>
                       </#if>
                   </div>
               </div>

               <ul class="messages">
                   <#if article.messages??>
                   <#list article.messages as msg>
                   <li class="message">
                       <div class="item-ts">
                           <a href="/social/users/${msg.account.user.id?replace("#","")}/posts">
                               ${msg.account.user.displayName}  
                           </a>
                           [ <@commonGadgets.showTimeBefore msg.timeBefore /> ]
                       </div>

                       <div id="message-${msg.id?replace("#", "")?replace(":", "-")}-content" class="item-content">
                           ${msg.content}
                       </div>

                      <div class="response">
                          <#if !(topic.deleted || article.deleted) && isMember>
                          <a class="create-reply action" data-action="<@spring.url '/messages/${msg.id?replace("#", "")}/messageReplies/_create' />" href="#">
                              <@spring.message 'Reply.message' />
                          </a>

                          <#if msg.isOwner(viewerAccount.id)>
                          <a class="delete action" data-action="<@spring.url '/messages/${msg.id?replace("#","")}/_delete' />" href="#">
                              <@spring.message 'Delete.message' />
                          </a>
                          <a class="update-message action" data-action="<@spring.url '/messages/${msg.id?replace("#", "")}/_update' />" data-target="#message-${msg.id?replace("#", "")?replace(":", "-")}-content" href="#">
                              <@spring.message 'Update.message' />
                          </a>
                          </#if>
                          </#if>
                     </div>

                     <ul id="message-${msg.id?replace("#", "")?replace(":", "-")}-replies" class="replies">
                         <#if msg.replies??>
                         <#list msg.replies as reply>
                         <li class="reply">
                             <div class="item-ts"> 
                                 <a href="/social/users/${reply.account.user.id?replace("#", "")}/posts">
                                     ${reply.account.user.displayName} 
                                 </a>
                                 [ <@commonGadgets.showTimeBefore reply.timeBefore /> ]
                             </div>
                            <div id="message-reply-${reply.id?replace("#", "")?replace(":", "-")}-content" class="item-content">
                                 ${reply.content}
                            </div>

                            <div class="response">
                                <#if !(topic.deleted || article.deleted) && isMember && reply.isOwner(viewerAccount.id)>
                                <a class="delete action" data-action="<@spring.url '/messageReplies/${reply.id?replace("#", "")}/_delete' />" href="#">
                                    <@spring.message 'Delete.reply' />
                                </a>
                                <a class="update-reply action" data-action="<@spring.url '/messageReplies/${reply.id?replace("#", "")}/_update"}' />" data-target="#message-reply-${reply.id?replace("#","")?replace(":", "-")}-content" href="#">
                                    <@spring.message 'Update.reply' />
                                </a>
                                </#if>
                            </div>
                         </li>
                         </#list>
                         </#if>
                     </ul>
                 </li>
                 </#list>
                 </#if>
              </ul>

              <#if !(topic.deleted || article.deleted) && isMember>
              <@topicGadgets.messageForm />
              <@topicGadgets.replyForm />
              <@commonGadgets.deleteConfirmForm />
              <#else>
              <@sec.authorize access="hasRole('ROLE_ADMIN')">
              <@topicGadgets.messageForm />
              <@topicGadgets.replyForm />
              <@commonGadgets.deleteConfirmForm />
              </@sec.authorize>
              </#if>
            </div>

            <div id="main-sidebar">
               <@topicGadgets.showPlanSidebar />
            </div>

        </div>
    </div>
</@commonGadgets.layout>
