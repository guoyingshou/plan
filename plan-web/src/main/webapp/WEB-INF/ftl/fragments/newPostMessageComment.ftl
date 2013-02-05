<#import "../spring.ftl" as spring />
<li class="message-comment-item">
    <div class="ts">
        ${postMessageComment.user.displayName}
    </div>
    <div class="content">${postMessageComment.content}</div>
    <a class="one-item-edit" href="#" data-action="<@spring.url '/messageComments/${postMessageComment.id}' />">edit</a>
    <a class="del" data-action="<@spring.url '/messageComments/${postMessageComment.id}/delete' />" href="#">delete</a>
</li>
