<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title=topic.title in site>

<@site.layout>
    <@topicGadgets.topicHeader />

    <div id="page-main-wrapper">
        <div id="page-main">
           <div id="main-content">
               <@spring.bind "questionForm.*" />
               <form method="post" action="<@spring.url '/topics/${topic.id?replace("#", "")}/questions/_create'/>">
              <div class="error">
                  <@spring.showErrors "<br>" />
              </div>
              <fieldset>
                  <legend>
                      <@spring.message "question" />
                  </legend>

                  <ul>
                      <li>
                          <label for="title">
                              <@spring.message "title" />
                          </label>
                          <@spring.formInput "questionForm.title" />
                      </li>
                      <li>
                          <label for="content">
                             <@spring.message "content" />
                          </label>
                          <@spring.formTextarea "questionForm.content" />
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
               CKEDITOR.replace("content");
           </script>
           </div>

            <div id="main-sidebar">
                <@topicGadgets.showPlansArchived/>
            </div>
       </div>
    </div>
</@site.layout>
