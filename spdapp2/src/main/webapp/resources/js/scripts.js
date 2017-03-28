/* Find any element which has a 'data-onload' function and load that to simulate an onload. */
$('[data-onload]').each(function() {
	eval($(this).data('onload'));
});

$( function() {
	$("#date").datepicker({
		format: "dd-mm-yyyy",
		weekStart: 1,
		clearBtn: true,
		calendarWeeks: true,
		autoclose: true,
		todayHighlight: true
	});
});

function calcSpecificationSum() {
	var configuringTarif = +document.getElementById("configuringHoursTarif").value;
	var programmingTarif = +document.getElementById("programmingHoursTarif").value;
	var architectingTarif = +document.getElementById("architectingHoursTarif").value;
	var configuring = +document.getElementById("configuringHours").value;
	var programming = +document.getElementById("programmingHours").value;
	var architecting = +document.getElementById("architectingHours").value;
	var hours = parseInt(configuring + programming + architecting);
	var days = +hours / 8;
	var specSum = (configuring * configuringTarif)
			+ (programming * programmingTarif)
			+ (architecting * architectingTarif);
	var finalLabel = document.getElementById('sum');
	finalLabel.innerHTML = '<b>' + "Итого: " + specSum.toFixed(2) + " грн | " + hours + " час. | " + days.toFixed(0) + " дн." + '</b>';
	document.getElementById("sum").bold();
}

function goToAddress(url) {
	window.location.href = url;
}
