<#import "../spring.ftl" as spring />
<li class="message">
    <div class="item-ts">${postMessage.account.user.displayName}</div>
    <div id="message-${postMessage.id?replace("#", "")?replace(":","-")}-content" class="item-content">
        ${postMessage.content}
    </div>

    <div class="response">
        <a class="delete-item action" href="#" data-action="<@spring.url '/topics/${topic.id?replace("#", "")}/messages/${postMessage.id?replace("#","")}/_delete' />">
            <@spring.message 'i18n.action.delete' />
        </a>
        <a class="update-item action" href="#" data-action="<@spring.url '/topics/${topic.id?replace("#", "")}/messages/${postMessage.id?replace("#","")}/_update' />" data-target="#message-${postMessage.id?replace("#", "")?replace(":","-")}-content">
            <@spring.message 'i18n.action.edit' />
        </a>
        <a class="create-item action" href="#" data-action="<@spring.url '/topics/${topic.id?replace("#", "")}/messages/${postMessage.id?replace("#","")}/comments/_create' />" data-target="#message-${postMessage.id?replace("#", "")?replace(":","-")}-comments">
            <@spring.message 'i18n.action.reply' />
        </a>
    </div>
    <ul id="message-${postMessage.id?replace("#","")?replace(":", "-")}-comments" class="comments"></ul>
</li>
