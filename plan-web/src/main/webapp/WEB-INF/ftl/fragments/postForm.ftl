<#import "../spring.ftl" as spring />

            <form action="<@spring.url '/plan/plans/${planId}/posts'/>" method="post">
                <fieldset>
                    <legend>please select post type</legend>
                    <label>Concept  <input type="radio" name="type" value="concept"/></label>
                    <label>Note  <input type="radio" name="type" value="note"/></label>
                    <label>Question  <input type="radio" name="type" value="question"/></label>
                    <label>Tutorial  <input type="radio" name="type" value="tutorial"/></label>
                </fieldset>

                <fieldset class="post">
                    <legend>your post</legend>
                    <ul>
                        <li>
                            <label for="title">title</label>
                            <input type="input" id="title" name="title" />
                        </li>
                        <li>
                            <label for="usercontent">content</label>
                            <textarea id="usercontent" name="content"></textarea>
                        </li>
                        <li>
                            <input type="submit" value="submit" />
                        </li>
                    </ul>
                </fieldset>
            </form>

<#--
        <script type="text/javascript">
            $(document).ready(function() {
                CKEDITOR.replace('usercontent');

                $('form').submit(function(e) {
                    $.post(this.action, $(this).serialize(), function(res, status) {
                        $('#content').html(res); 
                    });
                    return false;
                });
            });
        </script>
        -->
 

