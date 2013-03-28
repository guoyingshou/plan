<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign title="question" in site />

<@site.layout>

    <#include "topicHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-sidebar">
                <@topicGadgets.showPlanSidebar />
            </div>
            <div id="main-content">
                <div class="question">
                    <div class="item-ts">
                        <a href="/social/users/${question.account.user.id?replace("#","")}/questions">
                            ${question.account.user.displayName} 
                        </a>
                        [ <@site.showTimeBefore question.timeBefore /> ]
                        <#if !(topic.deleted || question.deleted)>
                        <@sec.authorize access="hasRole('ROLE_ADMIN')">
                        <a class="delete  action" data-action="<@spring.url '/questions/${question.id?replace("#", "")}/_delete' />">
                            <@spring.message 'Delete.question' />
                        </a>
                        </@sec.authorize>
                        </#if>
                    </div>

                    <h3 class="item-title">${question.title}</h3>
                    <div class="item-content">
                        ${question.content}
                    </div>

                   <div class="response">
                       <#if !(topic.deleted || question.deleted) && isMember>
                       <a class="create-questionComment action" data-action="<@spring.url '/questions/${question.id?replace("#", "")}/questionComments/_create' />" href="#">
                           <@spring.message 'Comment.question' />
                       </a>
                       <a class="create-answer action" data-action="<@spring.url '/questions/${question.id?replace("#", "")}/answers/_create' />" href="#">
                           <@spring.message 'Answer.question' />
                       </a>

                       <#if question.isOwner(viewerAccount.id)>
                       <a class="delete action" data-action="<@spring.url '/questions/${question.id?replace("#", "")}/_delete' />" href="#">
                           <@spring.message 'Delete.question' />
                       </a>
                       <a class="action" href="<@spring.url '/questions/${question.id?replace("#", "")}/_update' />">
                           <@spring.message 'Update.question' />
                       </a>
                       </#if>
                       </#if>
                   </div>
               </div>

               <ul class="question-comments">
                   <#if question.comments??>
                   <#list question.comments as questionComment>
                   <li class="comment">
                       <div class="item-ts">
                           <a href="/social/users/${questionComment.account.user.id?replace("#","")}/questions">
                               ${questionComment.account.user.displayName} 
                           </a>
                           [ <@site.showTimeBefore questionComment.timeBefore /> ]
                       </div>

                       <div id="question-comment-${questionComment.id?replace("#","")?replace(":", "-")}-content" class="item-content">
                           ${questionComment.content}
                       </div>

                       <div class="response">
                           <#if !(topic.deleted || question.deleted) && isMember && questionComment.isOwner(viewerAccount.id)>
                           <a class="delete action" data-action="<@spring.url '/questionComments/${questionComment.id?replace("#", "")}/_delete' />" href="#">
                               <@spring.message 'Delete.comment' />
                           </a>
                           <a class="update-questionComment action" data-action="<@spring.url '/questionComments/${questionComment.id?replace("#", "")}/_update' />" data-target="#question-comment-${questionComment.id?replace("#", "")?replace(":", "-")}-content" href="#">
                               <@spring.message 'Update.comment' />
                           </a>
                           </#if>
                        </div>
                    </li>
                    </#list>
                    </#if>
                 </ul>

                 <ul class="answers">
                     <#if question.answers??>
                     <#list question.answers as answer>
                     <li class="answer">
                         <div class="item-ts">
                             <a href="/social/users/${answer.account.user.id?replace("#","")}/questions">
                                 ${answer.account.user.displayName} 
                             </a>
                             [ <@site.showTimeBefore answer.timeBefore /> ]
                         </div>
                         <div id="answer-${answer.id?replace("#", "")?replace(":", "-")}-content" class="item-content">
                             ${answer.content}
                         </div>

                        <div class="response">
                            <#if !(topic.deleted || question.deleted) && isMember>
                            <a class="create-answerComment action" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/comments/_create' />" href="#">
                                <@spring.message 'Comment.answer' />
                            </a>

                            <#if answer.isOwner(viewerAccount.id)>
                            <a class="delete action" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/_delete' />" href="#">
                                <@spring.message 'Delete.answer' />
                            </a>
                            <a class="update-answer action" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/_update' />" data-target="#answer-${answer.id?replace("#", "")?replace(":", "-")}-content" href="#">
                                 <@spring.message 'Update.answer' />
                            </a>
                            </#if>
                            </#if>
                        </div>

                        <ul id="answer-${answer.id?replace("#", "")?replace(":", "-")}-comments" class="comments">
                            <#if answer.comments??>
                            <#list answer.comments as comment>
                            <li class="comment">
                            <div class="item-ts">
                                <a href="/social/users/${comment.account.user.id?replace("#","")}/questions">
                                    ${comment.account.user.displayName} 
                                </a>
                                [ <@site.showTimeBefore comment.timeBefore /> ]
                            </div>

                           <div id="answer-comment-${comment.id?replace("#", "")?replace(":", "-")}-content" class="item-content">
                               ${comment.content}
                           </div>

                           <div class="response">
                               <#if !(topic.deleted ||question.deleted) && isMember && comment.isOwner(viewerAccount.id)>
                               <a class="delete action" data-action="<@spring.url '/answerComments/${comment.id?replace("#","")}/_delete' />" href="#">
                                   <@spring.message 'Delete.comment' />
                               </a>
                               <a class="update-answerComment action" data-action="<@spring.url '/answerComments/${comment.id?replace("#", "")}/_update'/>" data-target="#answer-comment-${comment.id?replace("#", "")?replace(":", "-")}-content" href="#">
                                  <@spring.message 'Update.comment' />
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

               <#if !(topic.deleted || question.deleted) && isMember>
               <@topicGadgets.questionCommentForm />
               <@topicGadgets.answerForm />
               <@topicGadgets.answerCommentForm />
               <@site.deleteConfirmForm />
               <#else>
               <@sec.authorize access="hasRole('ROLE_ADMIN')">
               <@topicGadgets.questionCommentForm />
               <@topicGadgets.answerForm />
               <@topicGadgets.answerCommentForm />
               <@site.deleteConfirmForm />
               </@sec.authorize>
               </#if>
            </div>
        </div>
    </div>
</@site.layout>


