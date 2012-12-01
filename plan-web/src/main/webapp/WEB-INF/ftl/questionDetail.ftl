<#import "spring.ftl" as spring />

<div id="questionDetail">
    <h3>title: ${post.title}</h3>
    <p class="author">user: ${post.user.displayName}</p>
    <p class="entry">content: ${post.content}</p>
    <#if post.comments??>
        <div>
            <h2>comments: </h2>
            <ul>
                <#list post.comments as questionComment>
                    <li>
                        <div>${questionComment.content}</div>
                   </li>
                </#list>
            </ul>
        </div>
    </#if>

    <div>
        <p><a class="comment" href="#">comment</a></p>
        <form class="commentForm" action="<@spring.url '/plan/questions/${post.id}/comments'/>">
            <textarea name="content"></textarea>
            <input type="submit" value="submit" />
        </form>
    </div>
 
    <div class="answers">
        <#if post.answers??>
            <h2>answers: </h2>
            <ul>
                <#list post.answers as answer>
                    <li>
                        <div>
                            <div>${answer.content}</div>

                            <div>
                                <#if answer.answerComments??>
                                    <ul>
                                        <#list answer.answerComments as comment>
                                            <li>${comment.content}</li>
                                        </#list>
                                    </ul>
                                </#if>
                            </div>
                        </div>
                        <div>
                            <p><a class="comment" href="#">comment</a></p>
                            <form class="commentForm" action= "<@spring.url '/plan/posts/${post.id}/answers/${answer.id}/comments'/>">
                                    <textarea name="content"></textarea>
                                    <input type="submit" value="submit" />
                            </form>
                        </div>
                    </li>
                </#list>
            </ul>
        </#if>
    </div>

    <form id="answerForm" action="<@spring.url '/plan/posts/${post.id}/answers' />" > 
        <fieldset>
            <legend>Your Answer </legend>
            <textarea id="answer" name="content" cols="60" rows="20"></textarea>
            <input type="submit" value="submit" />
        </fieldset>
    </form>

    <script type="text/javascript">
            $(document).ready(function() {
                CKEDITOR.instances = [];
                $('#answer').ckeditor(); 

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
