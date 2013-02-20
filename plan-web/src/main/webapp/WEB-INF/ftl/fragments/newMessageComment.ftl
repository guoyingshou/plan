<#import "../spring.ftl" as spring />
<li class="message-comment-item">
    <div class="ts">
        ${messageComment.user.displayName}
    </div>
    <div id="message-comment-${messageComment.id?replace("#", "")?replace(":", "-")}-content">${messageComment.content}</div>

    <a class="update-item" href="#" data-action="<@spring.url '/messageComments/${messageComment.id?replace("#", "")}/_update' />" data-target="#message-comment-${messageComment.id?replace("#", "")?replace(":", "-")}-content">
        <@spring.message 'i18n.action.edit' />
    </a>
    <a class="delete-item" data-action="<@spring.url '/messageComments/${messageComment.id?replace("#", "")}/_delete' />" href="#">
        <@spring.message 'i18n.action.delete' />
    </a>
</li>
