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
                            <label for="posteditor">content</label>
                            <textarea id="posteditor" name="content">${post.content}</textarea>
                        </li>
                        <li>
                            <input type="submit" value="submit" />
                        </li>
                    </ul>
                    </fieldset>
                </form>
            </div>
            <script type="text/javascript">
                $(document).ready(function() {
                    CKEDITOR.replace('posteditor');
                });
            </script>
        </div>
    </div>

</@tissue.layout>
