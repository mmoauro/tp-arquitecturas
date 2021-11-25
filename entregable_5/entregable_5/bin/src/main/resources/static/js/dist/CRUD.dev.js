"use strict";

document.addEventListener('DOMContentLoaded', cargarPagina);

function cargarPagina() {
  var lista = document.createElement("ul");
  lista.className = "lista-clientes";
  document.getElementById("formulario-cliente").appendChild(lista);
  getClientes();
  document.getElementById("post-cliente").addEventListener("click", function (e) {
    e.preventDefault();
    var nombre = document.getElementById("nombre-cliente").value;
    var apellido = document.getElementById("apellido").value;
    var dni = document.getElementById("dni").value; //OBJETO

    var cliente = {
      "nombre": nombre,
      "apellido": apellido,
      "dni": dni
    };

    if (cliente.nombre != "" && cliente.apellido != "" && cliente.dni != null) {
      //SI ESTAN VACIOS LOS CAMPOS NO SE ENVIA
      fetch('http://localhost:8080/clientes', {
        method: 'POST',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(cliente)
      }).then(function (response) {
        return response.json();
      }).then(function () {
        return getClientes();
      })["catch"](function (error) {
        return console.log(error);
      });
    }

    document.getElementById("formulario-cliente").reset(); //SE RESETEAN LOS CAMPOS DEL FORMULARIO
  });

  function getClientes() {
    var lista = document.querySelector(".lista-clientes");
    lista.innerHTML = ""; //SE VACIA EL DIV

    fetch('clientes').then(function (response) {
      return response.json();
    }).then(function (clientes) {
      renderClientes(clientes);
      renderSelectClientes(clientes);
    }) //SE LLAMA A LA FUNCION QUE LOS MUESTRA
    ["catch"](function (error) {
      return console.log(error);
    });
  }

  function renderClientes(clientes) {
    if (clientes == undefined) return;
    clientes.forEach(function (cliente) {
      lista.innerHTML += "<li class='cliente' id=cliente-" + cliente.id + ">" + cliente.nombre + " " + cliente.apellido + ": " + cliente.dni + "<button class='botonEditar' id=" + cliente.id + ">Editar</button><button class='botonEliminarCliente' id=" + cliente.id + ">Eliminar</button></li>";
      boton_editar_cliente(); //se le da funcionalidad

      boton_borrar_cliente(); //se le da funcionalidad
    });
  }

  function boton_borrar_cliente() {
    var buttons = document.getElementsByClassName('botonEliminarCliente');

    var _loop = function _loop(i) {
      buttons[i].addEventListener('click', function () {
        var id = buttons[i].id;
        borrarCliente_en_servidor(id);
        buttons[i].parentElement.remove();
      });
    };

    for (var i = 0; i < buttons.length; i++) {
      _loop(i);
    }
  }

  function borrarCliente_en_servidor(id) {
    fetch('clientes/' + id, {
      'method': 'DELETE',
      'mode': "cors"
    }).then(function (response) {
      return response.json();
    }).then(function () {
      return getClientes();
    })["catch"](function (error) {
      return console.log(error);
    });
  }

  function boton_editar_cliente() {
    var buttons = document.getElementsByClassName('botonEditar');

    var _loop2 = function _loop2(i) {
      buttons[i].addEventListener('click', function () {
        var id = buttons[i].id;
        createFormEditCliente(id);
      });
    };

    for (var i = 0; i < buttons.length; i++) {
      _loop2(i);
    }
  }

  function createFormEditCliente(id) {
    var cliente_id = "cliente-" + id;
    var li = document.getElementById(cliente_id);
    li.innerHTML = '<form id="formulario-cliente-edit"> <input type="text" placeholder="Nombre" id="nombre-editado"> <input type="text" placeholder="Apellido" id="apellido-editado"> <input type="number" placeholder="dni" id="dni-editado"> <button id="editar-' + id + '">Editar</button></form>';
    var btn_id = "#editar-" + id;
    var btn = document.querySelector(btn_id);
    btn.addEventListener("click", function () {
      editarCliente(id);
    });
  }

  function editarCliente(id) {
    var cliente = {
      "nombre": document.getElementById("nombre-editado").value,
      "apellido": document.getElementById("apellido-editado").value,
      "dni": document.getElementById("dni-editado").value,
      "id": id
    };
    fetch('clientes', {
      method: 'PUT',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(cliente)
    }).then(function (response) {
      return response.json();
    }).then(function () {
      return getClientes();
    })["catch"](function (error) {
      return console.log(error);
    });
  } //----------------------------------------------------------------------- PRODUCTOS ------------------------------------------------------------


  var listaProducto = document.createElement("ul");
  listaProducto.className = "lista-productos";
  document.getElementById("formulario-producto").appendChild(listaProducto);
  getProductos();
  document.getElementById("post-producto").addEventListener("click", function (e) {
    e.preventDefault();
    var nombre = document.getElementById("nombre-producto").value;
    var precio = document.getElementById("precio-producto").value; //OBJETO

    var producto = {
      "nombre": nombre,
      "precio": precio
    };
    console.log(producto);

    if (producto.nombre != "" && producto.precio != "" && producto.precio > 0) {
      //SI ESTAN VACIOS LOS CAMPOS NO SE ENVIA
      fetch('http://localhost:8080/productos', {
        method: 'POST',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(producto)
      }).then(function (response) {
        return response.json();
      }).then(function () {
        return getProductos();
      })["catch"](function (error) {
        return console.log(error);
      });
    }

    document.getElementById("formulario-producto").reset(); //SE RESETEAN LOS CAMPOS DEL FORMULARIO
  });

  function getProductos() {
    var listaProducto = document.querySelector(".lista-productos");
    listaProducto.innerHTML = ""; //SE VACIA EL DIV

    fetch('productos').then(function (response) {
      return response.json();
    }).then(function (productos) {
      renderProductos(productos);
      renderSelectProductos(productos);
    }) //SE LLAMA A LA FUNCION QUE LOS MUESTRA
    ["catch"](function (error) {
      return console.log(error);
    });
  }

  function renderProductos(productos) {
    if (productos == undefined) return;
    productos.forEach(function (producto) {
      listaProducto.innerHTML += "<li class='producto' id=producto-" + producto.id + ">" + producto.nombre + " " + producto.precio + "<button class='botonEditarProducto' id=" + producto.id + ">Editar</button><button class='botonEliminarProducto' id=" + producto.id + ">Eliminar</button></li>";
      boton_editar_producto(); //se le da funcionalidad

      boton_borrar_producto(); //se le da funcionalidad
    });
  }

  function boton_borrar_producto() {
    var buttons = document.getElementsByClassName('botonEliminarProducto');

    var _loop3 = function _loop3(i) {
      buttons[i].addEventListener('click', function () {
        var id = buttons[i].id;
        borrarproducto_en_servidor(id);
        buttons[i].parentElement.remove();
      });
    };

    for (var i = 0; i < buttons.length; i++) {
      _loop3(i);
    }
  }

  function borrarproducto_en_servidor(id) {
    fetch('productos/' + id, {
      'method': 'DELETE',
      'mode': "cors"
    }).then(function (response) {
      return response.json();
    }).then(function (get) {
      return renderProductos(getProductos);
    })["catch"](function (error) {
      return console.log(error);
    });
  }

  function boton_editar_producto() {
    var buttons = document.getElementsByClassName('botonEditarProducto');

    var _loop4 = function _loop4(i) {
      buttons[i].addEventListener('click', function () {
        var id = buttons[i].id;
        createFormEditProducto(id);
      });
    };

    for (var i = 0; i < buttons.length; i++) {
      _loop4(i);
    }
  }

  function createFormEditProducto(id) {
    var producto_id = "producto-" + id;
    var li = document.getElementById(producto_id);
    li.innerHTML = '<form id="formulario-producto-edit"> <input type="text" placeholder="Nombre del producto" id="nombre-producto-editado"> <input type="number" placeholder="Precio" id="precio-editado"> <button id="editar-' + id + '">Editar</button></form>';
    var btn_id = "#editar-" + id;
    var btn = document.querySelector(btn_id);
    btn.addEventListener("click", function () {
      editarProducto(id);
    });
  }

  function editarProducto(id) {
    var producto = {
      "nombre": document.getElementById("nombre-producto-editado").value,
      "precio": document.getElementById("precio-editado").value,
      "id": id
    };
    console.log(producto);
    fetch('productos', {
      method: 'PUT',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(producto)
    }).then(function (response) {
      return response.json();
    }).then(function () {
      return getProductos();
    })["catch"](function (error) {
      return console.log(error);
    });
  } //----------------------------------------------------------------------- VENTAS~ ------------------------------------------------------------


  var lista_venta = document.createElement("ul");
  lista_venta.className = "lista-ventas";
  document.getElementById("formulario-venta").appendChild(lista_venta);
  getVentas();

  function getClienteById(cliente_id) {
    var cliente;
    fetch('clientes/' + cliente_id).then(function (response) {
      return response.json();
    }).then(function (json) {
      cliente = json;
    })["catch"](function (error) {
      return console.log(error);
    });
    console.log(cliente);
    return cliente;
  }

  function getProductoById(producto_id) {
    var producto;
    fetch('clientes/' + producto_id).then(function (response) {
      return response.json();
    }).then(function (json) {
      producto = json;
    })["catch"](function (error) {
      return console.log(error);
    });
    console.log(producto);
    return producto;
  }

  document.getElementById("post-venta").addEventListener("click", function (e) {
    e.preventDefault();
    var cliente_id = document.getElementById("clientes").value;
    var producto_id = document.getElementById("productos").value;
    var date = document.getElementById("date").value;
    var venta = {
      "cliente": getClienteById(cliente_id),
      "producto": getProductoById(producto_id),
      "date": date
    };

    if (venta.cliente != null && venta.producto != null && venta.date != null) {
      //SI ESTAN VACIOS LOS CAMPOS NO SE ENVIA
      fetch('http://localhost:8080/venta', {
        method: 'POST',
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(venta)
      }).then(function (response) {
        return response.json();
      }).then(function () {
        return renderVentas(venta);
      })["catch"](function (error) {
        return console.log(error);
      });
    }

    document.getElementById("formulario-producto").reset(); //SE RESETEAN LOS CAMPOS DEL FORMULARIO
  });

  function getVentas() {
    lista_venta.innerHTML = "";
    fetch('ventas').then(function (response) {
      return response.json();
    }).then(function (ventas) {
      return renderVentas(ventas);
    })["catch"](function (error) {
      return console.log(error);
    });
  }

  function renderVentas(ventas) {
    console.log(ventas);
    if (ventas == undefined) return;
    ventas.forEach(function (venta) {
      lista_venta.innerHTML += "<li class='venta' id=venta-" + venta.id + ">Cliente: " + venta.cliente.nombre + " - Producto: " +
      /* venta.producto.nombre +*/
      " - Fecha: " + venta.fecha + "<button class='botonEliminarVenta' id=" + producto.id + ">Eliminar</button></li>";
      boton_borrar_venta(); //se le da funcionalidad
    });
  }

  function boton_borrar_venta() {
    var buttons = document.getElementsByClassName('botonEliminarVenta');

    var _loop5 = function _loop5(i) {
      buttons[i].addEventListener('click', function () {
        var id = buttons[i].id;
        borrar_venta_en_servidor(id);
      });
    };

    for (var i = 0; i < buttons.length; i++) {
      _loop5(i);
    }
  }

  function borrar_venta_en_servidor(id) {
    fetch('ventas/' + id, {
      'method': 'DELETE',
      'mode': "cors"
    }).then(function (response) {
      return response.json();
    }).then(function () {
      return getVentas();
    })["catch"](function (error) {
      return console.log(error);
    });
  }

  function renderSelectClientes(clientes) {
    var select1 = document.querySelector("#clientes");
    var options = select1.getElementsByTagName("option");

    if (options.length > 0) {
      options.forEach(function (option) {
        option.remove();
      });
    }

    clientes.forEach(function (cliente) {
      var opt = document.createElement('option');
      opt.value = cliente.id;
      opt.innerHTML = cliente.nombre + ": " + cliente.dni;
      select1.appendChild(opt);
    });
  }

  function renderSelectProductos(productos) {
    var select2 = document.querySelector("#productos");
    var options = select2.getElementsByTagName("option");

    if (options.length > 0) {
      options.forEach(function (option) {
        option.remove();
      });
    }

    productos.forEach(function (producto) {
      var opt = document.createElement('option');
      opt.value = producto.id;
      opt.innerHTML = producto.nombre + ": " + producto.precio;
      select2.appendChild(opt);
    });
  }
}