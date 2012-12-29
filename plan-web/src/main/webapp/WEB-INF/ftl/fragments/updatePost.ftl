<#import "../spring.ftl" as spring />

<div>
    <h3 class="post-title">${post.title}</h3>
    <div class="post-content">${post.content}</div>

    <a class="post-edit" data-action="<@spring.url '/posts/${post.id}' />" href="#">edit</a>
    <#if post.type = 'question'>
        <a class="question-comment-add" href="#">comment</a></p>
    </#if>
</div>
