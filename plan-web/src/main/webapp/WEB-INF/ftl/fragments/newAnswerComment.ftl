<#import '../spring.ftl' as spring />
<#import '../utilGadgets.ftl' as utilGadgets />

<li class="comment">
    <div class="item-ts">
        <a href="/social/users/${comment.account.id?replace("#","")}/posts">
            ${comment.account.user.displayName} 
        </a>
        [ <@utilGadgets.showTimeBefore comment.timeBefore /> ]
    </div>
    <div id="answer-comment-${comment.id?replace("#", "")?replace(":", "-")}-content" class="item-content">
        ${comment.content}
    </div>

    <div class="response">
        <a class="delete-item action" data-action="<@spring.url '/answerComments/${comment.id?replace("#", "")}/_delete' />" href="#">
            <@spring.message 'i18n.action.delete' />
        </a>

        <a class="update-item action" data-action="<@spring.url '/answerComments/${comment.id?replace("#", "")}/_update'/>" data-target="#answer-comment-${comment.id?replace("#", "")?replace(":", "-")}-content" href="#">
            <@spring.message 'i18n.action.edit' />
        </a>
    </div>
</li>
 
