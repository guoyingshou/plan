<#import "../spring.ftl" as spring />
<li class="messageItem">
    <div>
        <div>
           ${postMessage.content}
        </div>
        <a class="one-item-edit" href="#" data-action="<@spring.url '/messages/${postMessage.id}' />">edit</a>
        <a class="msg-comment-add" href="#" data-id="${postMessage.id}">comment</a>
        <ul class="messageComments-${postMessage.id}"></ul>
    </div>
</li>
