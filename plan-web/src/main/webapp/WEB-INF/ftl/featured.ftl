<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "personGadgets.ftl" as personGadgets />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>

<#assign mystyles=["/tissue/css/explore.css"] in tissue>

<@tissue.layout "Trending">
    <div id="logo">
        <@exploreGadgets.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@personGadgets.showUsers />
        </div>

        <div id="content">
            <@topicGadgets.showTopics />
        </div>
    </div>
</@tissue.layout>
