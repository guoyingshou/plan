<#import "../spring.ftl" as spring />
<li class="message-item">
    <div class="ts">${postMessage.user.displayName}</div>
    <div id="message-${postMessage.id?replace("#", "")?replace(":","-")}-content">${postMessage.content}</div>

    <a class="update-item" href="#" data-action="<@spring.url '/messages/${postMessage.id?replace("#","")}/_update' />" data-target="#message-${postMessage.id?replace("#", "")?replace(":","-")}-content">
        <@spring.message 'i18n.action.edit' />
    </a>
    <a class="delete-item" href="#" data-action="<@spring.url '/messages/${postMessage.id?replace("#","")}/_delete' />">
        <@spring.message 'i18n.action.delete' />
    </a>
    <a class="create-item" href="#" data-action="<@spring.url '/messages/${postMessage.id?replace("#","")}/comments/_create' />" data-target="#message-${postMessage.id?replace("#", "")?replace(":","-")}-comments">
        <@spring.message 'i18n.action.reply' />
    </a>

    <ul id="message-${postMessage.id?replace("#","")?replace(":", "-")}-comments"></ul>
</li>
