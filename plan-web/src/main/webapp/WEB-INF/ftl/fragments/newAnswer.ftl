<#import '../spring.ftl' as spring />

<li class="answer-item">
    <div>${answer.content}</div>

    <a class="one-item-edit" data-action="<@spring.url '/answers/${answer.id}' />" href="#">edit</a>
    <a class="del" data-action="<@spring.url '/answers/${answer.id}/delete' />" href="#">delete</a>
    <a class="answer-comment-add" data-id="${answer.id}" href="#">comment</a>
    <ul class="answer-comments-${answer.id}></ul>
</li>

 
