<#import "../spring.ftl" as spring />
<li class="message-item">
    <div>${postMessage.content}</div>
    <div>${postMessage.user.displayName}  published: ${postMessage.createTime?datetime}</div>
    <a class="one-item-edit" href="#" data-action="<@spring.url '/messages/${postMessage.id}' />">edit</a>
    <a class="del" href="#" data-action="<@spring.url '/messages/${postMessage.id}/delete' />">delete</a>
    <a class="msg-comment-add" href="#" data-id="${postMessage.id}">comment</a>
    <ul class="message-comments-${postMessage.id}"></ul>
</li>
