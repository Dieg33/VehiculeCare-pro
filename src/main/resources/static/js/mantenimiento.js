document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('mantenimientoModal');
    const form = document.getElementById('mantenimientoForm');
    const titulo = document.getElementById('modalTitle');

    // Botón "+ Nuevo"
    document.getElementById('nuevoBtn').addEventListener('click', function() {
        titulo.textContent = 'Nuevo mantenimiento';
        form.reset();
        modal.style.display = 'block';
    });

    // Botones "Editar"
    document.querySelectorAll('.editarBtn').forEach(btn => {
        btn.addEventListener('click', function() {
            const id = this.getAttribute('data-id');
            const vehiculo = this.getAttribute('data-vehiculo');
            const fecha = this.getAttribute('data-fecha');
            const tipo = this.getAttribute('data-tipo');
            const estado = this.getAttribute('data-estado');

            titulo.textContent = 'Editar mantenimiento';
            form.querySelector('[name="id"]').value = id;
            form.querySelector('[name="vehiculo"]').value = vehiculo;
            form.querySelector('[name="fechaIngreso"]').value = fecha;
            form.querySelector('[name="tipoMantenimiento"]').value = tipo;
            form.querySelector('[name="estado"]').value = estado;

            modal.style.display = 'block';
        });
    });

    // Botón "Cancelar"
    document.getElementById('cancelarBtn').addEventListener('click', function() {
        modal.style.display = 'none';
    });

    // Cerrar modal al hacer clic fuera
    window.addEventListener('click', function(event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
});


