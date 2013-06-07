<#import "spring.ftl" as spring />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "siteGadgets.ftl" as site />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title = "Create Topic"  in site>

<@site.layout>
    <@topicGadgets.exploreHeader />

    <div id="page-main-wrapper">
        <@spring.bind "topicForm.*" />
      
        <form id="topicForm" method="post" action="<@spring.url '/topics/_create' />">
            <div class="error">
                <@spring.showErrors "<br>" />
            </div>
            <legend>
                <@spring.message "Legend.topicForm" />
            </legend>
            <ul>
                <li>
                    <label for="title">
                        <@spring.message "Label.title" />
                    </label>
                    <@spring.formInput "topicForm.title" />
                </li>
                <li>
                    <label for="content">
                        <@spring.message "Label.objective" />
                    </label>
                   <@spring.formTextarea "topicForm.content" />
                </li>
                <li>
                    <label for="tags">
                        <@spring.message "Label.tags" />
                     </label>
                    <@spring.formInput "topicForm.tags" />
                </li>

                <li>
                    <input type="submit" value="<@spring.message 'Text.submit'/>" />
                </li>
            </ul>
        </form>
        <script type="text/javascript">
            CKEDITOR.replace('content');
        </script>
   </div>
</@site.layout>
