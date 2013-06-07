<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>
<#assign title=questionForm.title in site>

<@site.layout>
    <@topicGadgets.topicHeader />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <@spring.bind "questionForm.*" />
               <form method="post" action="<@spring.url '/questions/${questionForm.id?replace("#","")}/_update'/>">
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
                              <@spring.message "Label.title" />
                          </label>
                          <@spring.formInput "questionForm.title" />
                      </li>
                      <li>
                          <label for="content">
                             <@spring.message "Label.formInut.content" />
                          </label>
                          <@spring.formTextarea "questionForm.content" />
                      </li>
                      <li>
                          <input type="hidden" name="type" value="question" />
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
