document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("modalMantenimiento");
    const cerrarModal = document.getElementById("cerrarModal");
    const btnNuevo = document.getElementById("btnNuevo");
    const form = document.getElementById("formMantenimiento");

    let editId = null;

    // 🟢 Abrir modal para nuevo mantenimiento
    btnNuevo.addEventListener("click", () => {
        editId = null;
        form.reset();
        document.getElementById("modalTitulo").textContent = "Nuevo Mantenimiento";
        modal.style.display = "flex";
    });

    // 🔴 Cerrar modal
    cerrarModal.addEventListener("click", () => {
        modal.style.display = "none";
    });

    // 🔘 Cerrar modal al hacer clic fuera
    window.addEventListener("click", (e) => {
        if (e.target === modal) modal.style.display = "none";
    });

    // ✏️ Editar mantenimiento (abrir modal con datos)
    document.querySelectorAll(".btn-edit").forEach(btn => {
        btn.addEventListener("click", async (e) => {
            e.preventDefault();
            editId = btn.getAttribute("data-id");

            const res = await fetch(`/mantenimientos/api/${editId}`);
            if (!res.ok) {
                alert("Error al obtener datos del mantenimiento");
                return;
            }

            const data = await res.json();

            document.getElementById("vehiculo").value = data.vehiculo;
            document.getElementById("tipo").value = data.tipoMantenimiento;
            document.getElementById("fecha").value = data.fecha;
            document.getElementById("estado").value = data.estado;

            document.getElementById("modalTitulo").textContent = "Editar Mantenimiento";
            modal.style.display = "flex";
        });
    });

    // 💾 Guardar o actualizar mantenimiento
    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        const formData = new FormData(form);
        const mantenimiento = Object.fromEntries(formData.entries());

        if (editId) mantenimiento.id = editId;

        const res = await fetch("/mantenimientos/guardar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(mantenimiento)
        });

        if (res.ok) {
            modal.style.display = "none";
            window.location.reload();
        } else {
            alert("Error al guardar mantenimiento");
        }
    });
});

