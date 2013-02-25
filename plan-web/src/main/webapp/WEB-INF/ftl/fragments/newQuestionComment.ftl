<#import '../spring.ftl' as spring />
<#import '../utilGadgets.ftl' as utilGadgets />

<li class="comment">
    <div class="item-ts">
        <a href="/social/users/${questionComment.account.id?replace("#","")}/posts">
            ${questionComment.account.user.displayName} 
        </a>
        [ <@utilGadgets.showTimeBefore questionComment.timeBefore /> ]
    </div>

    <div id="question-comment-${questionComment.id?replace("#", "")?replace(":", "-")}-content" class="item-content">
        ${questionComment.content}
    </div>

    <div class="response">
        <a class="delete-item action" data-action="<@spring.url '/topics/${topic.id?replace("#", "")}/questionComments/${questionComment.id?replace("#", "")}/_delete' />" href="#">
            <@spring.message 'i18n.action.delete' />
        </a>
        <a class="update-item action" data-action="<@spring.url '/topics/${topic.id?replace("#", "")}/questionComments/${questionComment.id?replace("#", "")}/_update' />" data-target="#question-comment-${questionComment.id?replace("#", "")?replace(":", "-")}-content" href="#">
            <@spring.message 'i18n.action.edit' />
        </a>
    </div>
</li>
 
