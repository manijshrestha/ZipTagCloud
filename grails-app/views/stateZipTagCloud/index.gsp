<!DOCTYPE html>
<html>
<head>
<meta name ="layout" content="main" />
<title>Tag Cloud Test jq</title>
<g:javascript src="jquery.tagcloud.js" />
<script>
$.fn.tagcloud.defaults = {
  size: {start: 8, end: 33, unit: 'pt'},
  color: {start: '#cde', end: '#f52'}
};

$(function () {
  $('#stateziptagcloud a').tagcloud();
});
</script>
</head>
<body>
Testing Tag Cloud
<div id="stateziptagcloud">
<a href="/path" rel="7">peace</a>
<g:each in="${stateList}" var="state">
   <a href="/path" rel="${state.zipCodes.size()}">${state.displayName}</a>
</g:each>
</div>
</body>
</html>