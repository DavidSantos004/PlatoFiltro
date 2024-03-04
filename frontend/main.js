document.addEventListener("DOMContentLoaded", async () => {
  const apiUrl = "http://localhost:8080/api/platos";
  const searchButton = document.getElementById('searchButton');
  const searchInput = document.getElementById('searchInput');
  const nftContainer = document.querySelector('.nft-container');
  const platoForm = document.getElementById('platoForm');

  // Fetch countries data
  let PlatosData = [];
  try {
    const response = await fetch(apiUrl);
    PlatosData = await response.json();
  } catch (error) {
    console.error("Error fetching countries data:", error);
    return;
  }

  displayPlatos(PlatosData);

  searchButton.addEventListener('click', (event) => {
    event.preventDefault();
    const searchTerm = searchInput.value.trim().toLowerCase();
    filterPlatos(searchTerm);
  });

  function filterPlatos(searchTerm) {
    const filteredPlatos = PlatosData.filter(plato => {
      const platoName = plato.nombreplato.toLowerCase();

      return Object.values(plato).some(value =>
        typeof value === 'string' && value.toLowerCase().includes(searchTerm)
      );
    });

    displayPlatos(filteredPlatos);
  }

  function displayPlatos(platos) {
    nftContainer.innerHTML = "";

    platos.forEach((plato) => {
      console.log(plato)

      nftContainer.insertAdjacentHTML(
        "beforeend",
        /*html*/
        `<div class="nft">
    <div class='main'>
      <h2>${plato.nombreplato} </h2>
      <p class='description'>
        Descripcion: ${plato.descripcionplato}<br>
        Material: ${plato.materialplato}<br>
        Color: ${plato.colorplato}<br>
        Precio: ${plato.precioplato}<br>
      </p>
      <div class='tokenInfo'>
        <div class="price">
          <ins>◘</ins>
        </div>
      </div>
      <hr />
      <div class='creator'>
        <div class='wrapper'>
          <img src="https://avatars.githubusercontent.com/u/123655790?v=4" alt="Creator" />
        </div>
        <p><ins>Creation of</ins> <a href="https://github.com/DavidSantos004" target="_blank" style="color: white; text-decoration: none;">@DavidSantos004</a></p>
      </div>
      <button class="deleteButton" onclick="eliminarPlato('${plato.idplato}')">Eliminar</button>
      <button class="editButton" onclick="abrirFormularioEdicion('${plato.idplato}')">Editar</button>
      </div>
  </div>`
      );
    });

  }

  // Agrega la función eliminarPlato al ámbito global o al objeto window
  window.eliminarPlato = async function (platoId) {
    // Realiza una solicitud para eliminar el plato en el servidor
    try {
      const response = await fetch(`${apiUrl}/${platoId}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        // Filtra el plato eliminado de la lista y renderiza
        PlatosData = PlatosData.filter(plato => plato._id !== platoId);
        displayPlatos(PlatosData);
        location.reload();
      } else {
        console.error("Error al eliminar el plato");
      }
    } catch (error) {
      console.error("Error al eliminar el plato:", error);
    }
  };


  window.abrirFormularioEdicion = function (platoId) {
    console.log("ID del plato a editar:", platoId);
    cargarDatosPlatoEnFormulario(platoId);
    const editarForm = document.getElementById('editarForm');
    editarForm.style.display = 'flex';
    editarForm.style.position = 'relative';
    editarForm.style.justifyContent = 'center';
    editarForm.style.flexdirection = 'column';
    editarForm.style.margin = '5em';
  }
  
  // Agrega la función cargarDatosPlatoEnFormulario
  function cargarDatosPlatoEnFormulario(platoId) {
    const platoAEditar = PlatosData.find(plato => plato.idplato === parseInt(platoId));
    console.log(platoAEditar);
  
    if (platoAEditar) {
      console.log("Plato a editar:", platoAEditar);
      document.getElementById('nombrePlatoEditar').value = platoAEditar.nombreplato || '';
      document.getElementById('descripcionPlatoEditar').value = platoAEditar.descripcionplato || '';
      document.getElementById('materialPlatoEditar').value = platoAEditar.materialplato || '';
      document.getElementById('colorPlatoEditar').value = platoAEditar.colorplato || '';
      document.getElementById('precioPlatoEditar').value = platoAEditar.precioplato || '';
      
      // Guardar el ID del plato que se está editando
      document.getElementById('platoIdEditar').value = platoId;
    } else {
      console.error("Plato no encontrado para editar");
    }
  }
  // Agrega el evento para enviar el formulario de edición
  const editPlatoForm = document.getElementById('editPlatoForm');
  editPlatoForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    await editarPlato();
    // Aquí deberías cerrar el formulario de edición o realizar otras acciones necesarias
    editarForm.style.display = 'none';
  });
  
  // Agrega la función editarPlato
  async function editarPlato() {
    const platoId = document.getElementById('platoIdEditar').value;
  
    const platoEditado = {
      nombreplato: document.getElementById('nombrePlatoEditar').value,
      descripcionplato: document.getElementById('descripcionPlatoEditar').value,
      materialplato: document.getElementById('materialPlatoEditar').value,
      colorplato: document.getElementById('colorPlatoEditar').value,
      precioplato: document.getElementById('precioPlatoEditar').value,
    };
  
    try {
      const response = await fetch(`${apiUrl}/${platoId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(platoEditado),
      });
  
      if (response.ok) {
        PlatosData = PlatosData.map(plato =>
          plato.idplato === platoId ? { ...plato, ...platoEditado } : plato
        );
        displayPlatos(PlatosData);
        location.reload();
      } else {
        console.error("Error al editar el plato");
      }
    } catch (error) {
      console.error("Error al editar el plato:", error);
    }
  }
  
  

  platoForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    // Obtener valores del formulario
    const nuevoPlato = {
      nombreplato: document.getElementById('nombrePlato').value,
      descripcionplato: document.getElementById('descripcionPlato').value,
      materialplato: document.getElementById('materialPlato').value,
      colorplato: document.getElementById('colorPlato').value,
      precioplato: document.getElementById('precioPlato').value,

      // Agrega más propiedades según tus necesidades
    };

    // Guardar el nuevo plato en el servidor
    try {
      const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(nuevoPlato),
      });

      const platoGuardado = await response.json();

      // Agregar el nuevo plato a la lista y renderizar
      PlatosData.push(platoGuardado);
      displayPlatos(PlatosData);
    } catch (error) {
      console.error("Error al guardar el plato:", error);
    }
  });

});
