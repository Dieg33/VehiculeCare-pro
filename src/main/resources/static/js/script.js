document.addEventListener("DOMContentLoaded", function () {

    // -----------------------------
    // REFERENCIAS BÁSICAS
    // -----------------------------
    const modal = document.getElementById("vehiculoModal");
    const modalTitle = document.getElementById("vehiculoModalTitle");
    const btnNuevo = document.getElementById("btnNuevoVehiculo");
    const btnCerrar = document.getElementById("vehiculoModalClose");
    const btnCancelar = document.getElementById("vehiculoModalCancel");
    const form = document.getElementById("vehiculoForm");

    // Si no estamos en la vista de vehículos, salimos silenciosamente
    if (!modal || !form) {
        return;
    }

    // Función para abrir/cerrar modal
    function abrirModal() {
        modal.style.display = "flex";
    }

    function cerrarModal() {
        modal.style.display = "none";
    }

    // -----------------------------
    // NUEVO VEHÍCULO
    // -----------------------------
    if (btnNuevo) {
        btnNuevo.addEventListener("click", () => {
            modalTitle.textContent = "Nuevo vehículo";
            form.reset();
            const idInput = document.getElementById("vehiculoId");
            if (idInput) idInput.value = "";
            abrirModal();
        });
    }

    if (btnCerrar) btnCerrar.addEventListener("click", cerrarModal);
    if (btnCancelar) btnCancelar.addEventListener("click", cerrarModal);

    window.addEventListener("click", (event) => {
        if (event.target === modal) {
            cerrarModal();
        }
    });

    // -----------------------------
    // EDITAR VEHÍCULO (DELEGACIÓN)
    // -----------------------------

    // Usamos delegación de eventos por si en el futuro las filas cambian
    document.addEventListener("click", function (e) {

        // ¿El click fue en un botón .btn-edit-vehiculo o dentro de él?
        const btn = e.target.closest(".btn-edit-vehiculo");
        if (!btn) return; // si no, ignoramos

        // Fila <tr> que contiene al botón
        const row = btn.closest("tr");
        if (!row) return;

        // Leer atributos data-*
        const id = row.dataset.id || "";
        const placa = row.dataset.placa || "";
        const marca = row.dataset.marca || "";
        const modelo = row.dataset.modelo || "";
        const anio = row.dataset.anio || "";
        const tipo = row.dataset.tipo || "";
        const estado = row.dataset.estado || "ACTIVO";

        // Rellenar formulario
        const idInput = document.getElementById("vehiculoId");
        const placaInput = document.getElementById("placa");
        const marcaInput = document.getElementById("marca");
        const modeloInput = document.getElementById("modelo");
        const anioInput = document.getElementById("anio");
        const tipoInput = document.getElementById("tipo");
        const estadoSelect = document.getElementById("estado");

        if (idInput) idInput.value = id;
        if (placaInput) placaInput.value = placa;
        if (marcaInput) marcaInput.value = marca;
        if (modeloInput) modeloInput.value = modelo;
        if (anioInput) anioInput.value = anio;
        if (tipoInput) tipoInput.value = tipo;
        if (estadoSelect) estadoSelect.value = estado;

        // Cambiar título
        modalTitle.textContent = "Editar vehículo";

        // Mostrar modal
        abrirModal();
    });

});
