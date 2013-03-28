<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title="topic" in site>

<@site.layout>
    <#include "topicHeader.ftl" />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <@spring.bind "questionForm.*" />
               <form id="updateQuestionForm" method="post" action="<@spring.url '/questions/${questionForm.id?replace("#","")}/_update'/>">
              <div class="error">
                  <@spring.showErrors "<br>" />
              </div>
              <fieldset>
                  <legend>
                      <@spring.message "Legend.questionForm" />
                  </legend>

                  <ul>
                      <li>
                          <@spring.formHiddenInput "questionForm.id" />

                      </li>
                      <li>
                          <label for="title">
                              <@spring.message "Label.questionForm.title" />
                          </label>
                          <@spring.formInput "questionForm.title" 'class="sum"' />
                      </li>
                      <li>
                          <label for="content">
                             <@spring.message "Label.questionForm.content" />
                          </label>
                          <@spring.formTextarea "questionForm.content" 'class="sum"' />
                      </li>
                      <li>
                          <input type="hidden" name="type" value="question" />
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
                <@topicGadgets.showPlanLive/>
                <@topicGadgets.showPlansArchived/>
            </div>


       </div>
    </div>
</@site.layout>
