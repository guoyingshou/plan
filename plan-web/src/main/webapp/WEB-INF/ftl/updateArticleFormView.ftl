<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title=articleForm.title in site>

<@site.layout>
    <@topicGadgets.topicHeader />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <@spring.bind "articleForm.*" />
               <form method="post" action="<@spring.url '/articles/${articleForm.id?replace("#","")}/_update'/>">
               <div class="error">
                   <@spring.showErrors "<br>" />
               </div>

               <fieldset>
                   <legend>
                       <@spring.message "articleForm" />
                   </legend>

                   <ul>
                       <li>
                           <@spring.formHiddenInput "articleForm.id" />
                       </li>
                       <li>
                           <label for="title">
                               <@spring.message "title" />
                           </label>
                           <@spring.formInput "articleForm.title" />
                       </li>
                       <li>
                           <label for="content">
                               <@spring.message "content" />
                           </label>
                           <@spring.formTextarea "articleForm.content" />
                       </li>
                       <li>
                           <@spring.formHiddenInput "articleForm.type" />
                       </li>
                       <li>
                           <input type="submit" value="<@spring.message 'Publish.button'/>" />
                       </li>
                   </ul>
               </fieldset>
              </form>
              <script type="text/javascript">
                CKEDITOR.replace("content", {
                    filebrowserUploadUrl: '/media/images/_create' 
                 });
              </script>
           </div>

            <div id="main-sidebar">
                <@topicGadgets.showPlanLive />
                <@topicGadgets.showPlansArchived/>
            </div>


        </div>
    </div>
</@site.layout>
