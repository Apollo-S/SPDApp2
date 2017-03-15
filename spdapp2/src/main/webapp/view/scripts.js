/* Find any element which has a 'data-onload' function and load that to simulate an onload. */
$('[data-onload]').each(function() {
	eval($(this).data('onload'));
});

function calcSpecificationSum() {
	
	var configuringTarif = +document.getElementById("configuringHoursTarif").innerText;
	var programmingTarif = +document.getElementById("programmingHoursTarif").innerText;
	var architectingTarif = +document.getElementById("architectingHoursTarif").innerText;
	var configuring = +document.getElementById("configuringHours").value;
	var programming = +document.getElementById("programmingHours").value;
	var architecting = +document.getElementById("architectingHours").value;

	var hours = parseInt(configuring + programming + architecting);
	var days = +hours / 8;
	var specSum = (configuring * configuringTarif)
			+ (programming * programmingTarif)
			+ (architecting * architectingTarif);
			
	var finalLabel;
//	specSum.toFixed(2) + ' грн | ' + hours + "час. | " + days.toString() + " дн."
	document.getElementById("sum").value = "Итого: " + specSum.toFixed(2) + " грн | " + hours + " час. | " + days.toFixed(0) + " дн.";

}