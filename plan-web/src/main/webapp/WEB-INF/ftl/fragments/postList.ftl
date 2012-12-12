<#if posts??>
<ul>
   <#list posts as post>
       <li>
           <span><a href="/u2/plan/posts/${post.id}">${post.title}</a></span>
           <span><a href="/u1/profile/users/${post.user.id}">${post.user.displayName}</a></span>
       </li>
   </#list>
</ul>
</#if>

<#--
<script type="text/javascript">
   $(document).ready(function() {
       $('#content li a.post').on('click', function(e) {
           $('#content').load(this.href);
           return false;
       });
   });
</script>
-->


