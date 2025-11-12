document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("mantenimientoModal");
    const cerrarModal = document.getElementById("cerrarModal");
    const cancelarBtn = document.getElementById("cancelarBtn");
    const nuevoBtn = document.getElementById("nuevoBtn");
    const modalTitle = document.getElementById("modalTitle");
    const form = document.getElementById("mantenimientoForm");

    // === ABRIR MODAL PARA NUEVO ===
    nuevoBtn.addEventListener("click", () => {
        modalTitle.textContent = "Nuevo mantenimiento";
        form.reset();
        form.action = "/mantenimientos/guardar";
        modal.style.display = "block";
    });

   // Abrir modal para Editar
   document.querySelectorAll(".editarBtn").forEach(btn => {
       btn.addEventListener("click", e => {
           e.preventDefault();

           modalTitle.textContent = "Editar mantenimiento";

           document.getElementById("id").value = btn.dataset.id;
           document.getElementById("vehiculo").value = btn.dataset.vehiculo;
           document.getElementById("fechaIngreso").value = btn.dataset.fechaingreso;
           document.getElementById("tipoMantenimiento").value = btn.dataset.tipomantenimiento;
           document.getElementById("estado").value = btn.dataset.estado;

           // 🔹 Usa la misma ruta para guardar/actualizar
           form.action = "/mantenimientos/guardar";

           modal.style.display = "block";
       });
   });

    // === CERRAR MODAL ===
    [cerrarModal, cancelarBtn].forEach(btn => {
        btn.addEventListener("click", () => {
            modal.style.display = "none";
        });
    });

    // === CERRAR SI SE HACE CLIC FUERA ===
    window.addEventListener("click", e => {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });
});

