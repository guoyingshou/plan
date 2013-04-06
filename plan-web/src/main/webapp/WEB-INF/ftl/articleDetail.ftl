<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign title= article.title  in site>

<@site.layout>

    <@topicGadgets.topicHeader />

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

                        <#if !topic.deleted && !article.deleted>
                        <#if viewerAccount?? && article.isOwner(viewerAccount.id)>
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
                        </#if>
                    </div>

                    <div class="content">
                       ${article.content}
                    </div>
                </div>

                <#if article.messages??>
                <ul class="messages">
                   <#list article.messages as msg>
                   <li>
                       <div class="message" id="message-${msg.id?replace("#","")?replace(":","-")}">
                           <div class="owner">
                               <div class="ts">
                                   <a href="/social/users/${msg.account.user.id?replace("#","")}/posts">
                                       ${msg.account.user.displayName}  
                                   </a>
                                   [ <@site.showTimeBefore msg.timeBefore /> ]
                               </div>

                               <#if !topic.deleted && !article.deleted>

                               <#if isMember>
                               <div class="member-action">
                                   <a class="pop" data-editor-name="reply-editor" data-form-selector="#replyForm" data-dialog-width="650" data-action="<@spring.url '/messages/${msg.id?replace("#", "")}/messageReplies/_create' />" href="#">
                                       <@spring.message 'Reply.message' />
                                   </a>
                               </div>
                               </#if>

                               <#if viewerAccount?? && msg.isOwner(viewerAccount.id)>
                               <div class="owner-action">
                                   <a class="pop" data-form-selector="#confirmForm" data-dialog-width="650" data-action="<@spring.url '/messages/${msg.id?replace("#","")}/_delete' />" href="#">
                                       <@spring.message 'Delete.message' />
                                   </a>
                               </div>
                               </#if>

                               </#if>
                           </div>

                           <div class="content">
                               ${msg.content}
                           </div>
                       </div>

<#--
                       <#if !(topic.deleted || article.deleted) && isMember>
                       <div class="member">
                          <a class="pop" data-editor-name="reply-editor" data-form-selector="#replyForm" data-dialog-width="650" data-action="<@spring.url '/messages/${msg.id?replace("#", "")}/messageReplies/_create' />" href="#">
                              <@spring.message 'Reply.message' />
                          </a>
                       </div>
                       </#if>
                       -->

                       <#if msg.replies??>
                       <ul class="replies">
                           <#list msg.replies as reply>
                           <li>
                               <div class="reply" id="reply-${reply.id?replace('#','')?replace(':','-')}">
                                   <div class="owner"> 
                                       <span class="ts">
                                           <a href="/social/users/${reply.account.user.id?replace('#', '')}/posts">
                                               ${reply.account.user.displayName} 
                                           </a>
                                           [ <@site.showTimeBefore reply.timeBefore /> ]
                                       </span>

                                       <#if !(topic.deleted || article.deleted) && viewerAccount?? && reply.isOwner(viewerAccount.id)>
                                       <span class="owner-action">
                                           <a class="pop" data-form-selector="#confirmForm" data-action="<@spring.url '/messageReplies/${reply.id?replace("#", "")}/_delete' />" href="#">
                                               <@spring.message 'Delete.reply' />
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

                <#if !(topic.deleted || article.deleted) && viewerAccount?? && isMember>
                <div class="member-form">
                    <@spring.bind "messageForm.*" />
                    <div class="error">
                        <@spring.showErrors "<br>" />
                    </div>

                    <form method="post" action="<@spring.url '/articles/${article.id?replace("#","")}/messages/_create' />">
                        <legend>
                            <@spring.message "message" />
                        </legend>
                        <ul>
                            <li>
                                <@spring.formTextarea "messageForm.content" />
                            </li>
                            <li>
                                <input type="submit" value='<@spring.message "Submit.button"/>' />
                            </li>
                        </ul>
                   </form>
                   <script type="text/javascript">
                       CKEDITOR.replace("content");
                   </script>
                </div>
                </#if>


              <#if !(topic.deleted || article.deleted) && isMember>
              <@topicGadgets.replyForm />
              <@site.confirmForm />
              <#else>
              <@sec.authorize access="hasRole('ROLE_ADMIN')">
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
