<#import '../spring.ftl' as spring />
<#import '../utilGadgets.ftl' as utilGadgets />

<li>
    <div class="ts">
        ${questionComment.user.displayName} 
        [ <@utilGadgets.showTimeBefore questionComment.timeBefore /> ]
    </div>

    <div id="question-comment-${questionComment.id?replace("#", "")?replace(":", "-")}-content">${questionComment.content}</div>

    <a class="item-edit" data-action="<@spring.url '/questionComments/${questionComment.id?replace("#", "")}' />" data-target="#question-comment-${questionComment.id?replace("#", "")?replace(":", "-")}-content" href="#">
        <@spring.message 'i18n.action.edit' />
    </a>
    <a class="del" data-action="<@spring.url '/questionComments/${questionComment.id?replace("#", "")}/delete' />" href="#">
        <@spring.message 'i18n.action.delete' />
    </a>
    
</li>
 
