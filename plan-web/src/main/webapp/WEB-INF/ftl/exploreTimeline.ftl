<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/exploreTimeline.css"] in tissue>

<@tissue.layout "topics">
    <div id="logo">
        <@tissue.exploreLogo />
    </div>


    <div id="contentWrapper">
        <div id="sidebar">

        </div>


        <div id="content">
            <@gadgets.showLatestEvents />
        </div>

    </div>

</@tissue.layout>
