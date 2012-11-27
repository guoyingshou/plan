<#import "spring.ftl" as spring />

<div id="postDetail">
    <h3>title: ${post.title}</h3>
    <p class="author">user: ${post.user.displayName}</p>
    <p class="entry">content: ${post.content}</p>

    <div class="message">
        <#if post.messages??>
            <h2>Messages: </h2>
            <ul>
                <#list post.messages as msg>
                    <li>
                        <div>
                            <div>${msg.content}</div>

                            <div>
                                <#if msg.comments??>
                                    <ul>
                                        <#list msg.comments as comment>
                                            <li>${comment.content}</li>
                                        </#list>
                                    </ul>
                                </#if>
                            </div>
                        </div>
                        <div>
                            <p><a class="comment" href="#">comment</a></p>
                            <form class="commentForm" action= "<@spring.url '/plan/posts/${post.id}/messages/${msg.id}/comments'/>">
                                    <textarea name="content"></textarea>
                                    <input type="submit" value="submit" />
                            </form>
                        </div>
                    </li>
                </#list>
            </ul>
        </#if>
    </div>

    <form id="messageForm" action="<@spring.url '/plan/posts/${post.id}/messages' />" > 
        <fieldset>
            <legend>Leave a message</legend>
            <textarea id="message" name="content" cols="60" rows="20"></textarea>
            <input type="submit" value="submit" />
        </fieldset>
    </form>

    <script type="text/javascript">
            $(document).ready(function() {
                CKEDITOR.instances = [];
                $('#message').ckeditor(); 

                $('.commentForm').hide();
                $('a.comment').on('click', function() {
                    $target = $(this).parent().next();
                    $target.toggle();
                    return false;
                });

                $('form').submit(function() {
                    $.post(this.action, $(this).serialize(), function(res, status) {
                        $('#content').html(res);
                    });
 
                    return false;
                });

            });
    </script>
</div>
