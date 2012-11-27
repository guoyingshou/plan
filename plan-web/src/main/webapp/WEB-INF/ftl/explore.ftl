<#import "tissue.ftl" as tissue />
<#import "spring.ftl" as spring />

<#assign mystyles=["http://www.tissue.com/resources/css/content-2cols.css", "http://www.tissue.com/resources/css/explore.css"] in tissue>

<@tissue.layout "explore">
    <div id="logo">
        <@tissue.exploreLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">

        </div>

        <div id="content" class="trend">
            <div>
                <h2>Trending topics</h2>
                <ul>
                    <li>
                        test
                    </li>
                    <li>
                        topic2
                    </li>
                </ul>
            </div>
            <div class="feature">
                <h2>Featured topics</h2>
                <ul>
                    <li>
                       topic 1
                    </li>
                    <li>
                       topic 2
                    </li>
                </ul>
            </div>
        </div>
    </div>
</@tissue.layout>
