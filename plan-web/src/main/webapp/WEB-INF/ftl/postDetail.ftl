<#import "tissue.ftl" as tissue />
<#import "gadgets.ftl" as gadgets />
<#import "spring.ftl" as spring />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/pop.js"] in tissue>

<#assign mystyles=["/tissue/css/content-2cols.css", "/tissue/css/topic.css", "/tissue/css/plan.css", "/tissue/css/postForm.css", "/tissue/css/post.css", "/tissue/css/pop.css"] in tissue>

<@tissue.layout "post detail">
    <div id="logo">
        <#assign topic = post.plan.topic in tissue />
        <@tissue.topicLogo />
    </div>

    <div id="contentWrapper">
        <div id="sidebar">
            <#assign activePlan = topic.activePlan in tissue />
            <@tissue.showActivePlan />
        </div>
        <div id="content">
            <div id="contentInner">
               <@gadgets.showPostDetail />
            </div>
        </div>
    </div>

    <div id="dia" style="display: none">
        <form>
            <ul>
                <li>
                    <textarea id="editor" name="content"></textarea>
                </li>
                <li>
                    <input type="submit" value="submit"/>
                </li>
            </ul>
        </form>
        <div>
            <a href="#" class="cancel">cancel</a>
        </div>
    </div>

</@tissue.layout>


