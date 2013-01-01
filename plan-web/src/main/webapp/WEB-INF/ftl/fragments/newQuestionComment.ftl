<#import '../spring.ftl' as spring />

<li>
    <div>${questionComment.content}</div>
        <a class="one-item-edit" data-action="<@spring.url '/questionComments/${questionComment.id}' />" href="#">edit</a>
        <a class="del" data-action="<@spring.url '/questionComments/${questionComment.id}/delete' />" href="#">delete</a>
</li>
 
