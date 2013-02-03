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
		$('#clear-btn').bind('click', clearBtnClick);
		$('#load-btn').bind('click', loadBtnClick);
	});

	
	function clearBtnClick() {
		window.location.href='clear?stateCode=' + getSelectedState();
	}

	function loadBtnClick() {
		window.location.href='load?stateCode=' + getSelectedState();
	}

	function getSelectedState() {
		return $('#selected-state').val();
	}
</script>

</head>
<body>
	<div id="stateziptagcloud">
		<g:each in="${stateList}" var="state">
			<g:link controller="state" action="show" id="${state.id}" rel="${state.zipCodes.size()}" title="${state.zipCodes.size()}">
				${state.displayName}
			</g:link>

		</g:each>
	</div>
	
	<div id="clear-btns">
		<input id="selected-state" type="text" value="ALL"/>
		<button id="clear-btn">Clear ZipCodes</button>
		<button id="load-btn">Load ZipCodes</button>
	</div>
</body>
</html>