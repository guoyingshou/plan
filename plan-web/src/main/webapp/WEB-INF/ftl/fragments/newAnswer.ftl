<#import '../spring.ftl' as spring />
<#import '../utilGadgets.ftl' as utilGadgets />

<li class="answer">
    <div class="item-ts">
        
        <a href="/social/users/${answer.account.id?replace("#","")}/posts">
            ${answer.account.user.displayName} 
        </a>
        [ <@utilGadgets.showTimeBefore answer.timeBefore /> ]
    </div>
    <div id="answer-${answer.id?replace("#","")?replace(":", "-")}-content" class="item-content">
        ${answer.content}
    </div>

    <div class="response">
        <a class="delete-item action" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/_delete' />" href="#">
            <@spring.message 'i18n.action.delete' />
        </a>
        <a class="update-item action" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/_update' />" data-target="#answer-${answer.id?replace("#","")?replace(":", "-")}-content" href="#">
            <@spring.message 'i18n.action.edit' />
        </a>
        <a class="create-item action" data-action="<@spring.url '/answers/${answer.id?replace("#", "")}/comments/_create' />" data-target="#answer-${answer.id?replace("#", "")?replace(":", "-")}-comments" href="#">
            <@spring.message 'i18n.action.comment' />
        </a>
    </div>
 
    <ul id="answer-${answer.id?replace("#", "")?replace(":", "-")}-comments" class="comments"></ul>
</li>

 
