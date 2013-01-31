<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/layout2.css", "/tissue/css/topic.css"] in tissue>

<@tissue.layout "topic">

    <div id="logo">
        <@topicGadgets.topicLogo current/>
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@topicGadgets.showLivePlan />
            <@topicGadgets.showArchivedPlans />
        </div>

       <div id="content">
           <div class="ts">
               <span>
                   ${topic.user.displayName}
               </span>
               <span>
                   ${topic.createTime?datetime}
               </span>
           </div>

           <div class="tags">
               <#list topic.tags as tag>${tag}&nbsp;</#list>
           </div>

           <div class="content">
               ${topic.content}
           </div>

           <#if viewer?? && topic.isOwner(viewer.id) >
           <a class="topic-edit" data-action="<@spring.url '/topics/${topic.id}' />" href="#">edit</a>
           <script type="text/javascript">
               $(document).on('click', 'a.topic-edit', function(e) {
                   e.preventDefault();
                   $(this).closest('div#content').editTopicDialog($(this).data("action"));
               });
           </script>
           </#if>
       </div>
    </div>

    <#if viewer?? && topic.isOwner(viewer.id)>
    <@formGadgets.topicEditForm />
    </#if>

</@tissue.layout>
