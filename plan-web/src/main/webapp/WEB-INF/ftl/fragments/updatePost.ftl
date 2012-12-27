<#import "../spring.ftl" as spring />

<div class="article">
    <h3 class="article-title">${post.title}</h3>
    <div class="article-content">${post.content}</div>

    <a class="post-edit" data-action="<@spring.url '/posts/${post.id}' />" href="#">edit</a>
</div>
