<#import "../spring.ftl" as spring />
<li class="message-item">
    <div class="ts">${postMessage.user.displayName}</div>
    <div id="message-${postMessage.id}-content">${postMessage.content}</div>

    <a class="item-edit" href="#" data-action="<@spring.url '/messages/${postMessage.id}' />" data-target="#message-${postMessage.id}-content">
        <@spring.message 'i18n.action.edit' />
    </a>
    <a class="del" href="#" data-action="<@spring.url '/messages/${postMessage.id}/delete' />">
        <@spring.message 'i18n.action.delete' />
    </a>
    <a class="item-add" href="#" data-action="<@spring.url '/messages/${postMessage.id}/comments' />" data-target="#message-${postMessage.id}-comments">
        <@spring.message 'i18n.action.comment' />
    </a>

    <ul id="message-${postMessage.id}-comments"></ul>
</li>
