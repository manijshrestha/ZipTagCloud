<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Tag Cloud Test jq</title>
	<g:javascript src="jquery.tagcloud.js" />
	<script>
	$.fn.tagcloud.defaults = {
			  size: {start: 8, end: 33, unit: 'pt'},
			  color: {start: '#cde', end: '#f52'}
			};

			$(function () {
			  $('#whatever a').tagcloud();
			});
	</script>
</head>
<body>
	Testing Tag Cloud
	<div id="whatever">
		<a href="/path" rel="7">peace</a>
		<a href="/path" rel="3">unity</a>
		<a href="/path" rel="10">love</a>
		<a href="/path" rel="5">having fun</a>
		<a href="/path" rel="7">peace</a>
		<a href="/path" rel="3">unity</a>
		<a href="/path" rel="10">love</a>
		<a href="/path" rel="5">having fun</a>
		<a href="/path" rel="7">peace</a>
		<a href="/path" rel="3">unity</a>
		<a href="/path" rel="10">love</a>
		<a href="/path" rel="5">having fun</a>
		<a href="/path" rel="7">peace</a>
		<a href="/path" rel="3">unity</a>
		<a href="/path" rel="10">love</a>
		<a href="/path" rel="5">having fun</a>
		<a href="/path" rel="7">peace</a>
		<a href="/path" rel="3">unity</a>
		<a href="/path" rel="10">love</a>
		<a href="/path" rel="5">having fun</a>
		<a href="/path" rel="7">peace</a>
		<a href="/path" rel="3">unity</a>
		<a href="/path" rel="10">love</a>
		<a href="/path" rel="5">having fun</a>
	</div>
</body>
</html>