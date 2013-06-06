<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title= question.title in site />

<@site.layout>

    <@topicGadgets.topicHeader />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <div class="question">
                    <h2 class="title">
                        ${question.title}
                    </h2>
                    <div class="post-meta">
                        <span class="owner">
                            <a class="username" href="/social/users/${question.account.user.id?replace("#","")}/posts">
                                ${question.account.user.displayName} 
                            </a>
                            [ <@site.showTimeBefore question.timeBefore /> ]
                        </span>

                        <#if !topic.deleted && !question.deleted>

                        <#if isMember>
                        <span class="member-action">
                            <a class="pop" data-form-selector="#questionCommentForm" data-editor-name="questionComment-editor" data-action="<@spring.url '/questions/${question.id?replace("#", "")}/questionComments/_create' />" href="#">
                                <@spring.message 'CommentText.link' />
                           </a>
                        </span>
                        </#if>

                        <#if viewerAccount?? && question.isAllowed(viewerAccount, 'ROLE_ADMIN')>
                        <span class="owner-action">
                            <a class="pop" data-form-selector="#confirmForm" data-action="<@spring.url '/questions/${question.id?replace("#", "")}/_delete' />" href="#">
                               <@spring.message 'DeleteText.link' />
                            </a>
                        </span>
                        <span class="owner-action">
                            <a href="<@spring.url '/questions/${question.id?replace("#", "")}/_update' />">
                                <@spring.message 'UpdateText.link' />
                            </a>
                        </span>
                        </#if>

                        </#if>
                    </div>

                    <div class="content">
                        ${question.content}
                    </div>

                    <#if question.comments??>
                    <ul class="question-comments">
                       <#list question.comments as questionComment>
                       <li>
                           <div class="question-comment" id="question-comment-${questionComment.id?replace("#","")?replace(":","-")}">
                               <div class="meta">
                                   <span class="owner">
                                       <a class="username" href="/social/users/${questionComment.account.user.id?replace("#","")}/posts">
                                           ${questionComment.account.user.displayName} 
                                       </a>
                                       [ <@site.showTimeBefore questionComment.timeBefore /> ]
                                   </span>

                                   <#if !topic.deleted && !question.deleted && viewerAccount?? && questionComment.isAllowed(viewerAccount, 'ROLE_ADMIN')>
                                   <div class="owner-action">
                                       <a class="pop" data-form-selector="#confirmForm" data-action="<@spring.url '/questionComments/${questionComment.id?replace("#", "")}/_delete' />" href="#">
                                           <@spring.message 'DeleteText.link' />
                                       </a>
                                   </div>
                                   </#if>

                               </div>

                               <div class="content">
                                   ${questionComment.content}
                               </div>
                           </div>
                       </li>
                       </#list>
                    </ul>
                    </#if>
                </div>

                <#if question.answers??>
                <ul class="answers">
                     <#list question.answers as answer>
                     <li>
                         <div class="answer" id="answer-${answer.id?replace("#","")?replace(":","-")}">
                             <div class="meta">
                                 <span class="owner">
                                     <a class="username" href="/social/users/${answer.account.user.id?replace("#","")}/posts">
                                         ${answer.account.user.displayName} 
                                     </a>
                                     [ <@site.showTimeBefore answer.timeBefore /> ]
                                 </span>

                                 <#if !topic.deleted && !question.deleted>

                                 <#if isMember>
                                 <span class="member-action">
                                     <a class="pop" data-form-selector="#answerCommentForm" data-editor-name="answerComment-editor" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/comments/_create' />" href="#">
                                         <@spring.message 'CommentText.link' />
                                     </a>
                                 </span>
                                 </#if>
                                
                                 <#if viewerAccount?? && answer.isAllowed(viewerAccount, 'ROLE_ADMIN')>
                                 <span class="owner-action">
                                     <a class="pop" data-form-selector="#confirmForm" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/_delete' />" href="#">
                                         <@spring.message 'DeleteText.link' />
                                     </a>
                                 </span>
                                 </#if>

                                 </#if>
                             </div>

                             <div class="content">
                                 ${answer.content}
                             </div>
                         </div>

                         <#if answer.comments??>
                         <ul class="answer-comments">
                             <#list answer.comments as comment>
                             <li>
                                 <div class="answer-comment" id="answer-comment-${comment.id?replace('#','')?replace(':','-')}">
                                     <div class="meta">
                                         <span class="owner">
                                             <a class="username" href="/social/users/${comment.account.user.id?replace('#','')}/posts">
                                                 ${comment.account.user.displayName} 
                                             </a>
                                             [ <@site.showTimeBefore comment.timeBefore /> ]
                                         </span>

                                         <#if !topic.deleted && !question.deleted && viewerAccount?? && comment.isAllowed(viewerAccount, 'ROLE_ADMIN')>
                                         <span class="owner-action">
                                             <a class="pop" data-form-selector="#confirmForm" data-action="<@spring.url '/answerComments/${comment.id?replace("#","")}/_delete' />" href="#">
                                                 <@spring.message 'DeleteText.link' />
                                             </a>
                                         </span>
                                         </#if>

                                     </div>

                                     <div class="content">
                                         ${comment.content}
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

                <#if !topic.deleted && !question.deleted && (isMember || (viewerAccount?? && viewerAccount.hasRole('ROLE_ADMIN')))>
                <div class="member-form">
                   <@spring.bind "answerForm.*" />
                   <div class="error">
                       <@spring.showErrors "<br>" />
                   </div>

                   <form id="answerForm" method="post" action="<@spring.url '/questions/${question.id?replace("#", "")}/answers/_create' />">
                       <legend>
                           <@spring.message "Legend.answerForm" />
                       </legend>
                       <ul>
                           <li>
                               <@spring.formTextarea "answerForm.content" />
                           </li>
                           <li>
                               <input type="submit" value="submit"/>
                           </li>
                       </ul>
                    </form>
                    <script type="text/javascript">
                        CKEDITOR.replace("content");
                    </script>
                </div>

                <@topicGadgets.questionCommentForm />
                <@topicGadgets.answerCommentForm />
                <@site.confirmForm />

                </#if>
            </div>

            <div id="main-sidebar">
                <@topicGadgets.showPlansArchived />
            </div>
        </div>
    </div>
</@site.layout>


