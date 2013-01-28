<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "utilGadgets.ftl" as utilGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/explore.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@exploreGadgets.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <@userGadgets.showUsers />
        </div>

        <div id="content">
            <div id="contentInner">
                <@topicGadgets.showTopics />
                <@utilGadgets.showPager />
            </div>
        </div>
   </div>

</@tissue.layout>
