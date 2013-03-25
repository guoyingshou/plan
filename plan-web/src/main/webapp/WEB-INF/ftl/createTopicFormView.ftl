<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout3.css"] in commonGadgets>

<#assign title="explore" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@topicGadgets.exploreLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@topicGadgets.exploreMenu />
        </div>
    </div>

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
                        <@spring.message "Label.topicForm.title" />
                    </label>
                    <@spring.formInput "topicForm.title" 'class="sum"'/>
                </li>
                <li>
                    <label for="content">
                        <@spring.message "Label.topicForm.objective" />
                    </label>
                   <@spring.formTextarea "topicForm.content" 'class="sum"' />
                </li>
                <li>
                    <label for="tags">
                        <@spring.message "Label.topicForm.tags" />
                     </label>
                    <@spring.formInput "topicForm.tags" 'class="sum"' />
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
</@commonGadgets.layout>
