<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />
<#import "topicGadgets.ftl" as topicGadgets />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title="explore" in site>

<@site.layout>
    <@topicGadgets.topicHeader/>

    <div id="page-main-wrapper">
        <form id="planForm" action="<@spring.url '/topics/${topic.id?replace("#", "")}/plans/_create' />" method="post">
            <legend>
                <@spring.message 'Legend.planForm' />
            </legend>

            <ul>
                <li>
                    <label>
                        <input type="radio" name="duration" checked value="1" />
                        <@spring.message 'Label.formInput.duration1' />
                    </label>
                </li>
                <li>
                    <label>
                        <input type="radio" name="duration" value="3" />
                        <@spring.message 'Label.formInput.duration2' />
                    </label>
                </li>
                <li>
                    <label>
                        <input type="radio" name="duration" value="6" />
                        <@spring.message 'Label.formInput.duration3' />
                    </label>
                </li>
                <li>
                    <input type="submit" value="<@spring.message 'Text.formInput.submit'/>" />
                </li>
            </ul>
        </form>
    </div>
</@site.layout>
