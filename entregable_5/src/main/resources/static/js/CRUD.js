"use strict";
document.addEventListener('DOMContentLoaded', cargarPagina);

function cargarPagina () {
    let lista = document.createElement("ul");
    lista.className = "lista-clientes";
    document.getElementById("formulario-cliente").appendChild(lista);
    getClientes();

    document.getElementById("post-cliente").addEventListener("click", function(e) {
        e.preventDefault();

        let nombre = document.getElementById("nombre-cliente").value;
        let apellido = document.getElementById("apellido").value;
        let dni = document.getElementById("dni").value;

    //OBJETO
        let cliente = { 
            "nombre": nombre,
            "apellido": apellido,
            "dni": dni
        }
        
        if(cliente.nombre != "" && cliente.apellido != "" && cliente.dni != null){  //SI ESTAN VACIOS LOS CAMPOS NO SE ENVIA
            fetch('http://localhost:8080/clientes', {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(cliente)
            })
                .then(response =>  response.json())
                .then( () => getClientes()) 
                .catch(error => console.log(error));

        }

        document.getElementById("formulario-cliente").reset();  //SE RESETEAN LOS CAMPOS DEL FORMULARIO
    });

    function getClientes(){
        let lista = document.querySelector(".lista-clientes");
        lista.innerHTML = ""; //SE VACIA EL DIV
        fetch('clientes')
            .then(response =>  response.json())
            .then(clientes => {
                renderClientes(clientes);
                renderSelectClientes(clientes);
            }) //SE LLAMA A LA FUNCION QUE LOS MUESTRA
            .catch(error => console.log(error));
    }

    function renderClientes(clientes) {
    	let ul = document.querySelector(".lista-clientes");
		if (clientes == undefined){
			ul.style.display = "none";
			return;
		}
        clientes.forEach(cliente => {
                lista.innerHTML += "<li class='cliente' id=cliente-"+ cliente.id+">" + cliente.nombre + " " + cliente.apellido + ": " + cliente.dni+ "<div class='buttons'><button class='botonEditar' id="+ cliente.id+">Editar</button><button class='botonEliminarCliente' id="+ cliente.id+">Eliminar</button></div></li>";
                boton_editar_cliente(); //se le da funcionalidad
                boton_borrar_cliente(); //se le da funcionalidad
        });
		if(ul.innerHTML != "")
			ul.style.display = "flex";
		else{
			ul.style.display = "none";
		}
    }
    
    function boton_borrar_cliente () { 
        let buttons = document.getElementsByClassName('botonEliminarCliente');
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener('click', function() {
                let id = buttons[i].id;
                borrarCliente_en_servidor(id);
                buttons[i].parentElement.parentElement.remove();
            })
        }
    }
    
    function borrarCliente_en_servidor(id) {
        fetch('clientes/'+ id,{
            'method': 'DELETE',
            'mode': "cors"
        })
        .then(response =>  response)
        .then(clientes => getClientes()) 
        .catch(error => console.log(error));
    }
    

    function boton_editar_cliente () { 
        let buttons = document.getElementsByClassName('botonEditar'); 
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener('click', function() {
                   	let id = buttons[i].id;
                    createFormEditCliente(id);
                })
                
            }
    }


    function createFormEditCliente(id){
        let cliente_id = "cliente-" + id;
        let li = document.getElementById(cliente_id);
        li.innerHTML = '<form id="formulario-cliente-edit"> <input type="text" placeholder="Nombre" id="nombre-editado"> <input type="text" placeholder="Apellido" id="apellido-editado"> <input type="number" placeholder="dni" id="dni-editado"><button id="editar-'+ id +'">Editar</button></form>';
        let btn_id = "#editar-"+ id;
        let btn = document.querySelector(btn_id)
        btn.addEventListener("click", function() {
            editarCliente(id);
        });
    }

    function editarCliente(id) {
        let cliente = { 
            "nombre": document.getElementById("nombre-editado").value,
            "apellido": document.getElementById("apellido-editado").value,
            "dni": document.getElementById("dni-editado").value,
            "id": id
        }
    
        fetch('clientes', {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(cliente)
        })
        .then(response =>  response.json())
        .then(() => getClientes())
        .catch(error => console.log(error));
    }

    //----------------------------------------------------------------------- PRODUCTOS ------------------------------------------------------------
    
    let listaProducto = document.createElement("ul");
    listaProducto.className = "lista-productos";
    document.getElementById("formulario-producto").appendChild(listaProducto);
    getProductos();
    document.getElementById("post-producto").addEventListener("click", function(e) {

        e.preventDefault();

        let nombre = document.getElementById("nombre-producto").value;
        let precio = document.getElementById("precio-producto").value;

    //OBJETO
        let producto = { 
            "nombre": nombre,
            "precio": precio,
        }
        
        if(producto.nombre != "" && producto.precio != "" && producto.precio > 0){  //SI ESTAN VACIOS LOS CAMPOS NO SE ENVIA
            fetch('http://localhost:8080/productos', {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(producto)
            })
                .then(response =>  response.json())
                .then( () => getProductos()) 
                .catch(error => console.log(error));

        }

        document.getElementById("formulario-producto").reset();  //SE RESETEAN LOS CAMPOS DEL FORMULARIO
    });

    
    function getProductos(){
        let listaProducto = document.querySelector(".lista-productos"); 
        listaProducto.innerHTML = ""; //SE VACIA EL DIV
        fetch('productos')
            .then(response =>  response.json())
            .then(productos => {
                renderProductos(productos);
                renderSelectProductos(productos);
            }) //SE LLAMA A LA FUNCION QUE LOS MUESTRA
            .catch(error => console.log(error));
    }
    
    function renderProductos(productos) {
    	let ul = document.querySelector(".lista-productos");
		if (productos == undefined){
			ul.style.display = "none";
			return;
		}
        productos.forEach(producto => {
            listaProducto.innerHTML += "<li class='producto' id=producto-"+ producto.id+">" + producto.nombre +" "+ producto.precio+ "<div class='buttons'><button class='botonEditarProducto' id="+ producto.id+">Editar</button><button class='botonEliminarProducto' id="+ producto.id+">Eliminar</button></div></li>";
                boton_editar_producto(); //se le da funcionalidad
                boton_borrar_producto(); //se le da funcionalidad
        });
		if(ul.innerHTML != "")
			ul.style.display = "flex";
		else{
			ul.style.display = "none";
		}
    }
    
    function boton_borrar_producto () { 
        let buttons = document.getElementsByClassName('botonEliminarProducto');
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener('click', function() {
                let id = buttons[i].id;
                borrarproducto_en_servidor(id);
                buttons[i].parentElement.parentElement.remove();
            })
            }
    }
   
    function borrarproducto_en_servidor(id) {
        fetch('productos/'+ id,{
            'method': 'DELETE',
            'mode': "cors"
        })
        .then(response =>  response)
        .then(get => getProductos()) 
        .catch(error => console.log(error));
    }

    
    function boton_editar_producto () { 
        let buttons = document.getElementsByClassName('botonEditarProducto'); 
        for (let i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener('click', function() {
                    let id = buttons[i].id;
                    createFormEditProducto(id);
                })
                
            }
    }


    function createFormEditProducto(id){
        let producto_id = "producto-" + id;
        let li = document.getElementById(producto_id);
        li.innerHTML = '<form id="formulario-producto-edit"> <input type="text" placeholder="Nombre del producto" id="nombre-producto-editado"> <input type="number" placeholder="Precio" id="precio-editado"> <button id="editar-'+ id +'">Editar</button></form>';
        let btn_id = "#editar-"+ id;
        let btn = document.querySelector(btn_id)
        btn.addEventListener("click", function() {
            editarProducto(id);
        });
    }


    function editarProducto(id) {
        let producto = { 
            "nombre": document.getElementById("nombre-producto-editado").value,
            "precio": document.getElementById("precio-editado").value,
            "id": id
        }
        console.log(producto);
    
        fetch('productos', {
            method: 'PUT',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(producto)
        })
        .then(response =>  response.json())
        .then(() => getProductos())
        .catch(error => console.log(error));
    }

    //----------------------------------------------------------------------- VENTAS~ ------------------------------------------------------------
    
    let lista_venta = document.createElement("ul");
    lista_venta.className = "lista-ventas";
    document.getElementById("formulario-venta").appendChild(lista_venta);
    getVentas();

    document.getElementById("post-venta").addEventListener("click", function(e) {
        e.preventDefault();

        let cliente_id = document.getElementById("clientes").value;
        let producto_id = document.getElementById("productos").value;
        let date = document.getElementById("date").value;

        let venta = { 
            "cliente": getClienteById(cliente_id),
            "producto": getProductoById(producto_id),
            "date": date,
        }
		console.log(venta);
        if(venta.cliente != null && venta.producto != null && venta.date != null){  //SI ESTAN VACIOS LOS CAMPOS NO SE ENVIA
            fetch('http://localhost:8080/venta', {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(venta)
            })
                .then(response =>  response.json())
                .then( () => renderVentas(venta)) 
                .catch(error => console.log(error));
        }

        document.getElementById("formulario-producto").reset();  //SE RESETEAN LOS CAMPOS DEL FORMULARIO
    })

    function getClienteById(cliente_id) {
        return fetch('clientes/'+cliente_id)
        .then(response =>  response.json())
        .then(json => {return json;})
        .catch(error => console.log(error));
    }

	function getData(obj){
	  	console.log(obj);
	}
	
    function getProductoById(producto_id) {
        let producto;
        fetch('productos/'+producto_id).then(response =>  response.json()).then(json => {producto = json}).catch(error => console.log(error));
        return producto;
    }


    function getVentas(){
        lista_venta.innerHTML = ""; 
        fetch('ventas').then(response =>  response.json()).then(ventas => renderVentas(ventas)).catch(error => console.log(error));
    }

    function renderVentas(ventas) {
        if (ventas == undefined)
            return;
        ventas.forEach(venta => {
			console.log(venta);
            lista_venta.innerHTML += "<li class='venta' id=venta-"+ venta.id+">Cliente: " + venta.cliente.nombre +" - Producto: "+/* venta.producto.nombre +*/ " - Fecha: " + venta.fecha+"<button class='botonEliminarVenta' id="+ producto.id+">Eliminar</button></li>";
            boton_borrar_venta(); //se le da funcionalidad
        });
    }

    function renderSelectClientes(clientes){
        let select1 = document.querySelector("#clientes");
		select1.innerHTML = "";
        let options = select1.getElementsByTagName("option")

        clientes.forEach(cliente => {
            let opt = document.createElement('option');
            opt.value = cliente.id;
            opt.innerHTML = cliente.nombre + " " + cliente.apellido + ": " + cliente.dni;
            select1.appendChild(opt);
        });
    }

    function renderSelectProductos(productos){
        let select2 = document.querySelector("#productos");
		select2.innerHTML = "";
        let options = select2.getElementsByTagName("option")

        productos.forEach(producto => {
            let opt = document.createElement('option');
            opt.value = producto.id;
            opt.innerHTML = producto.nombre + ": $" + producto.precio;
            select2.appendChild(opt);
        });
    }
}