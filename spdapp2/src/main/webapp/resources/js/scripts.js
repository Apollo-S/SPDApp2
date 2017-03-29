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

function calcSpecificationSum(configuringTarif, programmingTarif, architectingTarif) {
	var finalLabel = document.getElementById('sum');
	var configuringHours = +document.getElementById('configuringHours').value;
	var programmingHours = +document.getElementById('programmingHours').value;
	var architectingHours = +document.getElementById('architectingHours').value;
	var hours = configuringHours + programmingHours + architectingHours;
	var days = +hours / 8;
	var specSum = (configuringHours * configuringTarif)
					+ (programmingHours * programmingTarif)
						+ (architectingHours * architectingTarif);
	finalLabel.innerHTML = '<b>' + "Итого: " + specSum.toFixed(2) + " грн | " + hours.toFixed(0) + " ч. | " + days.toFixed(0) + " дн." + '</b>';
}

function goToAddress(url) {
	window.location.href = url;
}
