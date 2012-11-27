<#import "tissue.ftl" as tissue />
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
                        <ul>
                            <li>
                                event 1
                            </li>
                            <li>
                                event 2
                            </li>
                            <li>
                                event 3
                            </li>
                            <li>
                                event 4
                            </li>
                            <li>
                                event 5
                            </li>
                        </ul>
                    </div>
        </div>

</@tissue.layout>
