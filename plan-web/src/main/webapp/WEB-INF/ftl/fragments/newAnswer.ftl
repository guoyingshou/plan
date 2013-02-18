<#import '../spring.ftl' as spring />
<#import '../utilGadgets.ftl' as utilGadgets />

<li class="answer-item">
    <div class="ts">
        ${answer.user.displayName} 
        [ <@utilGadgets.showTimeBefore answer.timeBefore /> ]
    </div>

    <div id="answer-${answer.id?replace("#","")?replace(":", "-")}-content">${answer.content}</div>

    <a class="item-edit" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}' />" data-target="#answer-${answer.id?replace("#","")?replace(":", "-")}-content" href="#">
        <@spring.message 'i18n.action.edit' />
    </a>
    <a class="del" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/delete' />" href="#">
        <@spring.message 'i18n.action.delete' />
    </a>
    <a class="item-add" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/comments' />" data-target="#answer-${answer.id?replace("#", "")?replace(":", "-")}-comments" href="#">
        <@spring.message 'i18n.action.comment' />
    </a>
 
    <ul id="answer-${answer.id?replace("#", "")?replace(":", "-")}-comments"></ul>
</li>

 
