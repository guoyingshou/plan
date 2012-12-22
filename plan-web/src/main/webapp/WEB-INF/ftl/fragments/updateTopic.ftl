<#import "../spring.ftl" as spring />

<div>
    <div class="topic-content">${topic.content}</div>
    <div class="topic-tags"><#list topic.tags as tag>${tag}&nbsp;</#list></div>
</div>
<#--
<a class="topic-edit" data-action="<@spring.url '/plan/topics/${topic.id}' />" href="#">edit</a>
-->
