<#import '../spring.ftl' as spring />
<#import '../utilGadgets.ftl' as utilGadgets />

<li class="answer-item">
    <div class="ts">
        ${answer.user.displayName} 
        [ <@utilGadgets.showTimeBefore answer.timeBefore /> ]
    </div>

    <div id="answer-${answer.id}-content">${answer.content}</div>

    <a class="item-edit" data-action="<@spring.url '/answers/${answer.id}' />" data-target="#answer-${answer.id}-content" href="#">
        <@spring.message 'i18n.action.edit' />
    </a>
    <a class="del" data-action="<@spring.url '/answers/${answer.id}/delete' />" href="#">
        <@spring.message 'i18n.action.delete' />
    </a>
    <a class="item-add" data-action="<@spring.url '/answers/${answer.id}/comments' />" data-target="#answer-${answer.id}-comments" href="#">
        <@spring.message 'i18n.action.comment' />
    </a>
 
    <ul id="answer-${answer.id}-comments"></ul>
</li>

 
