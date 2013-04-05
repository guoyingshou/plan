<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title="explore" in site>

<@site.layout>

    <#include "topicHeader.ftl" />

    <div id="page-main-wrapper">
        <@spring.bind "topicForm.*" />
      
        <form id="topicForm" method="post" action="<@spring.url '/topics/${topicForm.id?replace("#","")}/_update' />">
            <div class="error">
                <@spring.showErrors "<br>" />
            </div>
            <legend>
                <@spring.message "topicForm" />
            </legend>
            <ul>
                <li>
                    <@spring.formHiddenInput "topicForm.id" />
                </li>
                <li>
                    <label for="title">
                        <@spring.message "title" />
                    </label>
                    <@spring.formInput "topicForm.title"/>
                </li>
                <li>
                    <label for="content">
                        <@spring.message "content" />
                    </label>
                   <@spring.formTextarea "topicForm.content" />
                </li>
                <li>
                    <label for="tags">
                        <@spring.message "tags" />
                     </label>
                    <@spring.formInput "topicForm.tags" />
                </li>

                <li>
                    <input type="submit" value="<@spring.message 'Submit.button'/>" />
                </li>
            </ul>
        </form>
        <script type="text/javascript">
            CKEDITOR.replace('content', {
                filebrowserUploadUrl: '/media/images/_create',
                filebrowserBrowseUrl: '/media/browseImages'
            });
        </script>
   </div>
</@site.layout>
