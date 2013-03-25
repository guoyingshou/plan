<#import "spring.ftl" as spring />
<#import "userGadgets.ftl" as userGadgets />
<#import "topicGadgets.ftl" as topicGadgets />
<#import "commonGadgets.ftl" as commonGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js", "/tissue/js/topic.js"] in commonGadgets>
<#assign mystyles=["/tissue/css/layout3.css"] in commonGadgets>

<#assign title="explore" in commonGadgets>

<@commonGadgets.layout>
    <div id="page-logo-wrapper">
        <div id="page-logo">
            <@topicGadgets.topicLogo />
        </div>
    </div>

    <div id="page-menu-wrapper">
        <div id="page-menu">
            <@topicGadgets.topicMenu />
        </div>
    </div>

    <div id="page-main-wrapper">
        <form id="createPlanForm" action="<@spring.url '/topics/${topic.id?replace("#", "")}/plans/_create' />" method="post">
            <legend>
                <@spring.message 'Legend.planForm' />
            </legend>

            <ul>
                <li>
                    <label>
                        <input type="radio" name="duration" checked value="1" />
                        <@spring.message 'Label.planForm.duration1' />
                    </label>
                </li>
                <li>
                    <label>
                        <input type="radio" name="duration" value="3" />
                        <@spring.message 'Label.planForm.duration2' />
                    </label>
                </li>
                <li>
                    <label>
                        <input type="radio" name="duration" value="6" />
                        <@spring.message 'Label.planForm.duration3' />
                    </label>
                </li>
                <li>
                    <input type="submit" value="<@spring.message 'Submit.button'/>" />
                </li>
            </ul>
        </form>
    </div>
</@commonGadgets.layout>
