<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>State ZipCode Tag Cloud</title>
<g:javascript src="jquery.tagcloud.js" />
<script>
	$.fn.tagcloud.defaults = {
		size : {
			start : 8,
			end : 33,
			unit : 'pt'
		},
		color : {
			start : '#cde',
			end : '#f52'
		}
	};

	$(function() {
		$('#stateziptagcloud a').tagcloud();
	});
</script>

</head>
<body>
	State Tag Cloud

	<div id="stateziptagcloud">
		<g:each in="${stateList}" var="state">
			<g:link controller="state" action="show" id="${state.id}" rel="${state.zipCodes.size()}">
				${state.displayName}
			</g:link>

		</g:each>
	</div>
</body>
</html>