<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign myscripts=["http://www.tissue.com/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/topicForm.css"] in tissue>

<@tissue.layout "topic">

    <div id="logo">
        <@tissue.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
        </div>

       <div id="content" class="topicEditForm">
            <form action="<@spring.url '/plan/topics/${topic.id}' />" method="post">
                <fieldset class="topic">
                    <legend>your topic</legend>
                        <ul>
                            <li>
                               <label for="usercontent">objective</label>
                               <textarea id="usercontent" name="content">${topic.content}</textarea>
                            </li>
                            <li>
                               <label for="tags">tags</label>
                               <input type="input" id="tags" name="tags" value="<#list topic.tags as tag>${tag}&nbsp;</#list>"/>
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
                CKEDITOR.replace('usercontent');
            });

        </script>
    </div>

</@tissue.layout>
