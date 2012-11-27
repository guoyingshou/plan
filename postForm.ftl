<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/ckeditor/adapters/jquery.js"] in tissue>

<#assign mystyles=["/resources/css/content-2cols.css", "/resources/css/postForm.css", "/resources/css/plan.css"] in tissue>

<@tissue.layout "newPost">

    <div id="logo">
        <@tissue.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@tissue.showActivePlan />
            <@tissue.showDeadPlans />
        </div>

        <div id="content" class="postForm">
            <form action="<@spring.url '/plan/posts'/>" method="post">
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
                            <input type="hidden" name="planId" value="${topic.activePlan.id}" />
                            <input type="hidden" name="topicId" value="${topic.id}" />
                            <input type="submit" value="submit" />
                        </li>
                    </ul>
                </fieldset>
            </form>

        <script type="text/javascript">
            var config = { language: '${lang}', filebrowserImageUploadUrl : '/u2/plan/images' }; 
            config.toolbar = [
                { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
                { name: 'clipboard',items: [ 'Undo', 'Redo' ] },
                { name: 'insert',items: [ 'Image'] },
                { name: 'paragraph',items: [ 'NumberedList', 'BulletedList'] },
                { name: 'links', items : [ 'Link', 'Unlink', 'Anchor' ] }

            ];
            $(document).ready(function() {
                //CKEDITOR.replace('usercontent', config);
                $('#usercontent').ckeditor();

                $('input[type="submit"]').on("click", function(e) {

                    var url = "/u2/plan/posts";
                    $.post(url, $('form').serialize(), function(res, status) {
                        $('#content').replaceWith(res); 
                    });
                    return false;
                });
            });

        </script>
        </div>
 
    </div>
</@tissue.layout>
