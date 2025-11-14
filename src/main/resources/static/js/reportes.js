function validarFechas() {
    var inicio = document.getElementById("fechaInicio").value;
    var fin = document.getElementById("fechaFin").value;
    if (inicio && fin && inicio > fin) {
        alert("La fecha de inicio no puede ser mayor que la fecha de fin.");
        return false;
    }
    return true;
}
