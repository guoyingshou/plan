<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title= article.title  in site>

<@site.layout>

    <@topicGadgets.topicHeader />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <div class="article">
                    <h2 class="title">
                        ${article.title}
                        <#if article.deleted>
                        <span>[ closed ]</span>
                        </#if>
                    </h2>

                    <div class="post-meta">
                        <span class="owner">
                            <a class="username" href="/social/users/${article.account.user.id?replace("#", "")}/posts">
                                ${article.account.user.displayName} 
                            </a>
                            [ <@site.showTimeBefore article.timeBefore /> ] 
                        </span>

                        <#if !topic.deleted && !article.deleted && viewerAccount?? && article.isAllowed(viewerAccount, 'ROLE_ADMIN')>
                        <span class="owner-action">
                            <a href="<@spring.url '/articles/${article.id?replace("#","")}/_update' />">
                                <@spring.message 'UpdateText.link' />
                            </a>
                        </span>
                        <span class="owner-action">
                            <a class="pop" data-form-selector="#confirmForm" data-dialog-width="650" data-action="<@spring.url '/articles/${article.id?replace("#", "")}/_delete' />" href="#">
                                <@spring.message 'DeleteText.link' />
                            </a>
                        </span>

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
                           <div class="meta">
                               <span class="owner">
                                   <a class="username" href="/social/users/${msg.account.user.id?replace("#","")}/posts">
                                       ${msg.account.user.displayName}  
                                   </a>
                                   [ <@site.showTimeBefore msg.timeBefore /> ]
                               </span>

                               <#if !topic.deleted && !article.deleted>

                               <#if isMember>
                               <span class="member-action">
                                   <a class="pop" data-editor-name="reply-editor" data-form-selector="#replyForm" data-dialog-width="650" data-action="<@spring.url '/messages/${msg.id?replace("#", "")}/messageReplies/_create' />" href="#">
                                       <@spring.message 'ReplyText.link' />
                                   </a>
                               </span>
                               </#if>

                               <#if viewerAccount?? && msg.isAllowed(viewerAccount, 'ROLE_ADMIN')>
                               <span class="owner-action">
                                   <a class="pop" data-form-selector="#confirmForm" data-dialog-width="650" data-action="<@spring.url '/messages/${msg.id?replace("#","")}/_delete' />" href="#">
                                       <@spring.message 'DeleteText.link' />
                                   </a>
                               </span>
                               </#if>

                               </#if>
                           </div>

                           <div class="content">
                               ${msg.content}
                           </div>
                       </div>

                       <#if msg.replies??>
                       <ul class="replies">
                           <#list msg.replies as reply>
                           <li>
                               <div class="reply" id="reply-${reply.id?replace('#','')?replace(':','-')}">
                                   <div class="meta"> 
                                       <span class="owner">
                                           <a class="username" href="/social/users/${reply.account.user.id?replace('#', '')}/posts">
                                               ${reply.account.user.displayName} 
                                           </a>
                                           [ <@site.showTimeBefore reply.timeBefore /> ]
                                       </span>

                                       <#if !topic.deleted && !article.deleted && viewerAccount?? && reply.isAllowed(viewerAccount, 'ROLE_ADMIN')>
                                       <span class="owner-action">
                                           <a class="pop" data-form-selector="#confirmForm" data-action="<@spring.url '/messageReplies/${reply.id?replace("#", "")}/_delete' />" href="#">
                                               <@spring.message 'DeleteText.link' />
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

                <#if !topic.deleted && !article.deleted && (isMember || (viewerAccount?? && viewerAccount.hasRole('ROLE_ADMIN')))>

                <div class="member-form">
                    <@spring.bind "messageForm.*" />
                    <div class="error">
                        <@spring.showErrors "<br>" />
                    </div>

                    <form method="post" action="<@spring.url '/articles/${article.id?replace("#","")}/messages/_create' />">
                        <legend>
                            <@spring.message "Legend.messageForm" />
                        </legend>
                        <ul>
                            <li>
                                <@spring.formTextarea "messageForm.content" />
                            </li>
                            <li>
                                <input type="submit" value='<@spring.message "Text.formInput.submit"/>' />
                            </li>
                        </ul>
                   </form>
                   <script type="text/javascript">
                       CKEDITOR.replace("content");
                   </script>
                </div>

                <@topicGadgets.replyForm />
                <@site.confirmForm />
                </#if>
            </div>

            <div id="main-sidebar">
               <@topicGadgets.showPlansArchived />
            </div>

        </div>
    </div>
</@site.layout>
