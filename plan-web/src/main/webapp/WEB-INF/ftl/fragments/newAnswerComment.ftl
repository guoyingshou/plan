<#import '../spring.ftl' as spring />
<#import '../utilGadgets.ftl' as utilGadgets />

<li class="answer-comment-item">
    <div class="ts">
        ${comment.user.displayName} 
        [ <@utilGadgets.showTimeBefore comment.timeBefore /> ]
    </div>
    <div id="answer-comment-${comment.id?replace("#", "")?replace(":", "-")}-content">${comment.content}</div>

    <a class="update-item" data-action="<@spring.url '/answerComments/${comment.id?replace("#", "")}/_update'/>" data-target="#answer-comment-${comment.id?replace("#", "")?replace(":", "-")}-content" href="#">
        <@spring.message 'i18n.action.edit' />
    </a>
    <a class="delete-item" data-action="<@spring.url '/answerComments/${comment.id?replace("#", "")}/_delete' />" href="#">
        <@spring.message 'i18n.action.delete' />
    </a>

</li>
 
