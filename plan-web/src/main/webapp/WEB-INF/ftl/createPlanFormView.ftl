<#import "spring.ftl" as spring />
<#import "siteGadgets.ftl" as site />

<#assign myscripts=["/ckeditor/ckeditor.js"] in site>

<#assign title="explore" in site>

<@site.layout>
    <#include "topicHeader.ftl" />

    <div id="page-main-wrapper">
        <form id="planForm" action="<@spring.url '/topics/${topic.id?replace("#", "")}/plans/_create' />" method="post">
            <legend>
                <@spring.message 'planForm' />
            </legend>

            <ul>
                <li>
                    <label>
                        <input type="radio" name="duration" checked value="1" />
                        <@spring.message 'duration1' />
                    </label>
                </li>
                <li>
                    <label>
                        <input type="radio" name="duration" value="3" />
                        <@spring.message 'duration2' />
                    </label>
                </li>
                <li>
                    <label>
                        <input type="radio" name="duration" value="6" />
                        <@spring.message 'duration3' />
                    </label>
                </li>
                <li>
                    <input type="submit" value="<@spring.message 'Submit.button'/>" />
                </li>
            </ul>
        </form>
    </div>
</@site.layout>
