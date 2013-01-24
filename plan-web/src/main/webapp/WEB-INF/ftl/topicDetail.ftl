<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "planGadgets.ftl" as planGadgets />
<#import "formGadgets.ftl" as formGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/topic.css", "/tissue/css/plan.css"] in tissue>

<@tissue.layout "topic">

    <div id="logo">
        <@topicGadgets.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

            <#if topic.activePlan??>
                <#assign activePlan = topic.activePlan in planGadgets />
            </#if>
            <@planGadgets.showActivePlan />

            <#--
            <@planGadgets.showDeadPlans />
            -->
        </div>

       <div id="content">

           <div class="topic-content">${topic.content}</div>
           <div class="topic-tags"><#list topic.tags as tag>${tag}&nbsp;</#list></div>
           <div>${topic.user.displayName} published: ${topic.createTime?datetime}</div>

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