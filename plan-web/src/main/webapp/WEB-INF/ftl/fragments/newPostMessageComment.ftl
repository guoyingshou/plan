<#import "../spring.ftl" as spring />
<li class="messageCommentItem">
        <div>
           ${postMessageComment.content}
        </div>
        <a class="one-item-edit" href="#" data-action="<@spring.url '/plan/messageComments/${postMessageComment.id}' />">edit</a>
        <a class="msg-comment-del" data-action="<@spring.url '/plan/messageComments/${postMessageComment.id}/delete' />" href="#">delete</a>
    </div>
</li>
