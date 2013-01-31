<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "postGadgets.ftl" as postGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/layout3.css"] in tissue>

<@tissue.layout "Trending">
    <div id="logo">
        <@exploreGadgets.exploreLogo "featured" />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showUsers />
        </div>

        <div id="mid">
            <@postGadgets.showPosts newPosts />
        </div>

        <div id="content">
            <@topicGadgets.showTopics />
        </div>
    </div>
</@tissue.layout>
