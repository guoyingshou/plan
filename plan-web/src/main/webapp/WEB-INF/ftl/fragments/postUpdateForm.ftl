<#import "../spring.ftl" as spring />

            <form action="<@spring.url '/plan/posts/${post.id}'/>" method="post">
            <from>
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

        <script type="text/javascript">
            $(document).ready(function() {
                CKEDITOR.replace('usercontent');
            });
        </script>
 

