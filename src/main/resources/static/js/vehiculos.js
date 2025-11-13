document.addEventListener('DOMContentLoaded', function () {
    console.log('vehiculos.js cargado ✅');

    const modal = document.getElementById('vehiculoModal');
    const modalTitle = document.getElementById('vehiculoModalTitle');
    const btnNuevo = document.getElementById('btnNuevoVehiculo');
    const btnClose = document.getElementById('vehiculoModalClose');
    const btnCancel = document.getElementById('vehiculoModalCancel');
    const form = document.getElementById('vehiculoForm');

    const inputId = document.getElementById('vehiculoId');
    const inputPlaca = document.getElementById('placa');
    const inputMarca = document.getElementById('marca');
    const inputModelo = document.getElementById('modelo');
    const inputAnio = document.getElementById('anio');
    const inputTipo = document.getElementById('tipo');
    const selectEstado = document.getElementById('estado');

    function abrirModal() {
        if (modal) {
            modal.style.display = 'flex'; // ajusta según tu CSS
        }
    }

    function cerrarModal() {
        if (modal) {
            modal.style.display = 'none';
        }
    }

    // NUEVO VEHÍCULO
    if (btnNuevo) {
        btnNuevo.addEventListener('click', function () {
            if (form) form.reset();
            if (inputId) inputId.value = '';
            if (modalTitle) modalTitle.textContent = 'Nuevo vehículo';
            abrirModal();
        });
    }

    // CERRAR MODAL (X y botón cancelar)
    if (btnClose) {
        btnClose.addEventListener('click', cerrarModal);
    }

    if (btnCancel) {
        btnCancel.addEventListener('click', function (e) {
            e.preventDefault();
            cerrarModal();
        });
    }

    // Cerrar haciendo clic fuera del contenido
    if (modal) {
        window.addEventListener('click', function (e) {
            if (e.target === modal) {
                cerrarModal();
            }
        });
    }

    // 🧠 DELEGACIÓN: cualquier click en el documento que venga de un botón .btn-edit-vehiculo
    document.addEventListener('click', function (e) {
        const btn = e.target.closest('.btn-edit-vehiculo');
        if (!btn) {
            return;
        }

        const fila = btn.closest('tr');
        if (!fila) return;

        const id = fila.dataset.id || '';
        const placa = fila.dataset.placa || '';
        const marca = fila.dataset.marca || '';
        const modelo = fila.dataset.modelo || '';
        const anio = fila.dataset.anio || '';
        const tipo = fila.dataset.tipo || '';
        const estado = fila.dataset.estado || '';

        console.log('Editando vehículo:', {
            id, placa, marca, modelo, anio, tipo, estado
        });

        if (inputId) inputId.value = id;
        if (inputPlaca) inputPlaca.value = placa;
        if (inputMarca) inputMarca.value = marca;
        if (inputModelo) inputModelo.value = modelo;
        if (inputAnio) inputAnio.value = anio;
        if (inputTipo) inputTipo.value = tipo;
        if (selectEstado) selectEstado.value = estado || 'ACTIVO';

        if (modalTitle) modalTitle.textContent = 'Editar vehículo';

        abrirModal();
    });

});
