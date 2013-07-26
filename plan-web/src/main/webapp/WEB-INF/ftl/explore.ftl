<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "siteGadgets.ftl" as site />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title="explore" in site>

<@site.layout>

    <@topicGadgets.exploreHeader />

    <div id="page-main-wrapper">
        <div id="page-main">
            <div id="main-content">
                <div id="content-topics">
                    <@topicGadgets.showTopics />
                </div>

                <div id="content-posts">
                    <@topicGadgets.showPosts />
                </div>
            </div>

            <div id="main-sidebar">
            <#--
                <@userGadgets.showUsers />
                -->
            </div>

        </div>
   </div>
</@site.layout>
