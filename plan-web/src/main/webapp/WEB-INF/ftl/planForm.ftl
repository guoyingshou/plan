<#import "spring.ftl" as spring />

            <form action="<@spring.url '/plan/topics/${topic.id}/plans' />" method="post">
                <fieldset>
                    <legend>please select a duration</legend>
                    <ul>
                        <li>
                            <label><input type="radio" name="duration" value="1"/>1 Mon</label>
                        </li>
                        <li>
                            <label><input type="radio" name="duration" value="3"/>3 Mon</label>
                        </li>
                        <li>
                            <label><input type="radio" name="duration" value="6"/>6 Mon</label>
                        </li>
                    </ul>
                </fieldset>
                <input type="submit" value="submit" />
            </form>

<#--
    <script type="text/javascript">
            $(document).ready(function() {
                $('form').submit(function() {

                    $.post(this.action, $(this).serialize(), function(res, status) {
                        $('#content').html(res);
                    });
 
                    return false;
                });

            });
    </script>
    -->

