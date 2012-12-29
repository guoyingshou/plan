<#import "spring.ftl" as spring />
<#import "tissue.ftl" as tissue />
<#import "exploreGadgets.ftl" as exploreGadgets />
<#import "eventGadgets.ftl" as eventGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>
<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/exploreTimeline.css", "/tissue/css/pop.css"] in tissue>


<@tissue.layout "topics">
    <div id="logo">
        <@exploreGadgets.exploreLogo />
    </div>


    <div id="contentWrapper">
        <div id="sidebar">

        </div>

        <div id="content">
            <div id="contentInner">
                <@eventGadgets.showLatestEvents />
            </div>
        </div>
    </div>

</@tissue.layout>
