<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>
<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/exploreTimeline.css", "/tissue/css/pop.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@tissue.exploreLogo />
    </div>


    <div id="contentWrapper">
        <div id="sidebar">

        </div>

        <div id="content">
            <div id="contentInner">
                <@gadgets.showLatestEvents />
            </div>
        </div>
    </div>

</@tissue.layout>
