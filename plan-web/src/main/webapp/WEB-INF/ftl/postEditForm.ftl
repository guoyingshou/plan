<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["http://www.tissue.com/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/topicForm.css"] in tissue>

<@tissue.layout "topic">

    <div id="logo">
        <@tissue.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content" class="postEditForm">
            <div id="contentInner">
                <form action="<@spring.url '/plan/posts/${post.id}'/>" method="post">
                    <fieldset class="post">
                    <legend>your post</legend>
                    <ul>
                        <li>
                            <label for="title">title</label>
                            <input type="input" id="title" name="title" value="${post.title}" />
                        </li>
                        <li>
                            <label for="usercontent">content</label>
                            <textarea id="usercontent" name="content">${post.content}</textarea>
                        </li>
                        <li>
                            <input type="submit" value="submit" />
                        </li>
                    </ul>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>

</@tissue.layout>
