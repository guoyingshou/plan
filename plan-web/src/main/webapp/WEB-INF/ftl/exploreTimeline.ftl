<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "activityGadgets.ftl" as activityGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in tissue>
<#assign mystyles=["/tissue/css/explore.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@exploreGadgets.exploreLogo />
    </div>


    <div id="contentWrapper">
        <div id="sidebar">

        </div>

        <div id="content">
            <div id="contentInner">
                <@activityGadgets.showActivities />
            </div>
        </div>
    </div>

</@tissue.layout>
