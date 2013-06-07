<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title=topic.title in site>

<@site.layout>

    <@topicGadgets.topicHeader/>

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <@spring.bind "articleForm.*" />
               <form method="post" action="<@spring.url '/topics/${topic.id?replace("#", "")}/articles/_create'/>">
               <div class="error">
                   <@spring.showErrors "<br>" />
               </div>

               <fieldset class="post-type">
                   <legend>
                       <@spring.message 'Legend.articleForm.type' />
                   </legend>

                   <label>
                      <@spring.message 'Label.concept' />
                      <input type="radio" name="type" value="concept" />
                   </label>
                   <label>
                       <@spring.message 'Label.note' />
                       <input type="radio" name="type" value="note" />
                   </label>
                   <label>
                       <@spring.message 'Label.tutorial' />
                       <input type="radio" name="type" value="tutorial" />
                   </label>
               </fieldset>

               <fieldset>
                   <legend>
                       <@spring.message "Legend.articleForm" />
                   </legend>

                   <ul>
                       <li>
                           <label for="title">
                               <@spring.message "Label.title" />
                           </label>
                           <@spring.formInput "articleForm.title" />
                       </li>
                       <li>
                           <label for="content">
                               <@spring.message "Label.content" />
                           </label>
                           <@spring.formTextarea "articleForm.content" />
                       </li>
                       <li>
                           <input type="submit" value="<@spring.message 'PublishText.submit'/>" />
                       </li>
                   </ul>
               </fieldset>
              </form>
              <script type="text/javascript">
                CKEDITOR.replace("content");
              </script>
            </div>

            <div id="main-sidebar">
                <@topicGadgets.showPlansArchived/>
            </div>

        </div>
    </div>
</@site.layout>
