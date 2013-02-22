<#import "../spring.ftl" as spring />
<li class="comment">
    <div class="item-ts">
        ${messageComment.account.user.displayName}
    </div>
    <div id="message-comment-${messageComment.id?replace("#", "")?replace(":", "-")}-content" class="item-content">
        ${messageComment.content}
    </div>

    <div class="response">
        <a class="update-item action" href="#" data-action="<@spring.url '/messageComments/${messageComment.id?replace("#", "")}/_update' />" data-target="#message-comment-${messageComment.id?replace("#", "")?replace(":", "-")}-content">
            <@spring.message 'i18n.action.edit' />
        </a>
        <a class="delete-item action" data-action="<@spring.url '/messageComments/${messageComment.id?replace("#", "")}/_delete' />" href="#">
            <@spring.message 'i18n.action.delete' />
        </a>
    </div>
</li>
