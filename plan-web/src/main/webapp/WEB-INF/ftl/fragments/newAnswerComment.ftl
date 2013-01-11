<#import '../spring.ftl' as spring />

<li class="answer-comment-item">
    <div>${comment.content}</div>
    <div>${comment.user.displayName} published: ${comment.createTime?datetime}</div>
    <a class="one-item-edit" data-action="<@spring.url '/answerComments/${comment.id}' />" href="#">edit</a>
    <a class="del" data-action="<@spring.url '/answerComments/${comment.id}/delete' />" href="#">delete</a>
</li>
 