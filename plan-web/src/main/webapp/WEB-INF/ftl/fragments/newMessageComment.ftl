<#import "../spring.ftl" as spring />
<li class="message-comment-item">
    <div class="ts">
        ${messageComment.user.displayName}
    </div>
    <div id="message-comment-${messageComment.id}-content">${messageComment.content}</div>

    <a class="item-edit" href="#" data-action="<@spring.url '/messageComments/${messageComment.id}' />" data-target="#message-comment-${messageComment.id}-content">
        <@spring.message 'i18n.action.edit' />
    </a>
    <a class="del" data-action="<@spring.url '/messageComments/${messageComment.id}/delete' />" href="#">
        <@spring.message 'i18n.action.delete' />
    </a>
</li>
