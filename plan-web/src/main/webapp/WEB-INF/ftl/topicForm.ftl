<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["http://www.tissue.com/ckeditor/ckeditor.js", "/ckeditor/adapters/jquery.js"] in tissue>

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/topicForm.css"] in tissue>

<@tissue.layout "topic">

    <div id="logo">
        <@tissue.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

        <div id="content" class="topicForm">
            <form action="http://www.tissue.com/u2/plan/topics" method="post">
                <fieldset class="topic">
                    <legend>your topic</legend>
                        <ul>
                            <li>
                               <label for="title">title</label>
                               <input type="input" id="title" name="title" />
                            </li>
                            <li>
                               <label for="usercontent">objective</label>
                               <textarea id="usercontent" name="content"></textarea>
                            </li>
                            <li>
                               <label for="tags">tags</label>
                               <input type="input" id="tags" name="tags" />
                            </li>
                            <li>
                               <input type="submit" value="submit" />
                            </li>
                        </ul>
                </fieldset>
            </form>
        </div>

        <script type="text/javascript">
            var config = {
                language: '${lang}',
                filebrowserImageUploadUrl : '/u2/plan/images',
                toolbar: [
                   { 
                       name: 'tools', 
                       items: ['Source', '-', 'Bold', 'Italic']
                   },
                   {   
                       name: 'insert',
                       items: ['Image']
                   }
                ]
            };

            $(document).ready(function() {
                $('#usercontent').ckeditor(config);
            });

        </script>
    </div>

</@tissue.layout>
